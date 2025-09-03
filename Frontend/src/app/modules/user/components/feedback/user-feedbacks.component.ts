import { Component, inject } from '@angular/core';
import { FeedbackApi } from '../../../../core/feedback.api';
import { Feedback } from '../../../../core/models';
import { NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { MaterialModule } from "../../../../material.module";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-users-feedbacks',
  standalone: true,
  imports: [MaterialModule, ReactiveFormsModule, CommonModule],
  template: `
    <div class="container mt-4">
      <div class="card shadow p-4">
        <h3 class="mb-3 text-center text-primary">Submit Feedback</h3>
        
        <form [formGroup]="form" (ngSubmit)="submit()" class="row g-3">
          <div class="col-md-6">
            <mat-form-field appearance="outline" class="w-100">
              <mat-label>Name</mat-label>
              <input matInput formControlName="name">
            </mat-form-field>
          </div>

          <div class="col-md-6">
            <mat-form-field appearance="outline" class="w-100">
              <mat-label>Email</mat-label>
              <input matInput formControlName="email">
            </mat-form-field>
          </div>

          <div class="col-12">
            <mat-form-field appearance="outline" class="w-100">
              <mat-label>Message</mat-label>
              <textarea matInput rows="3" formControlName="message"></textarea>
            </mat-form-field>
          </div>

          <div class="col-12 text-center">
            <button mat-raised-button color="primary" [disabled]="form.invalid">
              Save
            </button>
          </div>
        </form>
      </div>
    </div>
  `
})
export class UsersFeedbacksComponent {
  private readonly api = inject(FeedbackApi);
  private readonly fb = inject(NonNullableFormBuilder);

  form = this.fb.group({
    name: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    message: ['', Validators.required],
  });

  submit() {
    if (this.form.invalid) return;

    const feedback: Feedback = this.form.getRawValue();
    this.api.create(feedback).subscribe(() => {
      this.form.reset();
      alert('✅ Feedback submitted successfully');
    });
  }
}
