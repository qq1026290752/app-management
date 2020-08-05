import {NgModule, Optional, SkipSelf} from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule,ReactiveFormsModule } from '@angular/forms'
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ElModule} from "element-angular/release/element-angular.module";
import {DomSanitizer} from "@angular/platform-browser";
import {
  HeaderComponent,
  FooterComponent,
  SidebarComponent
} from "./componentes";
import {AppRoutingModule} from "../app-routing.module";
import {loadIconResources} from "../utils";
import {
  MatListModule,
  MatButtonModule,
  MatCardModule,
  MatSidenavModule,
  MatToolbarModule,
  MatFormFieldModule,
  MatInputModule,
  MatSlideToggleModule,
  MatIconModule,
  MatIconRegistry,
  MatSelectModule,
  MatExpansionModule,
  MatTableModule
} from "@angular/material";

@NgModule({
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
    ElModule,
    MatSelectModule,
    MatListModule,
    MatToolbarModule,
    MatTableModule,
    ReactiveFormsModule,
  ],
  exports: [
    CommonModule,
    AppRoutingModule,
    MatSidenavModule,
    MatExpansionModule,
    MatCardModule,
    MatSelectModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    FormsModule,
    MatSlideToggleModule,
    BrowserAnimationsModule,
    ElModule,
    HeaderComponent,
    FooterComponent,
    SidebarComponent,
    MatListModule,
    MatToolbarModule,
    MatTableModule,
    ReactiveFormsModule,
  ],
  declarations: [HeaderComponent, FooterComponent, SidebarComponent]
})
export class ShareModule {
  constructor(@Optional() @SkipSelf() parent :ShareModule,ir:MatIconRegistry,ds:DomSanitizer) {
    if(parent){
      throw new Error('核心模块，只需要加载一次')
    }
    loadIconResources(ir,ds);
  }
}
