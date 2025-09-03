import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { StorageService } from '../../../auth/services/storage.service';

const BASE_URL = 'http://localhost:8765/'; // Your backend URL

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  constructor(private readonly http: HttpClient) {}

  // --------------------- Users ---------------------
  getAllUsers(): Observable<any> {
    return this.http
      .get(BASE_URL + 'users', { headers: this.getAuthHeaders() })
      .pipe(catchError(this.handleError));
  }

  // --------------------- Feedbacks ---------------------
  getAllFeedbacks(): Observable<any> {
    return this.http
      .get(BASE_URL + 'feedbacks', { headers: this.getAuthHeaders() })
      .pipe(catchError(this.handleError));
  }

  // --------------------- Accounts ---------------------
  getAllAccounts(): Observable<any> {
    return this.http
      .get(BASE_URL + 'accounts', { headers: this.getAuthHeaders() })
      .pipe(catchError(this.handleError));
  }

  // --------------------- Audit Logs ---------------------
  getAuditLogs(): Observable<any> {
    return this.http
      .get(BASE_URL + 'auditlogs', { headers: this.getAuthHeaders() })
      .pipe(catchError(this.handleError));
  }

  // --------------------- Helpers ---------------------
  private getAuthHeaders(): HttpHeaders {
    let token = '';
    if (typeof window !== 'undefined') {
      token = StorageService.getToken(); // SSR-safe
    }
    return new HttpHeaders().set('Authorization', 'Bearer ' + token);
  }

  private handleError(error: any) {
    console.error('AdminService error:', error);
    return throwError(() => error);
  }
}
