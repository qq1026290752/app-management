import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {AppRoutingModule} from "../app-routing.module";
import {MatSidenavModule} from "@angular/material";
import {MatCardModule} from "@angular/material/card";
import {MatButtonModule} from "@angular/material/button";
import { FormsModule } from '@angular/forms'
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatIconModule} from "@angular/material/icon";
import {MatSlideToggleModule} from "@angular/material/slide-toggle";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ElColModule} from "element-angular/release/col/module";
import {ElRowModule} from "element-angular/release/row/module";


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    AppRoutingModule,
    MatSidenavModule,
    MatCardModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    FormsModule,
    MatSlideToggleModule,
    BrowserAnimationsModule,
    ElColModule,
    ElRowModule
  ],
  exports: [
    CommonModule,
    AppRoutingModule,
    MatSidenavModule,
    MatCardModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    FormsModule,
    MatSlideToggleModule,
    BrowserAnimationsModule,
    ElColModule,
    ElRowModule
  ]
})
export class ShareModule { }
