import { CommonModule } from "@angular/common";
import { Component } from "@angular/core";
import { FormsModule, ReactiveFormsModule, FormGroup, FormBuilder, Validators } from "@angular/forms";
import { MatSnackBar } from "@angular/material/snack-bar";
import { Router, RouterModule } from "@angular/router";
 
import { HomeComponentComponent } from "../../../home-component/home-component.component";
import { MaterialModule } from "../../../material.module";
import { AuthService } from "../../services/auth.service";
import { StorageService } from "../../services/storage.service";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    MaterialModule,
    HomeComponentComponent,
  ],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  loginForm!: FormGroup;
  hidePassword = true;
  isLoading = false;

  constructor(
    private readonly fb: FormBuilder,
    private readonly authService: AuthService,
    private readonly snackbar: MatSnackBar,
    private readonly router: Router
  ) {
    this.loginForm = this.fb.group({
      email: [null, [Validators.required, Validators.email]],
      password: [null, [Validators.required]],
    });
  }

  togglePasswordVisibility() {
    this.hidePassword = !this.hidePassword;
  }

  onSubmit() {
    if (this.loginForm.invalid) return;

    this.isLoading = true;
    this.authService.login(this.loginForm.value).subscribe({
      next: (res) => {
        this.isLoading = false;

        if (res.userId != null) {
          const user = { id: res.userId, role: res.userRole };
          StorageService.saveUser(user);
          StorageService.saveToken(res.jwt);

          if (StorageService.isAdminLoggedIn()) {
            this.router.navigate(['/admin/dashboard']);
            this.loginForm.reset();
          } else if (StorageService.isUserLoggedIn()) {
            this.router.navigate(['/user/user-dashboard']);
            

          }

          this.snackbar.open('Login Successful', 'Close', { duration: 5000 });
        } else {
          this.snackbar.open('Login failed. Invalid credentials', 'Close', {
            duration: 5000,
            panelClass: 'error-snackbar',
          });
        }
      },
      error: (err) => {
        this.isLoading = false;
        this.snackbar.open('Login failed. Please try again.', 'Close', {
          duration: 5000,
          panelClass: 'error-snackbar',
          
        });
        console.error(err);
      },
    });
  }
}
