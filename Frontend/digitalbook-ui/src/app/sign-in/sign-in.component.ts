import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { UserAuthService } from '../_services/user-auth.service';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent {

  constructor(
    private userService: UserService,
    private userAuthService: UserAuthService,
    private router: Router
  ) { }

  ngOnInit(): void {

  }

  signIn(signInForm: NgForm) {
    this.userService.signIn(signInForm.value).subscribe(
      (response: any) => {
        this.userAuthService.setToken(response.jwtToken);
        this.userAuthService.setUserDetails(response.user);
        const role = response.user.role;
        if (role === 'AUTHOR') {
          this.router.navigate(['/']);
        } else {
          this.router.navigate(['/']);
        }
      },
      (error) => {
        alert(error.error.message);
      }
    );
  }

}
