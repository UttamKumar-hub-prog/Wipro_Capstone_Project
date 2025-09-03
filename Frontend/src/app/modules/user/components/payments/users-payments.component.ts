import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { PaymentApi } from '../../../../core/payment.api';
import { ToastService } from '../../../../core/toast.service';
import { MaterialModule } from '../../../../material.module';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-users-payments',
  standalone: true,
  imports: [MaterialModule, ReactiveFormsModule, CommonModule],
  template: `
    <div class="container mt-4">
      <div class="card shadow p-4">
        <h3 class="mb-3 text-center text-success">Payments</h3>

        <form [formGroup]="form" (ngSubmit)="submit()" class="row g-3">
          <div class="col-md-4">
            <mat-form-field appearance="outline" class="w-100">
              <mat-label>Sender ID</mat-label>
              <input matInput type="number" formControlName="senderId" />
            </mat-form-field>
          </div>

          <div class="col-md-4">
            <mat-form-field appearance="outline" class="w-100">
              <mat-label>Receiver ID</mat-label>
              <input matInput type="number" formControlName="receiverId" />
            </mat-form-field>
          </div>

          <div class="col-md-4">
            <mat-form-field appearance="outline" class="w-100">
              <mat-label>Amount</mat-label>
              <input matInput type="number" formControlName="amount" />
            </mat-form-field>
          </div>

          <div class="col-12 text-center mt-2">
            <button
              mat-raised-button
              color="primary"
              class="px-4"
              [disabled]="form.invalid"
            >
              💸 Transfer
            </button>
          </div>
        </form>
      </div>
    </div>
  `,
})
export class UsersPaymentsComponent {
  private readonly api = inject(PaymentApi);
  private readonly toast = inject(ToastService);

  form = inject(FormBuilder).group({
    senderId: [null, [Validators.required, Validators.min(1)]],
    receiverId: [null, [Validators.required, Validators.min(1)]],
    amount: [1, [Validators.required, Validators.min(1)]],
  });

  submit() {
    this.api
      .transfer(this.form.value as any)
      .subscribe(() => this.toast.open('✅ Transfer submitted'));
  }
}
