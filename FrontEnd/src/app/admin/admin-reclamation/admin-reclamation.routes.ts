import { Routes } from '@angular/router';
import { AdminReclamationDetailsComponent } from './admin-reclamation-details/admin-reclamation-details.component';
import { AdminReclamationListComponent } from './admin-reclamation-list/admin-reclamation-list.component';

export const routes: Routes = [
  {
    path: '',
    component: AdminReclamationListComponent,
  },
  {
    path: ':id',
    component: AdminReclamationDetailsComponent,
  },
];
