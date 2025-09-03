import { Component } from '@angular/core';
import { StorageService } from '../../../../../auth/services/storage.service';
import { MaterialModule } from '../../../../../material.module';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
 

@Component({
  selector: 'app-usernavbar',

  imports: [MaterialModule, CommonModule, RouterLink],
  templateUrl: './usernavbar.component.html',
  styleUrls: ['./usernavbar.component.css'],
  standalone: true,
})
export class UsernavbarComponent {
  isUserLoggedIn:boolean = StorageService.isUserLoggedIn();

  constructor(private readonly router: Router) {
    this.router.events.subscribe(() => {
      this.isUserLoggedIn = StorageService.isUserLoggedIn();
    });
  }
  logout() {
    StorageService.logout();
    this.router.navigateByUrl('/login');
  }
}
