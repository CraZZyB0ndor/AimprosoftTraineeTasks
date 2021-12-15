import {Component, OnInit} from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators
} from "@angular/forms";
import {IDepartment} from "../../../models/IDepartment";
import {DepartmentService} from "../../../services/department.service";
import {ActivatedRoute, Router} from "@angular/router";
import {RequestUtils} from "../../../utils/RequestUtils";
import {Observable} from "rxjs";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-department-form',
  templateUrl: './department-form.component.html',
  styleUrls: ['./department-form.component.scss']
})
export class DepartmentFormComponent implements OnInit {

  departmentForm: FormGroup = new FormGroup({});
  department?: IDepartment;
  action: string = 'created';

  constructor(private departmentService: DepartmentService, private activatedRoute: ActivatedRoute,
              private route: Router, private fb: FormBuilder, private createStatusMessage: MatSnackBar) {
    const id: number = RequestUtils.getNumber(this.activatedRoute.snapshot.paramMap.get('id'));
    if (id != null && !isNaN(id)) {
      this.getDepartmentById(id);
      this.action = "edited"
    }
  }

  ngOnInit(): void {
    this.departmentForm = this.fb.group({
      name: [null, [Validators.required, Validators.pattern(/^[A-zА-яїЇєЄіІґҐ]{2,20}$/)]]
    });
  }

  onSubmit(form: FormGroup) {
    if (form.valid) {
      this.getDepartmentByName(form.value.name).subscribe({
        next: (result: IDepartment) => {
          if (result != null && this.department != null && this.department.id != result.id) {
            form.get('name')?.setErrors({invalid: false});
          } else {
            this.createDepartment({
              id: this.department?.id,
              name: form.value.name
            });
          }
        },
        error: (error) => {
          console.log(error);
        }});
    }}

  validateField(formControlName: string) {
    const df = this.departmentForm.get(formControlName)!;
    return df.invalid && (df.dirty || df.touched);
  }

  getDepartmentById(id: number): void {
    this.departmentService.getDepartmentById(id).subscribe({
      next: (result) => {
        this.department = result;
        this.departmentForm.get('name')?.setValue(result.name);
      },
      error: (error) => {
        console.log(error);
      }
    });
  }

  createDepartment(department: IDepartment): void {
    this.departmentService.createDepartment(department).subscribe({
      next: (_) => {
        console.log(department);
        this.route.navigate(['departments']);
        this.createStatusMessage.open(`Department has been ${this.action}`, 'OK');
      },
      error: (error) => {
        this.createStatusMessage.open('Error is occurred!', 'OK');
        console.log(error);
      }
    });
  }

  getDepartmentByName(departmentName: string): Observable<any> {
    return this.departmentService.getDepartmentByName(departmentName);
  }
}
