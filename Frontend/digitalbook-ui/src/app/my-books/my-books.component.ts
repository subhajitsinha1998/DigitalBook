import { Component, OnInit } from '@angular/core';
import { BookService } from '../_services/book.service';

@Component({
  selector: 'app-my-books',
  templateUrl: './my-books.component.html',
  styleUrls: ['./my-books.component.css']
})
export class MyBooksComponent implements OnInit {

  constructor(
    private bookService: BookService
  ) { }

  myBooks!: any

  ngOnInit(): void {
      this.bookService.getMyBooks().subscribe(
        (response) => {
          this.myBooks = response;
        }, 
        (error) => {
          console.log(error.error.message);
        }
      )
  }

}
