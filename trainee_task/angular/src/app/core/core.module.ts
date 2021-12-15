import { NgModule } from '@angular/core';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {BrowserModule} from "@angular/platform-browser";
import { MainLayoutComponent } from './layout/main-layout/main-layout.component';
import {RouterModule} from "@angular/router";
import {SharedModule} from "../shared/shared.module";
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatDividerModule} from "@angular/material/divider";
import {MatListModule} from "@angular/material/list";

@NgModule({
  declarations: [
    MainLayoutComponent
  ],
  imports: [
    // Vendor.
    BrowserModule,
    BrowserAnimationsModule,
    RouterModule,
    SharedModule,
    MatToolbarModule,
    MatDividerModule,
    MatListModule,
  ],
  exports: [
    MainLayoutComponent
  ]
})
export class CoreModule { }
