import { Routes } from '@angular/router';
import { ClamisComponent } from './clamis/clamis.component';
import { NewClaimsComponent } from './new-claims/new-claims.component';
import { DetailsClaimsComponent } from './details-claims/details-claims.component';
export const routes: Routes = [
  {
    path: '',
    component: ClamisComponent,
  },
  {
    path: 'new',
    component: NewClaimsComponent,
  },

  {
    path: ':id',
    component: DetailsClaimsComponent,
  },
];
