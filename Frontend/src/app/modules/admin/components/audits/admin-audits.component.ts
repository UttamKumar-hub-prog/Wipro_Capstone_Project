import { Component, OnInit, inject } from '@angular/core';
import { AuditApi } from '../../../../core/audit.api';
import { AuditLog } from '../../../../core/models';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '../../../../material.module';

@Component({
  selector: 'app-users-audits',
  standalone: true,
  imports: [CommonModule, MaterialModule, ReactiveFormsModule],
  template: `
       <div class="container mt-4">
      <div class="card shadow-lg p-4 bg-light rounded-4">
        <h3 class="text-center text-primary mb-4">📜 Audit Logs</h3>

        <!-- Toggle Filters for Mobile -->
        <div class="d-flex justify-content-end mb-2 d-md-none">
          <button class="btn btn-outline-primary btn-sm" type="button" (click)="toggleFilters()">
            {{ showFilters ? 'Hide Filters' : 'Show Filters' }} 🔽
          </button>
        </div>

        <!-- Filters -->
        <div [ngClass]="{'collapse d-md-block': true, 'show': showFilters}">
          <form (ngSubmit)="load()" class="row g-3 align-items-end">
            <div class="col-sm-6 col-md-3">
              <mat-form-field appearance="outline" class="w-100">
                <mat-label>Service</mat-label>
                <input matInput [formControl]="serviceName" />
              </mat-form-field>
            </div>

            <div class="col-sm-6 col-md-3">
              <mat-form-field appearance="outline" class="w-100">
                <mat-label>Action</mat-label>
                <input matInput [formControl]="action" />
              </mat-form-field>
            </div>

            <div class="col-sm-6 col-md-2">
              <mat-form-field appearance="outline" class="w-100">
                <mat-label>Status</mat-label>
                <input matInput [formControl]="status" />
              </mat-form-field>
            </div>

            <div class="col-sm-6 col-md-2">
              <mat-form-field appearance="outline" class="w-100">
                <mat-label>User ID</mat-label>
                <input matInput type="number" [formControl]="userId" />
              </mat-form-field>
            </div>

            <div class="col-sm-12 col-md-2 text-center">
              <button mat-raised-button color="primary" class="w-100">
                🔍 Search
              </button>
            </div>
          </form>
        </div>

        <!-- Table -->
        <div class="table-responsive mt-4">
          <table
            mat-table
            [dataSource]="data"
            class="mat-elevation-z2 table table-bordered table-hover table-striped align-middle"
          >
            <ng-container matColumnDef="createdAt">
              <th mat-header-cell *matHeaderCellDef>At</th>
              <td mat-cell *matCellDef="let r">{{ r.createdAt | date:'short' }}</td>
            </ng-container>

            <ng-container matColumnDef="serviceName">
              <th mat-header-cell *matHeaderCellDef>Service</th>
              <td mat-cell *matCellDef="let r">{{ r.serviceName }}</td>
            </ng-container>

            <ng-container matColumnDef="action">
              <th mat-header-cell *matHeaderCellDef>Action</th>
              <td mat-cell *matCellDef="let r">{{ r.action }}</td>
            </ng-container>

            <ng-container matColumnDef="status">
              <th mat-header-cell *matHeaderCellDef>Status</th>
              <td mat-cell *matCellDef="let r">{{ r.status }}</td>
            </ng-container>

            <ng-container matColumnDef="userId">
              <th mat-header-cell *matHeaderCellDef>User</th>
              <td mat-cell *matCellDef="let r">{{ r.userId }}</td>
            </ng-container>

            <ng-container matColumnDef="amount">
              <th mat-header-cell *matHeaderCellDef>Amount</th>
              <td mat-cell *matCellDef="let r">{{ r.amount | currency }}</td>
            </ng-container>

            <ng-container matColumnDef="remarks">
              <th mat-header-cell *matHeaderCellDef>Remarks</th>
              <td mat-cell *matCellDef="let r">{{ r.remarks }}</td>
            </ng-container>

            <tr mat-header-row *matHeaderRowDef="displayed"></tr>
            <tr mat-row *matRowDef="let row; columns: displayed" class="hover-row"></tr>
          </table>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .audit-card {
      background: rgba(255, 255, 255, 0.25);
      backdrop-filter: blur(12px);
      -webkit-backdrop-filter: blur(12px);
      border-radius: 1rem;
      box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.37);
      border: 1px solid rgba(255, 255, 255, 0.18);
      transition: transform 0.3s ease, box-shadow 0.3s ease;
    }

    .audit-card:hover {
      transform: translateY(-3px);
      box-shadow: 0 12px 48px rgba(31, 38, 135, 0.37);
    }

    mat-form-field {
      width: 100%;
    }

    th.mat-header-cell {
      background-color: rgba(25, 118, 210, 0.85);
      color: white;
      font-weight: 600;
    }

    td.mat-cell {
      vertical-align: middle;
    }

    .hover-row:hover {
      background-color: rgba(208, 228, 255, 0.5);
      transition: background-color 0.3s ease;
    }

    @media (max-width: 767px) {
      .mat-header-cell, .mat-cell {
        font-size: 0.85rem;
        padding: 0.5rem;
      }

      h3 {
        font-size: 1.5rem;
      }
    }
  `]
,
})
export class UsersAuditsComponent implements OnInit {
  private readonly api = inject(AuditApi);
  data: AuditLog[] = [];
  displayed = [
    'createdAt',
    'serviceName',
    'action',
    'status',
    'userId',
    'amount',
    'remarks',
  ];

  serviceName = inject(FormBuilder).control('');
  action = inject(FormBuilder).control('');
  status = inject(FormBuilder).control('');
  userId = inject(FormBuilder).control<number | null>(null);
  showFilters = false; // toggle state
  ngOnInit() {
    this.load();
  }
toggleFilters() {
    this.showFilters = !this.showFilters;
  }
  load() {
    this.api
      .getAll({
        serviceName: this.serviceName.value || undefined,
        action: this.action.value || undefined,
        status: this.status.value || undefined,
        userId: this.userId.value ?? undefined,
      })
      .subscribe((res) => (this.data = Array.isArray(res) ? res : res.content));
  }
}
