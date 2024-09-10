import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { routes } from './clamis-routings';
import { NewClaimsComponent } from './new-claims/new-claims.component';
import { DetailsClaimsComponent } from './details-claims/details-claims.component';

@NgModule({
  declarations: [
    NewClaimsComponent,
    DetailsClaimsComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forChild(routes),
  ],
  exports: [RouterModule],
})
export class ClamisModule {}
