import { AuthService } from './../../services/auth.service';
import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { MaterialModule } from '../../../material.module';
import {  FormGroup, Validators, FormsModule, ReactiveFormsModule, FormBuilder ,AbstractControl, ValidationErrors } from '@angular/forms';
import { HomeComponentComponent } from "../../../home-component/home-component.component";
import { MatSnackBar } from '@angular/material/snack-bar';






@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [RouterModule, MaterialModule, FormsModule, CommonModule, ReactiveFormsModule, HomeComponentComponent],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent{

  signupForm!: FormGroup;



  hidePassword = true;

  constructor(private readonly fb: FormBuilder,
              private readonly authService: AuthService,
              private readonly snackbar: MatSnackBar,
              private readonly router: Router) {
    this.signupForm = this.fb.group({
      name: [null, [Validators.required, Validators.minLength(5), Validators.maxLength(50), Validators.pattern(/^[a-zA-Z\s]+$/)]],
      email: [
        null,
        [
          Validators.required,
          Validators.email,
          Validators.pattern(/^(?=[a-zA-Z0-9._%+-]{1,64}@)[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/)
        ]
      ],
      password: [
        null,
        [
          Validators.required,
          Validators.minLength(8),
          Validators.maxLength(20),
          Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@#$%^&+=*!?])[A-Za-z\d@#$%^&+=*!?]{8,20}$/)
        ]
      ],
      confirmPassword: [null, [Validators.required]]
    }, { validators: this.passwordsMatchValidator });

    // Subscribe to valueChanges for password and confirmPassword fields
    this.signupForm.get('confirmPassword')?.valueChanges.subscribe(() => {
      this.signupForm.updateValueAndValidity(); // Trigger validation on form control
    });
  }

  // Validator to check if password and confirmPassword match
  passwordsMatchValidator(control: AbstractControl): ValidationErrors | null {
    const password = control.get('password')?.value;
    const confirmPassword = control.get('confirmPassword')?.value;
    return password === confirmPassword ? null : { passwordsMismatch: true };
  }

  get passwordMismatchError(): boolean {
    return (
      this.signupForm.hasError('passwordsMismatch') &&
      ((this.signupForm.get('confirmPassword')?.touched ?? false) ||
        (this.signupForm.get('password')?.touched ?? false))
    );
  }

  togglePasswordVisibility() {
    this.hidePassword = !this.hidePassword;
  }

  onSubmit() {
    const signupData = this.signupForm.value; // Get all form values
    console.log('Signup Data:', signupData); // Log entire form data
  
    this.authService.signup(signupData).subscribe({
      next: (res) => {
        if (res.id != null) {
         // this.router.navigateByUrl('/verify'); // Redirect to verification page
          this.router.navigateByUrl('/login');

        } else {
          this.snackbar.open('Signup failed. Try again.', 'Close', { duration: 5000, panelClass: 'error-snackbar' });
        }
      },
      error: (err) => {
        console.error(err);
        this.snackbar.open('An error occurred. Try again.', 'Close', { duration: 5000, panelClass: 'error-snackbar' });
      }
    });
  }

  





}
