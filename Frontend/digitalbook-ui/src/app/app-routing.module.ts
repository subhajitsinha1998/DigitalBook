import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SearchbookComponent } from './searchbook/searchbook.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { AddbookComponent } from './addbook/addbook.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { AuthGuard } from './_auth/auth.guard';
import { MyBooksComponent } from './my-books/my-books.component';
import { SubscriptionsComponent } from './subscriptions/subscriptions.component';
import { HomeComponent } from './home/home.component';
import { ReadSubscribedBookComponent } from './read-subscribed-book/read-subscribed-book.component';
import { EditBookComponent } from './edit-book/edit-book.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'search', component: SearchbookComponent},
  {path: 'sign-up', component: SignUpComponent},
  {path: 'sign-in', component: SignInComponent},
  {path: 'forbidden', component: ForbiddenComponent},
  {
    path: 'author', children : [
      {path: 'addbook', component: AddbookComponent},
      {path: 'mybooks', component: MyBooksComponent},
      {path: 'mybooks/edit', component: EditBookComponent}
    ], canActivate: [AuthGuard], data: {roles:['author']}
  },
  {
    path: 'reader', children : [
      {path: 'subscriptions', component: SubscriptionsComponent},
      {path: 'subscriptions/read', component: ReadSubscribedBookComponent}
    ], canActivate: [AuthGuard], data: {roles:['author', 'reader']}
  },
  {path: '**', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
