import {IContainer} from "../../IContainer";
import {EmployeeService} from "../../../services/impl/EmployeeService";
import {EmployeeForm} from "../../../components/impl/employee-form/EmployeeForm";
import {DepartmentService} from "../../../services/impl/DepartmentService";

export class CreateUpdateEmployee implements IContainer {

    private employeeService = new EmployeeService();
    private departmentService = new DepartmentService();
    private employeeForm = new EmployeeForm();

    getContent(param) {
        if (param.length === 3) {
            this.employeeService.getById(param[0]).then(employee => this.employeeForm
                .render({departmentId: param[2], employee: employee}),
                error => location.hash = `#employees/${param[2]}`);
            return;
        }
        this.employeeForm.render({departmentId: param[0]});
    }
}