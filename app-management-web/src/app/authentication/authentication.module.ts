import { NgModule } from '@angular/core';
import {
  LoginComponent,
  BaseComponent,
  RegisterComponent,
  AuthComponent,
  PhoneComponent,
  FindComponent
} from './components';
import { ShareModule } from "../share";
import { AuthenticationRoutingModule } from "./authentication.routing.module";
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    LoginComponent
    , BaseComponent
    , RegisterComponent
    , AuthComponent
    , PhoneComponent
    , FindComponent
  ],
  imports: [
        ShareModule,
        ReactiveFormsModule
  ],
  exports:[
    AuthenticationRoutingModule
  ]
})
export class AuthenticationModule { }
