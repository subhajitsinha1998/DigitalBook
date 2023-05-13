import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SubscriptionService } from '../_services/subscription.service';

@Component({
  selector: 'app-subscriptions',
  templateUrl: './subscriptions.component.html',
  styleUrls: ['./subscriptions.component.css']
})
export class SubscriptionsComponent implements OnInit {

  constructor(
    private subscriptionService: SubscriptionService,
    private router : Router
  ) { }

  mySubscriptions: any = []
  ngOnInit(): void {
    this.subscriptionService.mySubscriptions().subscribe(
      (response) => {
        this.mySubscriptions = response
      },
      (error) => {
        console.log(error)
      }
    )
  }

  cancelSubscription(subscription: any) {
    this.subscriptionService.cancelSubscription(subscription.id).subscribe(
      (response) => {
        this.mySubscriptions.splice(this.mySubscriptions.indexOf(subscription), 1)
      },
      (error) => {
        console.log(error)
      }
    )
  }

  readSubscribedBook(subscription: any) {
    this.router.navigateByUrl('reader/subscriptions/read', { state: subscription.book.content });
  }

}
