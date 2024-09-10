import { CommonModule, NgFor } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { DocumentService } from '../../service/document.service';
import { SideBarComponent } from '../../shared/side-bar/side-bar.component';
import { DocumentListComponent } from './document-list/document-list.component';
import { routes } from './document-routing.module';

@NgModule({
  declarations: [DocumentListComponent],
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

  providers: [DocumentService],
})
export class DocumentModule {}
