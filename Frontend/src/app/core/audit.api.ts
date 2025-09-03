import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { AuditLog, Page } from './models';

@Injectable({ providedIn: 'root' })
export class AuditApi {
  private readonly base = environment.apiBaseUrl;
  constructor(private readonly http: HttpClient) {}
  getAll(params?: { serviceName?: string; action?: string; status?: string; userId?: number; from?: string; to?: string; }) {
    let p = new HttpParams();
    if (params) Object.entries(params).forEach(([k, v]) => { if (v!==undefined && v!==null && v!=='') p = p.set(k, String(v)); });
    return this.http.get<AuditLog[] | Page<AuditLog>>(`${this.base}/audits/getAll`, { params: p });
  }
  log(body: Partial<AuditLog>) { return this.http.post(`${this.base}/audits/log`, body); }
}
