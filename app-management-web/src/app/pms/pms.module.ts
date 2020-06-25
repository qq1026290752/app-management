import { NgModule } from '@angular/core';
import {ShareModule} from "../share";
import { DashboardComponent,BaseComponent } from './components';
import {PmsRoutingModule} from "./pms.routing.module";
import {AclModuleComponent } from './components';




@NgModule({
  declarations: [BaseComponent, DashboardComponent, AclModuleComponent],
    imports: [
        ShareModule,
    ],
  exports:[
    PmsRoutingModule
  ]
})
export class PmsModule { }
