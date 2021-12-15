import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {EmployeeFormComponent} from "./employee-form/employee-form.component";
import {EmployeeComponent} from "./employee.component";

const routes: Routes = [
  {path: '', component: EmployeeComponent},
  {path: 'edit/:id', component: EmployeeFormComponent},
  {path: 'create', component: EmployeeFormComponent}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EmployeeRoutingModule {
}
