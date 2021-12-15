import {Component, OnInit} from '@angular/core';
import {IDepartment} from "../../../models/IDepartment";
import {DepartmentService} from "../../../services/department.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";

@Component({
  selector: 'app-department-list',
  templateUrl: './department-list.component.html',
  styleUrls: ['./department-list.component.scss']
})
export class DepartmentListComponent implements OnInit {

  departments: IDepartment[] = [];
  displayedColumns: string[] = ['id', 'name', 'actions'];

  constructor(private departmentService: DepartmentService, private deleteStatusMessage: MatSnackBar,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getDepartments();
  }

  navigateToDepartmentForm(department: IDepartment): void {
    this.router.navigate(['departments/edit', department.id]);
  }

  navigateToEmployeeList(department: IDepartment) {
    this.router.navigate(['departments/', department.id, 'employees']);
  }

  getDepartments(): void {
    this.departmentService.getAllDepartments().subscribe({
      next: (result) => {
        this.departments = result;
      },
      error: (error) => {
        console.log(error);
      }
    });
  }

  deleteDepartmentById(department: IDepartment): void {
    this.departmentService.deleteDepartmentById(department).subscribe({
      next: (_) => {
        this.deleteStatusMessage.open(`Department [${department.name}] has been deleted!`, "OK");
        this.ngOnInit();
      },
      error: (error) => {
        this.deleteStatusMessage.open(`You cannot delete department ${department.name},
         because error is occurred!`);
        console.log(error);
      }
    });
  }
}
