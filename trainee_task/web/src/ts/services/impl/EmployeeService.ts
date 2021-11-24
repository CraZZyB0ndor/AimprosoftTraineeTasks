import {Dao} from "../../dao/Dao";
import {IEmployeeService} from "../IEmployeeService";
import {IEmployee} from "../../models/IEmployee";
import jqXHR = JQuery.jqXHR;

export class EmployeeService implements IEmployeeService {
    getAll(id: number): jqXHR {
        return Dao.get('/employee', {'departmentId': id})
    }

    getById(id: number): jqXHR {
        return Dao.get('/employee/id', {'id': id})
    }

    createEmployee(employee: IEmployee) {
        Dao.create('/employee', employee);
    }

    deleteById(id: number) {
        Dao.delete('/employee', {'id': id})
    }
}