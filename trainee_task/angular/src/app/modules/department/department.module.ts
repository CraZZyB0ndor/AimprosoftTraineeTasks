import {NgModule} from '@angular/core';

import {DepartmentRoutingModule} from './department-routing.module';
import {DepartmentComponent} from './department.component';
import {SharedModule} from "../../shared/shared.module";
import {DepartmentListComponent} from './department-list/department-list.component';
import {DepartmentFormComponent} from './department-form/department-form.component';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from "@angular/material/input";
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    DepartmentComponent,
    DepartmentListComponent,
    DepartmentFormComponent
  ],
    imports: [
        SharedModule,
        DepartmentRoutingModule,
        MatProgressSpinnerModule,
        MatSnackBarModule,
        MatFormFieldModule,
        MatInputModule,
        ReactiveFormsModule
    ]
})
export class DepartmentModule {
}
