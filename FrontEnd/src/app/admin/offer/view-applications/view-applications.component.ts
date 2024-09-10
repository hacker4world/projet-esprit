import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Application } from '../../../models/Application';
import { UserDTO } from '../../../models/user.dto';
import { ApplicationService } from '../../../service/application.service';
import { UserService } from '../../../service/user.service';
import { Offer } from '../../../models/offer';
import { OfferService } from '../../../service/offer.service';

@Component({
  selector: 'app-view-applications',
  templateUrl: './view-applications.component.html',
  styleUrl: './view-applications.component.css',
})
export class ViewApplicationsComponent implements OnInit {
  offerId!: number;
  offer !: Offer ;
  applications: Application[] = [];
  users: { [key: number]: UserDTO } = {};

  constructor(
    private route: ActivatedRoute,
    private applicationService: ApplicationService,
    private userService: UserService,
    private offerService: OfferService
  ) {}

  ngOnInit(): void {
    this.offerId = Number(this.route.snapshot.paramMap.get('offerId'));
    this.offerService.getOfferById(this.offerId).subscribe(
      (data: Offer) => {
        this.offer = data; // Assigning the emitted Offer object
      },
      (error) => {
        console.error('Error fetching offer', error);
        // Handle error appropriately
      }
    );
    this.loadApplications();
  }

  loadApplications(): void {
    this.applicationService
      .getApplicationsByOffer(this.offerId)
      .subscribe((applications: Application[]) => {
        this.applications = applications;
        
        this.loadUsers(applications);
      });
  }

  loadUsers(applications: Application[]): void {
    applications.forEach((application) => {
      this.userService
        .getUserById(application.userId)
        .subscribe((user: UserDTO) => {
          this.users[application.userId] = user;
        });
    });
  }
  markApplicationViewed(application : Application): void {
    this.applicationService.markApplicationViewed(application).subscribe(
      response => {
        console.log('Application marked as viewed successfully', response);
        // Handle success, e.g., update UI or show a message
        this.loadApplications();
      },
      error => {
        console.error('Error marking application as viewed', error);
        // Handle error, e.g., show an error message
      }
    );
  }
}
