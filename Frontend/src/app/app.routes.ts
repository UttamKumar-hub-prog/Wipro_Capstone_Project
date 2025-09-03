// app.routes.ts
import { Routes } from '@angular/router';
import { HomeComponentComponent } from './home-component/home-component.component';
import { LoginComponent } from './auth/components/login/login.component';
import { SignupComponent } from './auth/components/signup/signup.component';

export const routes: Routes = [
  { path: '', component: HomeComponentComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  {
    path: 'admin',
    loadChildren: () =>
      import('./modules/admin/admin-routing.module').then((m) => m.AdminRoutingModule),
  },
  {
    path: 'user',
    loadChildren: () =>
      import('./modules/user/user-routing.module').then((m) => m.UserRoutingModule),
  },
  { path: '**', redirectTo: '/login' },
];
