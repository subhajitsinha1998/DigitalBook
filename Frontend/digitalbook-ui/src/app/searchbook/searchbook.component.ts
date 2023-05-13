import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { BookService } from '../_services/book.service';
import { UserAuthService } from '../_services/user-auth.service';

@Component({
  selector: 'app-searchbook',
  templateUrl: './searchbook.component.html',
  styleUrls: ['./searchbook.component.css']
})
export class SearchbookComponent {
  constructor(
    private bookService: BookService,
    private userAuthService: UserAuthService
  ) { }
  booksFound!: Array<any>
  notfound = false;

  searchBook(searchBookForm: NgForm) {
    this.bookService.searchBook(searchBookForm.value).subscribe(
      (response: any) => {
        if (response.length > 0) {
          this.booksFound = response
          this.notfound = false
        } else {
          this.booksFound = []
          this.notfound = true
        }
      },
      (error) => {
        console.log(error)
      }
    )
  }

  allowSubscription() {
    return Boolean(this.userAuthService.isSignedIn());
  }
}
