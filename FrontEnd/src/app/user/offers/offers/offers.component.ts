import { Component, OnInit } from '@angular/core';
import { Offer } from '../../../models/offer';
import { OfferService } from '../../../service/offer.service';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../../service/user.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { UserDTO } from '../../../models/user.dto';
import { StorageService } from '../../../service/storage.service';
import { ApplicationService } from '../../../service/application.service';
import { Application } from '../../../models/Application';

@Component({
  selector: 'app-offers',
  standalone: true,
  imports: [CommonModule,
    RouterModule,
    FormsModule
  ],
  templateUrl: './offers.component.html',
  styleUrl: './offers.component.css',
})
export class OffersComponent implements OnInit {
  offers: Offer[] = [];
  currentDate!: string;
  userId!: number;
  filteredOffers: Offer[] = [];
  selectedType: string = '';
  sortOrder: string = 'asc';
  offerTypes: string[] = ['EMPLOIE', 'STAGE']; // Example offer types
  selectedOffer: Offer | null = null; // Variable to store the selected offer
  currentUser!: UserDTO;
  isApplied !: boolean ;


  constructor(private offerService: OfferService ,
    private applicationService : ApplicationService,
     private UserService :UserService ,private modalService: NgbModal, private router: Router,
     private storage: StorageService,
    ) {
    this.currentUser=this.storage.getUser();
    
  }

  ngOnInit(): void {
    const date = new Date();
    this.currentDate = date.toISOString();
    this.loadOffers();
  }

  loadOffers(): void {
    this.offerService.getOffers().subscribe(
      (data: Offer[]) => {
        this.offers = data;
        this.offers.forEach(offer => {
          this.UserService.getUserById(offer.utilisateurId).subscribe(user => {
            offer.user = user;
            this.hasUserApplied(offer);
            this.applyFilters(); // Apply filters after loading offers and their users
          });
        });
      },
      (error) => {
        console.error('Error fetching offers', error);
      }
    );
  }

  applyFilters(): void {
    let filtered = this.offers;

    console.log('Initial offers:', this.offers);

    // Filter by type
    if (this.selectedType) {
      filtered = filtered.filter((offer) => offer.typeOffre === this.selectedType);
    }

    // Sort by name
    if (this.sortOrder === 'asc') {
      filtered.sort((a, b) => a.titre.localeCompare(b.titre));
    } else {
      filtered.sort((a, b) => b.titre.localeCompare(a.titre));
    }

    this.filteredOffers = filtered;
  }
  openOfferDetails(content: any, offer: Offer): void {
    this.selectedOffer = offer;
    console.log(this.selectedOffer.hasUserApplied);
    
     // Check if user has applied before opening the modal
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' });
  }
  apply(offerId: number| undefined): void {
    this.router.navigate(['api/user/offers/apply', offerId, this.currentUser.id]);
  }
  hasUserApplied(offer: Offer): void {
    this.applicationService.getApplicationsByUserandOffer(this.currentUser, offer).subscribe(
      (applications: Application[]) => {        
        offer.hasUserApplied = applications.length > 0;
        
      },
      (error) => {
        console.error('Error checking if user has applied', error);
      }
    );
  }
}