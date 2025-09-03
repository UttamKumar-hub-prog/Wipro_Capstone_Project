import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Feedback, Page } from './models';

@Injectable({ providedIn: 'root' })
export class FeedbackApi {
  private readonly base = environment.apiBaseUrl;
  constructor(private http: HttpClient) {}
  create(body: Partial<Feedback>) {
    return this.http.post<Feedback>(`${this.base}/feedbacks/saveFeedback`, body);
  }
  getAll() {
    return this.http.get<Feedback[] | Page<Feedback>>(
      `${this.base}/feedbacks/getAll`
    );
  }
}
