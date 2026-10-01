import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface User {
  name: string;
  email: string;
  password: string;
}

export interface LoginRequest {
  email: string;
  password: string;
}

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'http://localhost:8085/users';
  //private apiUrl = '/api/users';
  constructor(private http: HttpClient) {}

  signUp(user: User): Observable<User> {
    return this.http.post<User>(`${this.apiUrl}/signup`, user);
  }

  login(loginRequest: LoginRequest): Observable<string> {
    return this.http.post(`${this.apiUrl}/login`, loginRequest, {
      responseType: 'text',
    });
  }
}
