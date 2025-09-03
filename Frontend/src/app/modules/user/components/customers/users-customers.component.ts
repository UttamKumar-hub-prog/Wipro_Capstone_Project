import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { CustomerApi } from '../../../../core/customer.api';
import { Customer } from '../../../../core/models';
import { MaterialModule } from '../../../../material.module';

@Component({
  selector: 'app-users-customers',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, MaterialModule],
  template: `
    <section
      *ngIf="creating"
      class="create-customer-container card shadow-sm p-4"
    >
      <h4 class="mb-4 text-center fw-bold">Create Customer</h4>

      <form [formGroup]="form" (ngSubmit)="submit()">
        <div class="row g-3">
          <!-- Name -->
          <div class="col-md-6 col-12">
            <mat-form-field appearance="outline" class="w-100">
              <mat-label>Name</mat-label>
              <input
                matInput
                formControlName="name"
                placeholder="Enter full name"
              />
            </mat-form-field>
          </div>

          <!-- Email -->
          <div class="col-md-6 col-12">
            <mat-form-field appearance="outline" class="w-100">
              <mat-label>Email</mat-label>
              <input
                matInput
                formControlName="email"
                placeholder="Enter email"
              />
            </mat-form-field>
          </div>

          <!-- Phone -->
          <div class="col-md-6 col-12">
            <mat-form-field appearance="outline" class="w-100">
              <mat-label>Phone</mat-label>
              <input
                matInput
                formControlName="phone"
                placeholder="Enter phone number"
              />
            </mat-form-field>
          </div>

          <!-- Address -->
          <div class="col-md-6 col-12">
            <mat-form-field appearance="outline" class="w-100">
              <mat-label>Address</mat-label>
              <input
                matInput
                formControlName="address"
                placeholder="Enter address"
              />
            </mat-form-field>
          </div>

          <!-- Aadhaar -->
          <div class="col-md-6 col-12">
            <mat-form-field appearance="outline" class="w-100">
              <mat-label>Aadhaar</mat-label>
              <input
                matInput
                formControlName="aadhaar"
                placeholder="Enter Aadhaar"
              />
            </mat-form-field>
          </div>

          <!-- PAN -->
          <div class="col-md-6 col-12">
            <mat-form-field appearance="outline" class="w-100">
              <mat-label>PAN</mat-label>
              <input matInput formControlName="pan" placeholder="Enter PAN" />
            </mat-form-field>
          </div>

          <!-- Age -->
          <div class="col-md-6 col-12">
            <mat-form-field appearance="outline" class="w-100">
              <mat-label>Age</mat-label>
              <input
                matInput
                type="number"
                formControlName="age"
                placeholder="Enter age"
              />
            </mat-form-field>
          </div>

          <!-- Gender -->
          <div class="col-md-6 col-12">
            <mat-form-field appearance="outline" class="w-100">
              <mat-label>Gender</mat-label>
              <mat-select formControlName="gender">
                <mat-option value="MALE">Male</mat-option>
                <mat-option value="FEMALE">Female</mat-option>
                <mat-option value="OTHER">Other</mat-option>
              </mat-select>
            </mat-form-field>
          </div>

          <!-- KYC Status -->
          <div class="col-md-6 col-12">
            <mat-form-field appearance="outline" class="w-100">
              <mat-label>KYC Status</mat-label>
              <mat-select formControlName="kycStatus">
                <mat-option value="PENDING">Pending</mat-option>
                <mat-option value="VERIFIED">Verified</mat-option>
                <mat-option value="REJECTED">Rejected</mat-option>
              </mat-select>
            </mat-form-field>
          </div>

          <!-- Account Type -->
          <div class="col-md-6 col-12">
            <mat-form-field appearance="outline" class="w-100">
              <mat-label>Account Type</mat-label>
              <mat-select formControlName="accountType">
                <mat-option value="SAVINGS">Savings</mat-option>
                <mat-option value="CURRENT">Current</mat-option>
                <mat-option value="FIXED">Fixed</mat-option>
              </mat-select>
            </mat-form-field>
          </div>
        </div>

        <!-- Save Button -->
        <div class="d-flex justify-content-end mt-4">
          <button mat-raised-button color="primary" [disabled]="form.invalid">
            Save
          </button>
        </div>
      </form>
    </section>
  `,
  styles: [
    `
      .create-customer-container {
        border-radius: 12px;
        background: #ffffff;
        max-width: 950px;
        margin: 0 auto;
      }

      h4 {
        font-size: 1.5rem;
        color: #333;
      }

      mat-form-field {
        font-size: 14px;
      }

      button {
        min-width: 120px;
      }

      /* Responsive */
      @media (max-width: 768px) {
        h4 {
          font-size: 1.3rem;
        }
        mat-form-field {
          font-size: 13px;
        }
        button {
          width: 100%;
        }
      }
    `,
  ],
})
export class UserCustomerComponent implements OnInit {
  private api = inject(CustomerApi);

  creating = true;

  form = inject(FormBuilder).group({
    name: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    phone: ['', Validators.required],
    address: ['', Validators.required],
    aadhaar: ['', [Validators.pattern(/^[0-9]{12}$/)]],
    pan: ['', [Validators.pattern(/^[A-Z]{5}[0-9]{4}[A-Z]{1}$/)]],
    age: [null],
    gender: [''],
    kycStatus: [''],
    accountType: [''],
  });

  ngOnInit() {}

  submit() {
    if (this.form.valid) {
      this.api.create(this.form.value as Partial<Customer>).subscribe(() => {
        this.creating = false;
        this.form.reset();
      });
    }
  }
}
