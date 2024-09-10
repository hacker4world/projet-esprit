import { Routes } from '@angular/router';
import { CreateOfferComponent } from './create-offer/create-offer.component';
import { ViewApplicationsComponent } from './view-applications/view-applications.component';

export const routes: Routes = [
  {
    path: 'create-offer',
    component: CreateOfferComponent,
  },
  { path: 'applications/:offerId', component: ViewApplicationsComponent },
];
