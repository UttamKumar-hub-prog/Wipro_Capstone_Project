import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UsersAccountsComponent } from './components/accounts/users-accounts.component';
import { UserCustomerComponent } from './components/customers/users-customers.component';
import { UsersPaymentsComponent } from './components/payments/users-payments.component';
import { UsersFeedbacksComponent } from './components/feedback/user-feedbacks.component';
import { UserDashboardComponent } from './components/dashboard/user-dashboard/user-dashboard.component';

const routes: Routes = [
  {
    path: '',
    component: UserDashboardComponent, // parent wrapper with <router-outlet>
    children: [
      { path: '', redirectTo: 'user-dashboard', pathMatch: 'full' },
      { path: 'user-dashboard', component: UserDashboardComponent },
      { path: 'user-account', component: UsersAccountsComponent },
      { path: 'user-customer', component: UserCustomerComponent },
      { path: 'user-payment', component: UsersPaymentsComponent },
      { path: 'user-feedback', component: UsersFeedbacksComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UserRoutingModule {}
