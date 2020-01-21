import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './components';
import {PmsRoutingModule} from "./pms.routing.module";



@NgModule({
  declarations: [LoginComponent],
  imports: [
    CommonModule
  ],
  exports:[
    PmsRoutingModule
  ]
})
export class PmsModule { }
