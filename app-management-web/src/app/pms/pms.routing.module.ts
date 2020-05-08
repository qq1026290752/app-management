import { NgModule } from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {AclModuleComponent, BaseComponent, DashboardComponent} from "./components";
import {PmsGuard} from "../pms.guard";
const routes: Routes = [
  {
    path : "pms",
    component: BaseComponent,
    children:[
      {
        path:'dashboard',
        component: DashboardComponent
      },
      {
        path:'aclModule',
        component:AclModuleComponent
      }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  providers:[PmsGuard]
})
export class PmsRoutingModule { }
