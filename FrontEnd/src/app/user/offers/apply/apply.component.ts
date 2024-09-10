import { Component, Inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Offer } from '../../../models/offer';
import { UserDTO } from '../../../models/user.dto';
import { OfferService } from '../../../service/offer.service';
import { UserService } from '../../../service/user.service';
import { SafeUrlPipe } from '../../../user/offers/apply/SafeUrlPipe'; // Adjust pathas necessary
import { ApplicationService } from '../../../service/application.service';
import { Application } from '../../../models/Application';
import { StorageService } from '../../../service/storage.service';


@Component({
  selector: 'app-apply',
  templateUrl: './apply.component.html',
  styleUrl: './apply.component.css'
})
export class ApplyComponent implements OnInit {
  offerId!: number;
  userId!: number;
  offer!: Offer;
  user!: UserDTO;
  resumeURI: string ='';
  errorMessage: string = ''; // For error message
  currentUser!: UserDTO;
  application: Application = {
    id: 0, // Assuming id is managed by backend
    applicationDate: '',
    is_viewed: false,
    offerId: 0,
    userId: 0,
  };  constructor(
    private route: ActivatedRoute,
    private offerService: OfferService,
    private userService: UserService,
    private applicationService : ApplicationService,
    private router: Router,
    private storage: StorageService,

  ) {
    this.currentUser=this.storage.getUser();

  }

  ngOnInit(): void {
    this.offerId = Number(this.route.snapshot.paramMap.get('offerId'));
    this.userId = Number(this.route.snapshot.paramMap.get('userId'));

    this.offerService.getOfferById(this.offerId).subscribe(
      (data: Offer) => {
        this.offer = data;
        console.log(this.offer);
        
      },
      (error) => {
        console.error('Error fetching offer', error);
      }
    );

    this.userService.getUserById(this.userId).subscribe(
      (data: UserDTO) => {
        this.user = data;
        this.resumeURI = data.resumeURI || '';
        console.log(this.user);
         // Assuming resumeURI is a property of User
      },
      (error) => {
        console.error('Error fetching user', error);
      }
    );
  }


  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      const file = input.files[0];
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.resumeURI = e.target.result;
      };
      reader.readAsDataURL(file);

      // If you need to save the new resume to the server
      this.userService.updateResume(this.user.id||0, file).subscribe(
        response => {
          console.log('Resume uploaded successfully');
        },
        error => {
          console.error('Error uploading resume', error);
        }
      );
    }
  }

  apply(offerId: number): void {
    // Application logic
    this.application.applicationDate = new Date().toISOString() ;
    this.application.is_viewed = false;
    this.application.offerId = this.offer.id ;
    this.application.userId = this.userId ;
    console.log(this.offerId);
    console.log(this.userId);
    this.applicationService.apply(this.application , this.offerId , this.user.id||0).subscribe(
      response => {
        console.log('Application submitted successfully', response);
        this.router.navigate(['api/user/offers/Myapplications', this.currentUser.id]);
      },
      error => {
        console.error('Error submitting application', error);
      }
    );    console.log('Applying to offer with ID:', offerId);
  }
}
