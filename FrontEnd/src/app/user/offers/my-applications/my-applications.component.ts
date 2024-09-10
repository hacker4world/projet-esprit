import { Component, OnInit } from '@angular/core';
import { ApplicationService } from '../../../service/application.service'; // Adjust the path as needed
import { Application } from '../../../models/Application'; // Adjust the path as needed
import { UserDTO } from '../../../models/user.dto'; // Adjust the path as needed
import { StorageService } from '../../../service/storage.service';
import { CommonModule } from '@angular/common';
import { OfferService } from '../../../service/offer.service';
import { Offer } from '../../../models/offer';

@Component({
  selector: 'app-my-applications',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './my-applications.component.html',
  styleUrls: ['./my-applications.component.css']
})
export class MyApplicationsComponent implements OnInit {
  applications: Application[] = [];
  currentUser!: UserDTO;

  constructor(private applicationService: ApplicationService,
     private offerService : OfferService,   
    private storage: StorageService,
  ) {
    this.currentUser=this.storage.getUser();

  }

  ngOnInit(): void {
    this.getApplications();
  }

  getApplications(): void {
    this.applicationService.getApplicationsByUser(this.currentUser).subscribe(
      (applications: Application[]) => {
        this.applications = applications;
        console.log(this.applications);
        
        this.applications.forEach(application => {
          this.offerService.getOfferById(application.offerId).subscribe(
            (offer: Offer) => {
              application.offerDetails = offer;
              //this.applications.push(application);
            },
            (error) => {
              console.error('Error fetching offer details', error);
            }
          );
        });
      },
      (error) => {
        console.error('Error fetching applications', error);
      }
    );
  }
}
