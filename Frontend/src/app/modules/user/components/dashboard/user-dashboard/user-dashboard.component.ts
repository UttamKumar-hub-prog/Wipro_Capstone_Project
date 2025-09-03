import { Component } from '@angular/core';
import { Router, NavigationEnd, RouterOutlet } from '@angular/router';
import { filter } from 'rxjs';
import { StorageService } from '../../../../../auth/services/storage.service';
import { UsernavbarComponent } from '../../userNavbar/usernavbar/usernavbar.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-user-dashboard',
  imports: [UsernavbarComponent, RouterOutlet, CommonModule],
  templateUrl: './user-dashboard.component.html',
  styleUrl: './user-dashboard.component.css',
})
export class UserDashboardComponent {
  isUserLoggedIn = StorageService.isUserLoggedIn();

  constructor(private readonly router: Router) {
    this.router.events.subscribe(() => {
      this.isUserLoggedIn = StorageService.isUserLoggedIn();
    });
  }
  logout() {
    console.log('User logged out');
    StorageService.logout();
    this.router.navigateByUrl('/login');
  }
}
