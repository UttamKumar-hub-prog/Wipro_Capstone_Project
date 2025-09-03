import { Component } from '@angular/core';
import { MaterialModule } from '../material.module';
import { Router, RouterLink } from '@angular/router';
import { StorageService } from '../auth/services/storage.service';
import { CommonModule } from '@angular/common';
import { AdminRoutingModule } from "../modules/admin/admin-routing.module";
import { UserRoutingModule } from '../modules/user/user-routing.module';


@Component({
  selector: 'app-home-component',
  imports: [CommonModule, MaterialModule, RouterLink, AdminRoutingModule, UserRoutingModule],
  templateUrl: './home-component.component.html',
  styleUrl: './home-component.component.css'
})
export class HomeComponentComponent {
 isAdminLoggedIn: boolean = StorageService.isAdminLoggedIn();
    isUserLoggedIn: boolean = StorageService.isUserLoggedIn();

    constructor(private readonly router: Router) {
      this.router.events.subscribe(event => {
        this.isAdminLoggedIn = StorageService.isAdminLoggedIn();
        this.isUserLoggedIn = StorageService.isUserLoggedIn();
      });
    }

  
    logout()
    {
      StorageService.logout();
      this.router.navigateByUrl('/login');
    }

}
