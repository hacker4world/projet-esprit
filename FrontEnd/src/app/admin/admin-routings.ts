import { Routes } from '@angular/router';
import { AdminPanelComponent } from './admin-panel.component';
export const routes: Routes = [
  {
    path: 'admin',
    component: AdminPanelComponent,
    children: [
      {
        path: 'user-manager',
        //data: {
          //roles: ['ADMIN'],
        //},
        loadChildren: () =>
          import('./admin-manger/admin-manager.module').then(
            (m) => m.AdminMangerModule
          ),
      },

      {
        path: 'book',
        loadChildren: () =>
          import('./book/book.module').then((m) => m.BookModule),
      },
      {
        path: 'offer-manager',
        loadChildren: () =>
          import('./offer/offer.module').then((m) => m.OfferModule),
      },
      {
        path: 'document-manager',
        loadChildren: () =>
          import('./document/document.module').then((m) => m.DocumentModule),
      },
      {
        path: 'admin-classroom',
        loadChildren: () =>
          import('./admin-classroom/admin-classroom.module').then(
            (m) => m.AdminClassroomModule
          ),
      },
      {
        path: 'admin-test-evalutions',
        loadChildren: () =>
          import('./admin-test-evalution/admin-test-evalution.module').then(
            (m) => m.AdminTestEvalutionModule
          ),
      },
      {
        path: 'admin-subject',
        loadChildren: () =>
          import('./admin-subject/admin-subject.module').then(
            (m) => m.AdminSubjectModule
          ),
      },
      {
        path: 'admin-subject-test',
        loadChildren: () =>
          import('./admin-subject-test/admin-subject-test.module').then(
            (m) => m.AdminSubjectTestModule
          ),
      },
      {
        path: 'admin-reclamation',
        loadChildren: () =>
          import('./admin-reclamation/admin-reclamation.module').then(
            (m) => m.AdminReclamationModule
          ),
      },
    ],
  },

  {
    path: '**',
    redirectTo: 'admin',
    pathMatch: 'full',
  },
];
