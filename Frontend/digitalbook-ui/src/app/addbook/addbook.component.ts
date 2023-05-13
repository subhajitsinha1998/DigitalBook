import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { BookService } from '../_services/book.service';
import { UserAuthService } from '../_services/user-auth.service';

@Component({
  selector: 'app-addbook',
  templateUrl: './addbook.component.html',
  styleUrls: ['./addbook.component.css']
})
export class AddbookComponent {

  constructor(
    private bookService: BookService,
    private userAuthService: UserAuthService
  ) { }

  createBook(createBookForm: NgForm) {
    let bookData = createBookForm.value;
    bookData.active = true;
    bookData.author = this.userAuthService.getFullName();
    this.bookService.addBook(bookData).subscribe(
      (response)=>{
        alert('Book created successfully')
        createBookForm.reset()
      },
      (error)=>{
        alert(error.error.message)
      }
    )
  }

}
