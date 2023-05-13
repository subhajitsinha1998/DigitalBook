import { animate } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserAuthService } from '../_services/user-auth.service';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit { 
  constructor(
    private userService: UserService,
    private userAuthService: UserAuthService,
    private router: Router
  ) { }
  signUpForm!: FormGroup;

  ngOnInit(): void {
    this.signUpForm = new FormGroup({
      'fullname': new FormControl(null, [Validators.required]),
      'email': new FormControl(null, [Validators.required, Validators.email]),
      'password': new FormControl(null, [this.validPassword]),
      'confirmPassword': new FormControl(null, [this.confirmPassword.bind(this)]),
      'role': new FormControl('READER')
    })
  }

  showValidationError(forInput: string) {
    return !this.signUpForm.get(forInput)?.valid && this.signUpForm.get(forInput)?.touched;
  }

  validPassword(control: AbstractControl): {[s: string]: boolean}|null {
    var validatorRegex=  /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\s).{8,15}$/;
      if(!control.value || !control.value.match(validatorRegex)){
        return {'InvalidPassword': true};
      }
      return null;
  }

  confirmPassword(control: AbstractControl): {[s: string]: boolean}|null {
    if(!control.value || control.value !== this.signUpForm.get('password')?.value){
      return {'ConfirmationPasswordNotMatch': true};
    }
    return null;
  }

  signUp() {
    delete this.signUpForm.value.confirmPassword
    this.userService.signUp(this.signUpForm.value).subscribe(
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
