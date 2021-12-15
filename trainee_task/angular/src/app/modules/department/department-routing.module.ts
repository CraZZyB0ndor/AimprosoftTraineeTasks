import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DepartmentComponent} from './department.component';
import {DepartmentFormComponent} from "./department-form/department-form.component";

//TODO() Create error page #404.
const routes: Routes = [
  {path: '', component: DepartmentComponent},
  {path: 'edit/:id', component: DepartmentFormComponent},
  {path: 'create', component: DepartmentFormComponent},
  {
    path: ':departmentId/employees',
    loadChildren: () => import('../employee/employee.module').then(m => m.EmployeeModule)
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DepartmentRoutingModule {
}
