 import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { RouterModule } from "@angular/router";
import { HttpClientModule } from '@angular/common/http'
import { AppRoutingModule} from './app-routing.module'
import { ShareModule } from './share'
import { AuthenticationModule } from "./authentication";
import { ElModule } from "element-angular/release/element-angular.module";
import { PmsModule } from "./pms";


@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule,
    AppRoutingModule,
    HttpClientModule,
    ShareModule,
    AuthenticationModule,
    PmsModule,
    ElModule.forRoot(),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
