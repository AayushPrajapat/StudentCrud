// import { Component } from '@angular/core';
// import { Router } from '@angular/router';
// import { AuthService, User } from './auth.service';

// @Component({
//   selector: 'app-signup',
//   templateUrl: './signup.component.html',
//   styleUrls: ['./signup.component.css'],
// })
// export class SignupComponent {
//   user: User = {
//     name: '',
//     email: '',
//     password: '',
//   };

//   constructor(
//     private authService: AuthService,
//     private router: Router,
//   ) {}

//   signUp(): void {
//     this.authService.signUp(this.user).subscribe({
//       next: (response) => {
//         console.log('Signup successful:', response);

//         alert('Signup successful!');

//         this.router.navigate(['/login']);
//       },

//       error: (error) => {
//         console.error('Signup error:', error);

//         if (error.status === 409) {
//           alert('Email already registered.');
//         } else {
//           alert('Signup failed.');
//         }
//       },
//     });
//   }

//   goToLogin(): void {
//     this.router.navigate(['/login']);
//   }
// }

import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService, User } from './auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent {
  user: User = {
    name: '',
    email: '',
    password: '',
  };

  // Backend validation errors
  serverErrors: { [key: string]: string } = {};

  constructor(
    private authService: AuthService,
    private router: Router,
  ) {}

  signUp(): void {
    // Clear old errors
    this.serverErrors = {};

    this.authService.signUp(this.user).subscribe({
      next: (response: User) => {
        console.log('Signup successful:', response);

        alert('Signup successful!');

        this.router.navigate(['/login']);
      },

      error: (error: any) => {
        console.error('Signup error:', error);

        // Backend validation errors
        if (
          error?.error &&
          typeof error.error === 'object' &&
          !Array.isArray(error.error)
        ) {
          this.serverErrors = error.error;
        } else if (error.status === 409) {
          alert('Email already registered.');
        } else {
          alert('Signup failed.');
        }
      },
    });
  }

  goToLogin(): void {
    this.router.navigate(['/login']);
  }

  logout(): void {
    this.router.navigate(['/signup']);
  }
}


