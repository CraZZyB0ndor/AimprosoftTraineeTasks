import {DataService} from "./DataService";
import {IEmployeeService} from "../IEmployeeService";
import {IEmployee} from "../../models/IEmployee";
import jqXHR = JQuery.jqXHR;

export class EmployeeService implements IEmployeeService {

    getAll(id: number): jqXHR {
        return DataService.get(`/api/employees/${id}`)
    }

    getById(id: number): jqXHR {
        return DataService.get(`/api/employees/id/${id}`)
    }

    getByEmail(email: string): jqXHR {
        return DataService.get(`/api/employee/email/${email}`)
    }

    createEmployee(employee: IEmployee): jqXHR {
        return DataService.create('/api/employees', employee);
    }

    deleteById(id: number): jqXHR {
        return DataService.delete('/api/employees', {'id': id});
    }
}