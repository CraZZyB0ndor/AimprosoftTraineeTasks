import {IContainer} from "../../IContainer";
import {EmployeeList} from "../../../components/impl/employee-list/EmployeeList";
import {EmployeeService} from "../../../services/impl/EmployeeService";

export class DisplayEmployee implements IContainer {

    private employeeService = new EmployeeService();
    private employeeList = new EmployeeList();

    getContent(param) {
        this.employeeService.getAll(param[0]).then(department => this.employeeList
                .render({department: department}),
            error => location.hash = "#departments"
        );
    }
}