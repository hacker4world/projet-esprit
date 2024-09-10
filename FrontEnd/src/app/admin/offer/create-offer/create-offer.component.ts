import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { OfferService } from '../../../service/offer.service';
import { Offer } from '../../../models/offer';
import { UserDTO } from '../../../models/user.dto';
import { StorageService } from '../../../service/storage.service';
import { Router } from '@angular/router';
import { ApplicationService } from '../../../service/application.service';

@Component({
  selector: 'app-create-offer',
  templateUrl: './create-offer.component.html',
  styleUrl: './create-offer.component.css'
})
export class CreateOfferComponent {
  offerForm: FormGroup;
  offers: Offer[] = [];
  currentDate !: string ;
  editMode: boolean = false;
  editedOfferId: number | null = null;
  currentUser!: UserDTO;

  constructor(private fb: FormBuilder, private offerService: OfferService,  private storage: StorageService,    
    private router: Router,
    private applicationService : ApplicationService

  ) {
    this.currentUser=this.storage.getUser();
    this.offerForm = this.fb.group({
      titre: ['', Validators.required],
      description: ['', Validators.required],
      typeOffre: ['', Validators.required] ,
      stillAvailable : [true],
      dateMaj : [''],
      utilisateurId:this.currentUser.id
    });
  }
  ngOnInit(): void {
    this.loadOffers();
    const date = new Date();
    this.currentDate = date.toISOString()
    console.log(this.currentUser);
    
    
  }
  cancelEdit(): void {
    this.editMode = false;
    this.editedOfferId = null;
    this.offerForm.reset({
      typeOffre: '' // Reset only the typeOffre control to its initial value
    });
  }

  loadOffers(): void {
    this.offerService.getOffers().subscribe(
      (data: Offer[]) => {        
        this.offers = data;
        console.log(data);
        
      },
      (error) => {
        console.error('Error fetching offers', error);
      }
    );
  }

  onSubmit(): void {
    console.log(this.offerForm.value);
    const formValues = this.offerForm.value;
    const currentDate = new Date().toISOString(); // Ensure current date is set at submit time
    if (this.editMode && this.editedOfferId !== null) {
      // Update existing offer
      const updatedOffer = {
        ...formValues,
        datePublication: currentDate,
        dateMaj : new Date(),
        id: this.editedOfferId
      };

      this.offerService.updateOffer(this.editedOfferId,updatedOffer).subscribe(
        response => {
          console.log('Offer updated successfully', response);
          this.offerForm.reset({
            typeOffre: '' // Reset only the typeOffre control to its initial value
          });
          this.editMode = false;
          this.editedOfferId = null;
          this.loadOffers(); // Reload offers to reflect the updated offer
        },
        error => {
          console.error('Error updating offer', error);
        }
      );
    } else {
    const newOffer = {
      ...formValues,
      datePublication: currentDate
    };
    if (this.offerForm.valid) {
      console.log('Form is valid', this.offerForm.value);

      this.offerService.createOffer(newOffer).subscribe(
        response => {
          console.log('Offer created successfully', response);
          this.offerForm.reset({
            typeOffre: '' // Reset only the typeOffre control to its initial value
          });          this.loadOffers(); // Reload offers to reflect the newly created offer
        },
        error => {
          console.error('Error creating offer', error);
        }
      );
    } else {
      console.log('Form is invalid', this.offerForm.errors);
    }
  }
  }
  editOffer(offer: Offer): void {
    // Logic to edit the offer
    this.editMode = true;
    this.editedOfferId = offer.id;
    console.log('Editing offer:', offer);
    // Populate the form with the offer details
    this.offerForm.patchValue(offer);
  }

  deleteOffer(offerId : Number)
  {
    this.applicationService.deleteByOfferID(offerId).subscribe(
      response => {
        console.log('applications deleted', response);
        this.offerService.deleteByOfferID(offerId).subscribe(
          response => {console.log('offer deleted', response)},
          error => {
            console.error('Error toggling offer availability', error);
          })
      },
      error => {
        console.error('Error toggling offer availability', error);
      }
    );
  }
  toggleIsValid(offer: Offer) :void {
    offer.isStillAvailable = !offer.isStillAvailable;
    offer.dateMaj = new Date();    
    this.offerService.updateOffer(offer.id,offer).subscribe(
      response => {
        console.log('Offer availability toggled successfully', response);
       // this.loadOffers(); // Reload offers to reflect the updated offer
      },
      error => {
        console.error('Error toggling offer availability', error);
      }
    );
  }

  viewApplications(offerId: number): void {
    this.router.navigate(['api/dashboard/admin/offer-manager/applications', offerId]);
  }
}
