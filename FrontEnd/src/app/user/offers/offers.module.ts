import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { routes } from './offer-routings';
import { HttpClientModule } from '@angular/common/http';
import { OfferService } from '../../service/offer.service';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../service/user.service';
import { ApplyComponent } from './apply/apply.component';
import { SafeUrlPipe } from './apply/SafeUrlPipe';
import { ApplicationService } from '../../service/application.service';



@NgModule({
  declarations: [ApplyComponent ,SafeUrlPipe],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    HttpClientModule,
    FormsModule,
 
  ],
  providers :[
    OfferService,
    UserService,
    ApplicationService
  ]
})
export class OffersModule { }
