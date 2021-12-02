import {IEmployee} from "../models/IEmployee";
import {IService} from "./IService";
import jqXHR = JQuery.jqXHR;

export interface IEmployeeService extends IService {

    getAll(departmentId: number): jqXHR;
    getByEmail(email: string): jqXHR;
    createEmployee(employee: IEmployee);
}