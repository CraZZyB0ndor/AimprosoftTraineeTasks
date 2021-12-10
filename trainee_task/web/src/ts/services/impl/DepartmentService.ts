import jqXHR = JQuery.jqXHR;
import {DataService} from "./DataService";
import {IDepartment} from "../../models/IDepartment";
import {IDepartmentService} from "../IDepartmentService";

export class DepartmentService implements IDepartmentService {

    getAll(): jqXHR {
        return DataService.get('/api/departments');
    }

    getById(id: number): jqXHR {
        return DataService.get(`/api/departments/id/${id}`);
    }

    getByName(name: string): jqXHR {
        return DataService.get(`/api/departments/name/${name}`);
    }

    createDepartment(department: IDepartment): jqXHR {
        return DataService.create('/api/departments', department);
    }

    deleteById(id: number): jqXHR {
        return DataService.delete('/api/departments', {'id': id})
    }
}