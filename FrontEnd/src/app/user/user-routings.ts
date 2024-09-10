import { Routes } from '@angular/router';
import { AdminPanelComponent } from '../admin/admin-panel.component';
import { UserPanelComponent } from './user-panel.component';

export const routes: Routes = [
  {
    path: '',
    component: UserPanelComponent,
    children: [
      {
        path: 'manag',
        data: {
          roles: ['STUDENT'],
        },
        loadChildren: () =>
          import('./user-profile/profile.module').then((m) => m.ProfileModule),
      },
      {
        path: 'claims',
        data: {
          roles: ['STUDENT'],
        },
        loadChildren: () =>
          import('./clamis/clamis.module').then((m) => m.ClamisModule),
      },
      {
        path: 'documents',
        data: {
          roles: ['STUDENT'],
        },
        loadChildren: () =>
          import('./documentsStudent/documentStudent.module').then(
            (m) => m.DocumentsStudentModule
          ),
      },
      {
        path: 'offers',
        data: {
          roles: ['STUDENT'],
        },
        loadChildren: () =>
          import('./offers/offers.module').then(
            (m) => m.OffersModule
          ),
      },
      {
        path: 'books',
        loadChildren: () =>
          import('./books/books.module').then((m) => m.BooksModule),
      },
    ],
  },
];
