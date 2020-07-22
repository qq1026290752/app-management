import { NgModule } from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {BaseComponent, FindComponent, LoginComponent, PhoneComponent, RegisterComponent} from './components';
const routes: Routes = [
  {
    path: '',
    component: BaseComponent,
    children:[
      {
        path:'login',
        component: LoginComponent,
      },
      {
        path: 'register',
        component: RegisterComponent
      },
      {
        path: 'phone',
        component:PhoneComponent
      },
      {
        path: 'find',
        component:FindComponent
      }

    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthenticationRoutingModule { }
