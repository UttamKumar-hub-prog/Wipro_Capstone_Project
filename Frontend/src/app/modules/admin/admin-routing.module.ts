import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminDashboardComponent } from './components/dashboard/dashboard.component';
import { AdminAccountsComponent } from './components/accountsAdmin/admin-accounts/admin-accounts.component';
import { UsersAuditsComponent } from './components/audits/admin-audits.component';
import { AdminFeedbacksComponent } from './components/feedback/admin-feedbacks.component';
import { AdmincustomerComponent } from './components/customers/admincustomer/admincustomer.component';

const routes: Routes = [
  {
    path: '',
    component: AdminDashboardComponent, // parent wrapper with <router-outlet>
    children: [
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
      { path: 'dashboard', component: AdminDashboardComponent },
      { path: 'accounts', component: AdminAccountsComponent },
      { path: 'customers', component: AdmincustomerComponent },
      { path: 'feedbacks', component: AdminFeedbacksComponent },
      { path: 'auditlogs', component: UsersAuditsComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AdminRoutingModule {}
