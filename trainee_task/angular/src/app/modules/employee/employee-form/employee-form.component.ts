import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";
import {IEmployee} from "../../../models/IEmployee";
import {RequestUtils} from "../../../utils/RequestUtils";
import {EmployeeService} from "../../../services/employee.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-employee-form',
  templateUrl: './employee-form.component.html',
  styleUrls: ['./employee-form.component.scss']
})
export class EmployeeFormComponent implements OnInit {

  employeeForm: FormGroup = new FormGroup({});
  departmentId?: number;
  employee?: IEmployee;
  action: string = 'created';
  myFilter = (d: Date | null): boolean => {
    const date = (d || new Date());
    return date.getTime() <= new Date().getTime();
  };

  constructor(private employeeService: EmployeeService, private activatedRoute: ActivatedRoute,
              private route: Router, private fb: FormBuilder, private createStatusMessage: MatSnackBar) {
    this.departmentId = RequestUtils.getNumber(this.activatedRoute.snapshot.paramMap.get('departmentId'));
    const id: number = RequestUtils.getNumber(this.activatedRoute.snapshot.paramMap.get('id'));
    if (id != null && !isNaN(id)) {
      this.getEmployeeById(id);
      this.action = "edited"
    }
  }

  ngOnInit(): void {
    this.employeeForm = this.fb.group({
      name: [null, [Validators.required, Validators.pattern(/^[A-zА-яїЇєЄіІґҐ]{2,20}$/)]],
      email: [null, [Validators.required, Validators.email]],
      age: [null, [Validators.required, Validators.max(60), Validators.min(18)]],
      startWorkingDate: [null, [Validators.required]],
    });
  }

  onSubmit(form: FormGroup) {
    if (form.valid) {
      this.getEmployeeByEmail(form.value.email).subscribe({
        next: (result: IEmployee) => {
          if (result != null && (this.employee == null || (this.employee.id != result.id))) {
            form.get('email')?.setErrors({invalid: false});
          } else {
            this.createEmployee({
              id: this.employee?.id,
              department: {id: this.departmentId},
              name: form.value.name,
              email: form.value.email,
              age: form.value.age,
              startWorkingDate: form.value.startWorkingDate
            });
          }
        },
        error: (error) => {
          console.log(error);
        }
      });
    }
  }

  validateField(formControlName: string) {
    const df = this.employeeForm.get(formControlName)!;
    return df.invalid && (df.dirty || df.touched);
  }

  getEmployeeById(id: number): void {
    this.employeeService.getEmployeeById(id).subscribe({
      next: (result: IEmployee) => {
        this.employee = result;
        this.employeeForm.get('name')?.setValue(result.name);
        this.employeeForm.get('email')?.setValue(result.email);
        this.employeeForm.get('age')?.setValue(result.age);
        this.employeeForm.get('startWorkingDate')?.setValue(new Date(result.startWorkingDate!).toISOString().slice(0, 10));
      },
      error: (error) => {
        console.log(error);
      }
    });
  }

  createEmployee(employee: IEmployee): void {
    this.employeeService.createEmployee(employee).subscribe({
      next: (_) => {
        this.route.navigate(['departments/', this.departmentId, 'employees']);
        this.createStatusMessage.open(`Employee has been ${this.action}`, 'OK');
      },
      error: (error) => {
        this.createStatusMessage.open('Error is occurred!', 'OK');
        console.log(error);
      }
    });
  }

  getEmployeeByEmail(email: string): Observable<any> {
    return this.employeeService.getEmployeeByEmail(email);
  }
}
