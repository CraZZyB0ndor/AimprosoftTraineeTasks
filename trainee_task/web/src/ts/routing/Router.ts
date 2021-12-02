import "../components/impl/style/list-style.css";
import {IContainer} from "../containers/IContainer";
import {DisplayDepartments} from "../containers/impl/department/DisplayDepartments";
import {DisplayEmployee} from "../containers/impl/employee/DisplayEmployee";
import {CreateUpdateDepartment} from "../containers/impl/department/CreateUpdateDepartment";
import {CreateUpdateEmployee} from "../containers/impl/employee/CreateUpdateEmployee";

export class Router {

    private routers: Map<string, IContainer>;

    constructor() {
        this.routers = new Map();
        this.routers.set("#departments", new DisplayDepartments());
        this.routers.set("#employees", new DisplayEmployee());
        this.routers.set("#department", new CreateUpdateDepartment());
        this.routers.set("#employee", new CreateUpdateEmployee());
    }

    public getRoute(hash: string): IContainer {
        if (this.routers.has(hash)) {
            return this.routers.get(hash);
        }
        return new DisplayDepartments();
    }
}