import { Component, inject, OnInit } from '@angular/core';
import { NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { CustomerApi } from '../../../../../core/customer.api';
import { unwrapList } from '../../../../../core/page.util';
import { MaterialModule } from "../../../../../material.module";
import { MaskPanPipe, MaskAadhaarPipe } from "../../../../../core/mask.pipes";
import { CommonModule } from '@angular/common';
// ✅ Customer model (no nulls needed anymore)
export interface Customer {
  name?: string;
  email?: string;
  phone?: string;
  address?: string;
}

@Component({
  selector: 'app-admincustomer',
  imports: [MaterialModule, MaskPanPipe, MaskAadhaarPipe, CommonModule, ReactiveFormsModule],
  templateUrl: './admincustomer.component.html',
  styleUrl: './admincustomer.component.css'
})
export class AdmincustomerComponent implements OnInit {
private readonly api = inject(CustomerApi);
  private readonly fb = inject(NonNullableFormBuilder); // ✅ Non-nullable form builder

  data: Customer[] = [];
  filtered: Customer[] = [];
  displayed = ['name','email','phone','aadhaar','pan'];
  creating = false;

  // ✅ form controls now always return string (no nulls)
  form = this.fb.group({
    name: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    phone: ['', Validators.required],
    address: ['', Validators.required],
  });

  ngOnInit() {
    this.load();
  }

  load() {
    this.api.getAll().subscribe(res => {
      this.data = unwrapList<Customer>(res as any);
      this.filtered = [...this.data];
    });
  }

  applyFilter(e: Event) {
    const q = (e.target as HTMLInputElement).value.toLowerCase();
    this.filtered = this.data.filter(r =>
      (r.name || '').toLowerCase().includes(q) ||
      (r.email || '').toLowerCase().includes(q) ||
      (r.phone || '').toLowerCase().includes(q)
    );
  }

  toggleCreate() {
    this.creating = !this.creating;
  }

  submit() {
    const customer: Customer = this.form.getRawValue(); // ✅ no nulls here
    this.api.create(customer).subscribe(() => {
      this.creating = false;
      this.load();
    });
  }
}
