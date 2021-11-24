import {IContainer} from "../../IContainer";
import {EmployeeList} from "../../../components/impl/employee-list/EmployeeList";

export class DisplayEmployee implements IContainer {

    private employeeList = new EmployeeList();

    getContent(param) {

        this.employeeList.render(param);
        // Get data from DB or from Department via [param].
        // this.departmentService.getById(param.id).done((result) => this.employeeList.render(result.employees))
    }
}