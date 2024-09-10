import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CreateOfferComponent } from './create-offer/create-offer.component';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { routes } from './offer-manager-routings';
import { OfferService } from '../../service/offer.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ViewApplicationsComponent } from './view-applications/view-applications.component';



@NgModule({
  declarations: [CreateOfferComponent ,ViewApplicationsComponent],
  imports: [
    CommonModule,
    HttpClientModule,
    RouterModule.forChild(routes),
    FormsModule,
    ReactiveFormsModule
  ],
  providers :[OfferService,
    
  ]
})
export class OfferModule { }
