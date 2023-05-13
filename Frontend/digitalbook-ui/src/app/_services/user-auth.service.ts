import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserAuthService {

  constructor() { }

  public setUserDetails(userDetails: any) {
    localStorage.setItem('userDetails', JSON.stringify(userDetails));
  }

  public getUserDetails(): any {
    return JSON.parse(localStorage.getItem('userDetails') || '{}');
  }

  public getFullName(): string {
    return JSON.parse(localStorage.getItem('userDetails') || '{}').fullName;
  }

  public getRoles(): string {
    return JSON.parse(localStorage.getItem('userDetails') || '{}').role;
  }

  public setToken(jwtToken: string) {
    localStorage.setItem('jwtToken', jwtToken);
  }

  public getToken(): string {
    return localStorage.getItem('jwtToken') || '';
  }

  public clear() {
    localStorage.clear();
  }

  public isSignedIn() {
    return this.getRoles() && this.getToken();
  }

  public signOut() {
    localStorage.clear();
  }
  
}
