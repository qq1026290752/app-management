import { NgModule } from '@angular/core';
import { LoginComponent,BaseComponent,RegisterComponent } from './components';
import { ShareModule } from "../share";
import { AuthenticationRoutingModule } from "./authentication.routing.module";
import { AuthComponent } from './components/auth/auth.component';
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [LoginComponent, BaseComponent, RegisterComponent, AuthComponent],
    imports: [
        ShareModule,
        ReactiveFormsModule
    ],
  exports:[
    AuthenticationRoutingModule
  ]
})
export class AuthenticationModule { }
