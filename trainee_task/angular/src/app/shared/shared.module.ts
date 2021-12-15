import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from "@angular/router";
import {MatButtonModule} from "@angular/material/button";
import {MatTableModule} from "@angular/material/table";
import {MatIconModule} from '@angular/material/icon'

@NgModule({
  declarations: [],
  imports: [
    // Vendor.
    CommonModule,
    RouterModule,

    // Angular material.
    MatTableModule,
    MatButtonModule
  ],
  exports: [
    // Vendor.
    CommonModule,
    RouterModule,

    // Angular material.
    MatTableModule,
    MatButtonModule,
    MatIconModule
  ]
})
export class SharedModule {
}
