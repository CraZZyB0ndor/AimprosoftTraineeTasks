import {IContainer} from "../../IContainer";
import {DepartmentForm} from "../../../components/impl/department-form/DepartmentForm";
import {DepartmentService} from "../../../services/impl/DepartmentService";

export class CreateUpdateDepartment implements IContainer {

    private departmentService = new DepartmentService();
    private departmentForm = new DepartmentForm();

    getContent(param) {
        if (param != '') {
            this.departmentService.getById(param[0]).then(department => this.departmentForm.render(department),
                error => location.hash = "#departments");
            return;
        }
        this.departmentForm.render(null);
    }
}