import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../../core/models';


const BASE_URL="http://localhost:8765/";


@Injectable({
  providedIn: 'root'
})
export class AuthService {


  constructor(private  readonly http:HttpClient) { }

  signup(signupRequest:User):Observable<any>
  {
    return this.http.post(BASE_URL+"auth/signup",signupRequest)
  }
  login(loginRequest:User):Observable<any>
  {
    return this.http.post(BASE_URL+"auth/login",loginRequest)
  }
    
}
