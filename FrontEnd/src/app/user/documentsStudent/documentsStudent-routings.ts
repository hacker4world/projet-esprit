import { Routes } from '@angular/router';
import { ListDocumentsComponent } from './list-documents/list-documents.component';
import { NewDocumentsComponent } from './new-documents/new-documents.component';

export const routes: Routes = [
  {
    path: '',
    component: ListDocumentsComponent,
  },
  {
    path: 'new',
    component: NewDocumentsComponent,
  },
];
