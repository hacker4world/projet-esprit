import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserPanelComponent } from './user-panel.component';
import { RouterModule } from '@angular/router';
import { routes } from './user-routings';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ClamisComponent } from './clamis/clamis/clamis.component';
import { ListDocumentsComponent } from './documentsStudent/list-documents/list-documents.component';
import { NewDocumentsComponent } from './documentsStudent/new-documents/new-documents.component';
import { HttpClientModule } from '@angular/common/http';
import { OfferModule } from '../admin/offer/offer.module';
import { UserService } from '../service/user.service';
import { OfferService } from '../service/offer.service';
import { CookieService } from 'ngx-cookie-service';
@NgModule({
  declarations: [
    UserPanelComponent,
    ClamisComponent,
    ListDocumentsComponent,
    NewDocumentsComponent,
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    OfferModule,
  ],
  providers: [UserService, OfferService, CookieService],
})
export class UserModule {}
