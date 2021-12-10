import {IContainer} from "../../IContainer";
import {DepartmentService} from "../../../services/impl/DepartmentService";
import {DepartmentList} from "../../../components/impl/department-list/DepartmentList";


export class DisplayDepartments implements IContainer {

    private departmentService = new DepartmentService();
    private departmentList = new DepartmentList();

    renderContent(param) {
        this.departmentService.getAll().then(departments => this.departmentList.render(departments),
            error => location.hash = "#departments"
            );
    }
}