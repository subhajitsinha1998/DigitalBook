import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SubscriptionService {
  BASE_URL = "http://ec2-52-15-88-109.us-east-2.compute.amazonaws.com:8080/api/v1/digitalbooks/"

  constructor(private httpClient: HttpClient) { }

  subscribeBook(bookId: number, data: any) {
    var url= this.BASE_URL+`reader/book/${bookId}/subscribe`
    return this.httpClient.post(url, data);
  }

  mySubscriptions() {
    var url= this.BASE_URL+`reader/${JSON.parse(localStorage.getItem('userDetails')!).userId}/books`
    return this.httpClient.get(url);
  }

  cancelSubscription(subscriptionId: number) {
    var url= this.BASE_URL+`reader/${JSON.parse(localStorage.getItem('userDetails')!).userId}/books/${subscriptionId}/cancel-subscription`
    return this.httpClient.post(url, null);
  }
}
