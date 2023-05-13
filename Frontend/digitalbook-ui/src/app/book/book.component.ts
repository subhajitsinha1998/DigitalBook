import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { BookService } from '../_services/book.service';
import { SubscriptionService } from '../_services/subscription.service';
import { UserAuthService } from '../_services/user-auth.service';

@Component({
  selector: 'book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent {
  @Input() bookDetails!: {
    id: number;
    title: string;
    category: string;
    author: string;
    image: string;
    price: string;
    publisher: string;
    active: boolean
  };
  @Input() allowSubscription= false;
  @Input() isMyBook= false;

  constructor(
    private userAuthService: UserAuthService,
    private bookService: BookService,
    private subscriptionService: SubscriptionService,
    private router: Router
  ) { }

  isSignedIn() {
    return this.userAuthService.isSignedIn();
  }

  isActive() {
    return this.bookDetails.active;
  }

  updateBookBlock(block: boolean) {
    this.bookService.updateBookBlock(this.bookDetails.id, block).subscribe(
      ()=>this.bookDetails.active=!block,
      (err)=>console.log(err)
    )
  }

  subscribeBook() {
    var userId = this.userAuthService.getUserDetails().userId;
    var bookId = this.bookDetails.id;
    var subsriptionData = {'bookId': bookId, 'userId': userId};
    this.subscriptionService.subscribeBook(userId, subsriptionData).subscribe(
      () => {
        alert(`Successfully subscribed to book ${this.bookDetails.title}`);
      }, 
      (error) => {
        alert(error.error.message);
      }
    )
  }

  editBook() {
    this.router.navigateByUrl('author/mybooks/edit', { state: this.bookDetails });
  }
}
