import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-read-subscribed-book',
  templateUrl: './read-subscribed-book.component.html',
  styleUrls: ['./read-subscribed-book.component.css']
})
export class ReadSubscribedBookComponent {
  constructor(private router: Router, private activatedRoute: ActivatedRoute) {
    this.content = this.router.getCurrentNavigation()!.extras.state;
    if (this.content === undefined) {
      this.router.navigateByUrl('reader/subscriptions')
    }
  }
  content:any
}
