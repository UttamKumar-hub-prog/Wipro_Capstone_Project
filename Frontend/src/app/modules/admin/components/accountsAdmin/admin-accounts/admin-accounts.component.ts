import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
 
import { AccountApi } from '../../../../../core/account.api';
import { Account } from '../../../../../core/models';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-admin-accounts',
  standalone: true,
  imports: [FormsModule, CommonModule],   // ✅ Needed for [(ngModel)]
  templateUrl: './admin-accounts.component.html',
  styleUrls: ['./admin-accounts.component.css']
})
export class AdminAccountsComponent implements OnInit {
  accounts: Account[] = [];
  selected?: Account;

  constructor(private readonly service: AccountApi) {}

  ngOnInit() {
    this.loadAll();
  }

  loadAll() {
    this.service.getAll().subscribe((data: Account[]) => this.accounts = data);
  }

  select(account: Account) {
    this.selected = { ...account };
  }

  save() {
    if (this.selected?.id) {
      this.service.update(this.selected.id, this.selected).subscribe(() => this.loadAll());
    } else {
      this.service.create(this.selected!).subscribe(() => this.loadAll());
    }
    this.selected = undefined;
  }

  
}
