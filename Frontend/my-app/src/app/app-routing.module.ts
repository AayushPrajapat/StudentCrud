import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './login.component';
import { SignupComponent } from './signup.component';
import { StudentComponent } from './student.component';

const routes: Routes = [

  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full',
  },

  {
    path: 'login',
    component: LoginComponent,
  },

  {
    path: 'signup',
    component: SignupComponent,
  },

  {
    path: 'student',
    component: StudentComponent,
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}