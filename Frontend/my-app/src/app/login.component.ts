// import { Component } from '@angular/core';
// import { Router } from '@angular/router';
// import { AuthService, LoginRequest } from './auth.service';

// @Component({
//   selector: 'app-login',
//   templateUrl: './login.component.html',
//   styleUrls: ['./login.component.css']
// })
// export class LoginComponent {

//   loginRequest: LoginRequest = {
//     email: '',
//     password: ''
//   };

//   constructor(
//     private authService: AuthService,
//     private router: Router
//   ) {}

//   login(): void {

//     this.authService.login(this.loginRequest).subscribe({

//       next: (response) => {

//         console.log('Login response:', response);

//         if (response === 'User Login SuccessFully..!!') {

//           alert('Login successful!');

//           // For now
//           // We will connect this to the student page later.
//           this.router.navigate(['/login']);

//         } else {

//           alert(response);
//         }
//       },

//       error: (error) => {

//         console.error('Login error:', error);

//         alert('Login failed.');
//       }

//     });
//   }

//   goToSignup(): void {
//     this.router.navigate(['/signup']);
//   }
// }

/*
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService, LoginRequest } from './auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  loginRequest: LoginRequest = {
    email: '',
    password: '',
  };

  serverErrors: { [key: string]: string } = {};

  loginError: string = '';

  constructor(
    private authService: AuthService,
    private router: Router,
  ) {}

  login(): void {
    // Clear previous errors
    this.serverErrors = {};
    this.loginError = '';

    this.authService.login(this.loginRequest).subscribe({
      next: (response) => {
        console.log('Login response:', response);

        if (response === 'User Login SuccessFully..!!') {
          alert('Login successful!');

          this.router.navigate(['/student']);
        } else {
          // Backend login error
          this.loginError = response;
        }
      },

      error: (error) => {
        console.error('Login error:', error);
        console.error('Backend response:', error?.error);

        // Backend validation errors
        if (
          error?.error &&
          typeof error.error === 'object' &&
          !Array.isArray(error.error)
        ) {
          this.serverErrors = error.error;
        } else {
          this.loginError = 'Login failed. Please try again.';
        }
      },
    });
  }

  goToSignup(): void {
    this.router.navigate(['/signup']);
  }
}
*/

/*
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService, LoginRequest } from './auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  loginRequest: LoginRequest = {
    email: '',
    password: '',
  };

  serverErrors: { [key: string]: string } = {};

  loginError: string = '';

  constructor(
    private authService: AuthService,
    private router: Router,
  ) {}

  login(): void {
    // Clear old errors
    this.serverErrors = {};
    this.loginError = '';

    this.authService.login(this.loginRequest).subscribe({
      next: (response: string) => {
        console.log('Login response:', response);

        if (response === 'User Login SuccessFully..!!') {
          alert('Login successful!');

          this.router.navigate(['/student']);
        } else {
          // These are responses from UserService
          // Wrong Password / Email Not Found
          this.loginError = response;
        }
      },

      error: (error: any) => {
        console.log('HTTP status:', error.status);
        console.log('Complete error:', error);
        console.log('Backend error:', error.error);

        /*
         * Backend validation response:
         *
         * {
         *   "email": "Email is required",
         *   "password": "Password is required"
         * }
         

        if (error.status === 400 && error.error) {
          this.serverErrors = error.error;
        } else {
          this.loginError = 'Something went wrong. Please try again.';
        }
      },
    });
  }

  goToSignup(): void {
    this.router.navigate(['/signup']);
  }
}*/

/*
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService, LoginRequest } from './auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  loginRequest: LoginRequest = {
    email: '',
    password: '',
  };

  serverErrors: { [key: string]: string } = {};

  loginError: string = '';

  constructor(
    private authService: AuthService,
    private router: Router,
  ) {}

  login(): void {
    console.log('LOGIN BUTTON CLICKED');

    console.log('Login data:', this.loginRequest);

    this.serverErrors = {};
    this.loginError = '';

    this.authService.login(this.loginRequest).subscribe({
      next: (response: string) => {
        console.log('BACKEND SUCCESS RESPONSE:', response);

        if (response === 'User Login SuccessFully..!!') {
          alert('Login successful!');

          this.router.navigate(['/student']);
        } else {
          console.log('BACKEND LOGIN MESSAGE:', response);

          this.loginError = response;
        }
      },

      error: (error: any) => {
        console.log('BACKEND ERROR:', error);
        console.log('HTTP STATUS:', error.status);
        console.log('ERROR BODY:', error.error);

        if (error.status === 400) {
          this.serverErrors = error.error;

          console.log('SERVER VALIDATION ERRORS:', this.serverErrors);
        } else {
          this.loginError = 'Something went wrong. Please try again.';
        }
      },
    });
  }

  goToSignup(): void {
    this.router.navigate(['/signup']);
  }
}*/

import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService, LoginRequest } from './auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  loginRequest: LoginRequest = {
    email: '',
    password: '',
  };

  serverErrors: { [key: string]: string } = {};

  loginError: string = '';

  constructor(
    private authService: AuthService,
    private router: Router,
  ) {}

  login(): void {
    // Remove previous validation errors
    this.serverErrors = {};

    // Remove previous login error
    this.loginError = '';

    this.authService.login(this.loginRequest).subscribe({
      next: (response: string) => {
        if (response === 'User Login SuccessFully..!!') {
          alert('Login successful!');

          this.router.navigate(['/student']);
        } else {
          // Wrong Password / Email Not Found
          this.loginError = response;
        }
      },

      error: (error: any) => {
        console.log('Backend error:', error.error);

        let backendErrors = error.error;

        // In case Angular gives the JSON response as a string
        if (typeof backendErrors === 'string') {
          try {
            backendErrors = JSON.parse(backendErrors);
          } catch {
            // Do nothing
          }
        }

        // Backend validation errors
        if (
          error.status === 400 &&
          backendErrors &&
          typeof backendErrors === 'object'
        ) {
          this.serverErrors = backendErrors;
        } else {
          this.loginError = 'Login failed. Please try again.';
        }
      },
    });
  }

  goToSignup(): void {
    this.router.navigate(['/signup']);
  }
}
