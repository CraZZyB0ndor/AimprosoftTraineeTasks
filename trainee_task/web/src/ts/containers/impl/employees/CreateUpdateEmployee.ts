import {IContainer} from "../../IContainer";
import {EmployeeService} from "../../../services/impl/EmployeeService";
import {EmployeeForm} from "../../../components/impl/employee-form/EmployeeForm";

export class CreateUpdateEmployee implements IContainer {

    private employeeService = new EmployeeService();
    private employeeForm = new EmployeeForm();

    renderContent(param) {
        console.log(param)
        if (param.length === 2) {
            this.employeeService.getById(param[1]).then(employee => this.employeeForm
                .render({departmentId: param[0], employee: employee}),
                error => location.hash = '#notFound');
            return;
        }
        // location.hash = `#departments/${param[0]}/employees`
        this.employeeForm.render({departmentId: param[0]});
    }
}