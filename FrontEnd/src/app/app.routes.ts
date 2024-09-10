import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'api/dashboard',
    // canActivate: [AdminGuard],
    // data: { roles: ['ADMIN', 'ENTERPRISE'] } ,
    loadChildren: () =>
      import('./admin/admin.module').then((m) => m.AdminModule),
  },
  {
    path: 'api/user',
    loadChildren: () => import('./user/user.module').then((m) => m.UserModule),
  },
  {
    path: 'api/auth',
    loadChildren: () => import('./auth/auth.module').then((m) => m.AuthModule),
  },
  { path: '', redirectTo: 'api/auth', pathMatch: 'full' },
];
