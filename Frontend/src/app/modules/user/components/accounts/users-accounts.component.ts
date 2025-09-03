import { Component, OnInit, inject } from '@angular/core';
import { AccountApi } from '../../../../core/account.api';
import { Account } from '../../../../core/models';
import { MaskAadhaarPipe, MaskPanPipe } from '../../../../core/mask.pipes';
import { MaterialModule } from '../../../../material.module';
import { CommonModule, DecimalPipe } from '@angular/common';
import { StorageService } from '../../../../auth/services/storage.service';

@Component({
  selector: 'app-users-accounts',
  template: `
    <div class="container my-4">
      <h3 class="text-center text-primary mb-4">My Account</h3>

      <!-- ✅ If account exists -->
      <div
        *ngIf="account"
        class="table-responsive glass-card p-3 shadow-sm rounded"
      >
        <table
          mat-table
          [dataSource]="[account]"
          class="table table-striped table-hover text-white"
        >
          <ng-container matColumnDef="id">
            <th mat-header-cell *matHeaderCellDef>ID</th>
            <td mat-cell *matCellDef="let r">{{ r.id }}</td>
          </ng-container>

          <ng-container matColumnDef="accNo">
            <th mat-header-cell *matHeaderCellDef>Acc No</th>
            <td mat-cell *matCellDef="let r">{{ r.accNo }}</td>
          </ng-container>

          <ng-container matColumnDef="username">
            <th mat-header-cell *matHeaderCellDef>Username</th>
            <td mat-cell *matCellDef="let r">{{ r.username }}</td>
          </ng-container>

          <ng-container matColumnDef="aadhaar">
            <th mat-header-cell *matHeaderCellDef>Aadhaar</th>
            <td mat-cell *matCellDef="let r">{{ r.aadhaar | maskAadhaar }}</td>
          </ng-container>

          <ng-container matColumnDef="pan">
            <th mat-header-cell *matHeaderCellDef>PAN</th>
            <td mat-cell *matCellDef="let r">{{ r.pan | maskPan }}</td>
          </ng-container>

          <ng-container matColumnDef="bankType">
            <th mat-header-cell *matHeaderCellDef>Bank Type</th>
            <td mat-cell *matCellDef="let r">{{ r.bankType }}</td>
          </ng-container>

          <ng-container matColumnDef="balance">
            <th mat-header-cell *matHeaderCellDef>Balance</th>
            <td mat-cell *matCellDef="let r">
              {{ r.balance | number : '1.0-0' }}
            </td>
          </ng-container>

          <ng-container matColumnDef="loan">
            <th mat-header-cell *matHeaderCellDef>Loan</th>
            <td mat-cell *matCellDef="let r">
              {{ r.loan | number : '1.0-0' }}
            </td>
          </ng-container>

          <ng-container matColumnDef="address">
            <th mat-header-cell *matHeaderCellDef>Address</th>
            <td mat-cell *matCellDef="let r">{{ r.address }}</td>
          </ng-container>

          <tr
            mat-header-row
            *matHeaderRowDef="displayed"
            class="table-header"
          ></tr>
          <tr mat-row *matRowDef="let row; columns: displayed"></tr>
        </table>
      </div>

      <!-- ✅ If no account -->
      <p *ngIf="!account" class="text-center text-danger mt-4">
        No account found for this user.
      </p>
    </div>
  `,
  styles: [
    `
      /* Glass effect card */
      .glass-card {
        background: rgba(255, 255, 255, 0.5);
        backdrop-filter: blur(12px);
        -webkit-backdrop-filter: blur(12px);
        border: 1px solid rgba(255, 255, 255, 0.2);
      }

      /* Table text color */
      .table th,
      .table td {
        color: #000; /* fixed typo and made text black for visibility */
      }

      /* Header style */
      .table-header th {
        background: rgba(109, 109, 109, 0.1);
        font-weight: 600;
      }

      /* Hover effect */
      .table-hover tbody tr:hover {
        background: rgba(255, 255, 255, 0.2);
        cursor: pointer;
        transition: background 0.3s;
      }

      /* Responsive heading */
      h3 {
        font-size: 1.8rem;
      }

      /* Small screens adjustments */
      @media (max-width: 768px) {
        h3 {
          font-size: 1.5rem;
        }
        .glass-card {
          padding: 1rem;
        }
      }
    `,
  ],
  imports: [
    MaskAadhaarPipe,
    MaskPanPipe,
    MaterialModule,
    DecimalPipe,
    CommonModule,
  ],
  standalone: true,
})
export class UsersAccountsComponent implements OnInit {
  private readonly api = inject(AccountApi);

  account?: Account;
  displayed = [
    'id',
    'accNo',
    'username',
    'aadhaar',
    'pan',
    'bankType',
    'balance',
    'loan',
    'address',
  ];

  ngOnInit() {
    const user = StorageService.getUser(); // 👈 contains {id, username}
    if (user?.id) {
      this.loadCustomerInfo(user.id);
    }
  }

  loadCustomerInfo(id: number) {
    this.api.getCustomerInfo(id).subscribe({
      next: (res) => (this.account = res),
      error: () => (this.account = undefined), // if not found → show message
    });
  }
}
