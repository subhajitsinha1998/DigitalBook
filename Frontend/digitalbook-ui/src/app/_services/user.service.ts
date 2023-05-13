import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserAuthService } from './user-auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  BASE_URL = "http://ec2-52-15-88-109.us-east-2.compute.amazonaws.com:8080/api/v1/digitalbooks/"
  requestHeader = new HttpHeaders({"No-Auth": "True"})

  constructor(private httpClient: HttpClient) { }

  public signUp(signUpData: any) {
    return this.httpClient.post(this.BASE_URL+'sign-up', signUpData, {headers: this.requestHeader});
  }

  public signIn(signInData: any) {
    return this.httpClient.post(this.BASE_URL+'sign-in', signInData, {headers: this.requestHeader});
  }

  public matchRole(allowedRoles: string[]): boolean {
    const userRole = JSON.parse(localStorage.getItem('userDetails') || '{}').role?.toLowerCase();
    if (userRole){
      for(let i=0; i<= allowedRoles.length; i++){
        if (allowedRoles[i] === userRole) {
          return true
        }
      }
    }
    return false
  }
}
