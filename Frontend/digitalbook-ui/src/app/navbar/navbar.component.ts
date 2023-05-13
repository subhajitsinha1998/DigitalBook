import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserAuthService } from '../_services/user-auth.service';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  constructor(
    private userAuthService: UserAuthService,
    private router: Router,
    private userService: UserService
  ) { }

  public isSignedIn() {
    return this.userAuthService.isSignedIn();
  }

  public signOut() {
    this.userAuthService.signOut();
    this.router.navigate(['/']);
  }

  public allowedRoles(roles: string[]): boolean {
    return this.userService.matchRole(roles);
  }
}
