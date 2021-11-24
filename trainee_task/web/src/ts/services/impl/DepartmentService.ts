import jqXHR = JQuery.jqXHR;
import {Dao} from "../../dao/Dao";
import {IDepartment} from "../../models/IDepartment";
import {IDepartmentService} from "../IDepartmentService";

export class DepartmentService implements IDepartmentService {

    getAll(): jqXHR {
        return Dao.get('/department');
    }

    getById(id: number): JQuery.jqXHR {
        return Dao.get('/department/id', {'id': id});
    }

    createDepartment(department: IDepartment) {
        return Dao.create('/department', department);
    }

    deleteById(id: number) {
        Dao.delete('/department', {'id': id})
    }
}