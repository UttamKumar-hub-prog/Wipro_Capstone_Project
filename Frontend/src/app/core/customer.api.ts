import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Customer, Feedback, Page } from './models';

@Injectable({ providedIn: 'root' })
export class CustomerApi {
  private base = environment.apiBaseUrl;
  constructor(private http: HttpClient) {}
  create(body: Partial<Customer>) {
    return this.http.post<Customer>(`${this.base}/customers/saveDetails`, body);
  }
  getAll() {
    return this.http.get<Customer[] | Page<Customer>>(
      `${this.base}/customers/getAll`
    );
  }
  getById(id: number) {
    return this.http.get<Customer>(`${this.base}/customers/${id}`);
  }
  submitFeedbackThroughCustomer(body: Partial<Feedback>) {
    return this.http.post(`${this.base}/customers/feedback`, body);
  }
}
