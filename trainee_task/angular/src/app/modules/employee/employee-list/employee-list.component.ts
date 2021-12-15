import {Component, OnInit} from '@angular/core';
import {IEmployee} from "../../../models/IEmployee";
import {EmployeeService} from "../../../services/employee.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {ActivatedRoute, Router} from "@angular/router";
import {RequestUtils} from "../../../utils/RequestUtils";
import {IDepartment} from "../../../models/IDepartment";

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.scss']
})
export class EmployeeListComponent implements OnInit {

  departmentId?: number;
  department?: IDepartment;
  displayedColumns: string[] = ['id', 'name', 'email', 'age', 'startWorkingDate', 'actions'];

  constructor(private employeeService: EmployeeService, private deleteStatusMessage: MatSnackBar,
              private router: Router, private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.departmentId = RequestUtils.getNumber(this.activatedRoute.snapshot.paramMap.get('departmentId'));
    if (this.departmentId != null && !isNaN(this.departmentId)) {
      this.getEmployees();
    }
    //TODO() Navigate to error page.
  }

  navigateToEmployeeForm(employee: IEmployee): void {
    this.router.navigate([`departments/${this.departmentId}/employees/edit/${employee.id}`]);
  }

  correctDate(date: Date): string {
    return new Date(date).toISOString().slice(0, 10);
  }

  getEmployees(): void {
    this.employeeService.getAllEmployeesByDepartmentId(this.departmentId!).subscribe({
      next: (result) => {
        this.department = result;
      },
      error: (error) => {
        //TODO() Navigate to error page.
        console.log(error);
      }
    });
  }

  deleteEmployeeById(employee: IEmployee): void {
    this.employeeService.deleteEmployeeById(employee).subscribe({
      next: (_) => {
        this.deleteStatusMessage.open(`Employee [${employee.email}] has been deleted!`, "OK");
        this.ngOnInit();
      },
      error: (error) => {
        this.deleteStatusMessage.open(`You cannot delete employee [${employee.email}],
         because error is occurred!`);
        console.log(error);
      }
    });
  }
}
