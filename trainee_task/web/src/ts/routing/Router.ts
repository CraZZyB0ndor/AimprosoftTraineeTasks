import "../components/impl/department-list/DepartmentList.css";
import {IContainer} from "../containers/IContainer";
import {DisplayDepartments} from "../containers/impl/department/DisplayDepartments";
import {DisplayEmployee} from "../containers/impl/employee/DisplayEmployee";
import {CreateUpdateDepartment} from "../containers/impl/department/CreateUpdateDepartment";

export class Router {

    private routers: Map<string, IContainer>;

    constructor() {
        this.routers = new Map();
        this.routers.set("#department", new DisplayDepartments());
        this.routers.set("#employee", new DisplayEmployee());
        this.routers.set("#department-form", new CreateUpdateDepartment());
    }

    public getRoute(hash: string): IContainer {
        if (this.routers.has(hash)) {
            location.hash = hash;
            return this.routers.get(hash);
        }
        location.hash = "#department";
        return new DisplayDepartments();
    }
}