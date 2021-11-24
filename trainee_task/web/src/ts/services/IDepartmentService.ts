import {IDepartment} from "../models/IDepartment";
import {IService} from "./IService";
import jqXHR = JQuery.jqXHR;

export interface IDepartmentService extends IService {

    getAll(): jqXHR;

    createDepartment(department: IDepartment);
}