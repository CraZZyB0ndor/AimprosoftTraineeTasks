import {IContainer} from "../../IContainer";
import {DepartmentForm} from "../../../components/impl/department-form/DepartmentForm";
import {DepartmentValidation} from "../../../validation/department/DepartmentValidation";
import {IDepartment} from "../../../models/IDepartment";
import {RequestUtils} from "../../../utils/RequestUtils";
import {DepartmentService} from "../../../services/impl/DepartmentService";
import {Routers} from "../../../routing/Routers";

export class CreateUpdateDepartment implements IContainer {

    private departmentService = new DepartmentService();
    private departmentForm = new DepartmentForm();
    private validator = new DepartmentValidation();

    getContent(param) {

        this.departmentForm.render(param);

        $('#create-department-form-id').on('submit', (event) => {
                event.preventDefault();
                const department: IDepartment = {
                    id: RequestUtils.getNumber($("input[name=id]").val()),
                    employees: null,
                    name: RequestUtils.getString($("input[name=department-name]").val())
                };
                if (this.validator.validate(department)) {
                    this.departmentService.createDepartment(department).done(() => {
                        Routers.searchHash('#department');
                    });
                    // Routers.searchHash('#department');
                    console.log('OK');
                }
            }
        );
    }
}