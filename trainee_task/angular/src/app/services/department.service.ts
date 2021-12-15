import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {catchError, Observable, of, tap} from "rxjs";
import {DEV_URL} from "../constants/constantUrl";
import {IDepartment} from "../models/IDepartment";

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {

  constructor(private http: HttpClient) {
  }

  getDepartmentById(id: number): Observable<any> {
    return this.http.get<any>(DEV_URL + `/departments/id/${id}`)
      .pipe(
        tap(_ => true),
        catchError(this.handleError('Get department by ID', []))
      );
  }

  getAllDepartments(): Observable<any> {
    return this.http.get<any>(DEV_URL + '/departments')
      .pipe(
        tap(_ => true),
        catchError(this.handleError('Get departments', []))
      );
  }

  createDepartment(department: IDepartment): Observable<any> {
    return this.http.post<any>(DEV_URL + '/departments', department)
      .pipe(
        tap(_ => true),
        catchError(this.handleError('Create department', []))
      );
  }

  getDepartmentByName(departmentName: string): Observable<any> {
    return this.http.get<any>(DEV_URL + '/departments/exist', {params: {departmentName: departmentName}})
      .pipe(
        tap(_ => true),
        catchError(this.handleError('Get department by department name', []))
      );
  }

  deleteDepartmentById(department: IDepartment): Observable<any> {
    return this.http.delete<any>(DEV_URL + '/departments', {body: department})
      .pipe(
        tap(_ => true),
        catchError(this.handleError('Delete department', []))
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
