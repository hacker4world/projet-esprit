import { Routes } from '@angular/router';
import { OffersComponent } from './offers/offers.component';
import { ApplyComponent } from './apply/apply.component';
import { MyApplicationsComponent } from './my-applications/my-applications.component';

export const routes: Routes = [
{
    path:"list-offer",component: OffersComponent  
} ,
{ path: "apply/:offerId/:userId", component: ApplyComponent },
{ path: "Myapplications/:userId", component: MyApplicationsComponent }

];
