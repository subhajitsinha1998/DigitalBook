import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { BookService } from '../_services/book.service';
import { UserAuthService } from '../_services/user-auth.service';

@Component({
  selector: 'app-edit-book',
  templateUrl: './edit-book.component.html',
  styleUrls: ['./edit-book.component.css']
})
export class EditBookComponent implements OnInit {

  constructor(
    private router: Router,
    private bookService: BookService,
    private userAuthService: UserAuthService
  ) {
    this.bookDetails = this.router.getCurrentNavigation()!.extras.state;
    if (this.bookDetails === undefined) {
      this.router.navigateByUrl('author/mybooks')
    }
  }

  bookDetails: any;
  bookUpdateForm!: FormGroup;

  ngOnInit(): void {
    this.bookUpdateForm = new FormGroup({
      'bookId': new FormControl(this.bookDetails.id),
      'title': new FormControl(this.bookDetails.title),
      'category': new FormControl(this.bookDetails.category),
      'image': new FormControl(this.bookDetails.image),
      'price': new FormControl(this.bookDetails.price),
      'publisher': new FormControl(this.bookDetails.publisher),
      'content': new FormControl(this.bookDetails.content)
    })
    this.bookUpdateForm.get('bookId')?.disable()
  }

  updateBook() {
    this.bookDetails.title = this.bookUpdateForm.value.title
    this.bookDetails.category = this.bookUpdateForm.value.category
    this.bookDetails.image = this.bookUpdateForm.value.image
    this.bookDetails.price = this.bookUpdateForm.value.price
    this.bookDetails.publisher = this.bookUpdateForm.value.publisher
    this.bookDetails.content = this.bookUpdateForm.value.content
    this.bookService.updateBook(this.bookDetails).subscribe(
      (response) => {
        alert(`Book id ${this.bookDetails.id} is successfully updated.\nYou will be redirected to your books`)
        this.router.navigateByUrl('author/mybooks')
      },
      (error) => {
        alert(`something went wrong!\nBook not updated`)
        console.log(error.error.message)
      }
    )
  }

}
