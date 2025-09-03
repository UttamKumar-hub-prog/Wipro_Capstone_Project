import { Component, OnInit, inject } from '@angular/core';
import { FeedbackApi } from '../../../../core/feedback.api';
import { Feedback } from '../../../../core/models';
import {
  NonNullableFormBuilder,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MaterialModule } from '../../../../material.module';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-users-feedbacks',
  template: `
    <div class="container py-5">
      <h3 class="text-center fw-bold mb-4 text-primary">👥 User Feedbacks</h3>

      <!-- Action Button -->
      <div class="d-flex justify-content-end mb-3">
        <button
          class="btn btn-primary"
          (click)="toggleCreate()"
        >
          {{ createMode ? 'Cancel' : 'New Feedback' }}
        </button>
      </div>

      <!-- Feedback Table -->
      <div class="table-responsive glass-card mb-4 shadow-sm rounded">
        <table class="table table-hover align-middle mb-0">
          <thead class="table-light">
            <tr>
              <th class="text-center">#</th>
              <th>Name</th>
              <th>Email</th>
              <th>Message</th>
            </tr>
          </thead>
          <tbody>
            <tr *ngFor="let r of data">
              <td class="text-center">{{ r.id }}</td>
              <td>{{ r.name }}</td>
              <td>{{ r.email }}</td>
              <td>{{ r.message }}</td>
            </tr>
          </tbody>
        </table>
      </div>

      <!-- Feedback Form -->
      <div *ngIf="createMode" class="card glass-card shadow-sm p-4">
        <h4 class="mb-3 text-secondary">Submit Feedback</h4>
        <form [formGroup]="form" (ngSubmit)="submit()">
          <div class="row g-3">
            <div class="col-md-4 col-12">
              <div class="form-floating">
                <input
                  type="text"
                  class="form-control"
                  id="name"
                  formControlName="name"
                  placeholder="Enter your name"
                />
                <label for="name">Name</label>
              </div>
            </div>

            <div class="col-md-4 col-12">
              <div class="form-floating">
                <input
                  type="email"
                  class="form-control"
                  id="email"
                  formControlName="email"
                  placeholder="Enter your email"
                />
                <label for="email">Email</label>
              </div>
            </div>

            <div class="col-12">
              <div class="form-floating">
                <textarea
                  class="form-control"
                  id="message"
                  formControlName="message"
                  placeholder="Your feedback"
                  style="height: 100px;"
                ></textarea>
                <label for="message">Message</label>
              </div>
            </div>
          </div>

          <div class="d-flex justify-content-end mt-3">
            <button
              type="submit"
              class="btn btn-success"
              [disabled]="form.invalid"
            >
              Save
            </button>
          </div>
        </form>
      </div>
    </div>
  `,
  styles: [`
    h3 {
      font-size: 2rem;
    }

    /* Glass Effect */
    .glass-card {
      background: rgba(255, 255, 255, 0.2);
      backdrop-filter: blur(10px);
      -webkit-backdrop-filter: blur(10px);
      border-radius: 15px;
      border: 1px solid rgba(255, 255, 255, 0.3);
      transition: all 0.3s ease;
    }

    .glass-card:hover {
      transform: translateY(-3px);
      box-shadow: 0 15px 30px rgba(0,0,0,0.1);
    }

    table {
      border-radius: 12px;
      overflow: hidden;
    }

    @media (max-width: 768px) {
      h3 {
        font-size: 1.5rem;
      }
      table {
        font-size: 0.9rem;
      }
      .btn {
        font-size: 0.9rem;
      }
    }
  `],
  imports: [MaterialModule, ReactiveFormsModule, CommonModule],
  standalone: true,
})
export class AdminFeedbacksComponent implements OnInit {
  private readonly api = inject(FeedbackApi);
  private readonly fb = inject(NonNullableFormBuilder);

  data: Feedback[] = [];
  createMode = false;

  form = this.fb.group({
    name: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    message: ['', Validators.required],
  });

  ngOnInit() {
    this.load();
  }

  load() {
    this.api.getAll().subscribe((res) => {
      this.data = Array.isArray(res) ? res : res.content;
    });
  }

  toggleCreate() {
    this.createMode = !this.createMode;
  }

  submit() {
    if (this.form.valid) {
      this.api.create(this.form.value).subscribe(() => {
        this.load();
        this.form.reset();
        this.createMode = false;
      });
    }
  }
}
