import { CommonModule, NgFor } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ReclamationService } from '../../service/reclamation.service';
import { SideBarComponent } from '../../shared/side-bar/side-bar.component';
import { AdminReclamationListComponent } from './admin-reclamation-list/admin-reclamation-list.component';
import { routes } from './admin-reclamation.routes';
import { AdminReclamationDetailsComponent } from './admin-reclamation-details/admin-reclamation-details.component';

@NgModule({
  declarations: [AdminReclamationListComponent, AdminReclamationDetailsComponent],
  imports: [
    CommonModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule.forChild(routes),
    SideBarComponent,
    FormsModule,
    NgFor,
  ],
  exports: [RouterModule],
  providers: [ReclamationService],
})
export class AdminReclamationModule {}
