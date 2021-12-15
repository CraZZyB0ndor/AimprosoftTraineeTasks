import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {catchError, Observable, of, tap} from "rxjs";
import {DEV_URL} from "../constants/constantUrl";
import {IEmployee} from "../models/IEmployee";

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private http: HttpClient) {
  }

  getEmployeeById(id: number): Observable<any> {
    return this.http.get<any>(DEV_URL + `/employees/id/${id}`)
      .pipe(
        tap(_ => true),
        catchError(this.handleError('Get employee by ID', []))
      );
  }

  getAllEmployeesByDepartmentId(departmentId: number): Observable<any> {
    return this.http.get<any>(DEV_URL + `/employees/${departmentId}`)
      .pipe(
        tap(_ => true),
        catchError(this.handleError('Get employees', []))
      );
  }

  createEmployee(employee: IEmployee): Observable<any> {
    return this.http.post<any>(DEV_URL + '/employees', employee)
      .pipe(
        tap(_ => true),
        catchError(this.handleError('Create employee', []))
      );
  }

  getEmployeeByEmail(email: string): Observable<any> {
    return this.http.get<any>(DEV_URL + '/employees/exist', {params: {email: email}})
      .pipe(
        tap(_ => true),
        catchError(this.handleError('Get employee by email', []))
      );
  }

  deleteEmployeeById(employee: IEmployee): Observable<any> {
    return this.http.delete<any>(DEV_URL + '/employees', {body: employee})
      .pipe(
        tap(_ => true),
        catchError(this.handleError('Delete employee', []))
      );
  }

  private handleError<T>(operation = 'operation', result?: T): any {
    return (error: any): Observable<T> => {
      console.log(error.message);
      console.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }
}
