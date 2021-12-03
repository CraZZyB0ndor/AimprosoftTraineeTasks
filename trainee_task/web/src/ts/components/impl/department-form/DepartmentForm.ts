import {IComponent} from "../../IComponent";
import {AppConst} from "../../../const/AppConst";
import "../style/form-style.css";
import {IDepartment} from "../../../models/IDepartment";
import {RequestUtils} from "../../../utils/RequestUtils";
import {DepartmentService} from "../../../services/impl/DepartmentService";
import {DepartmentValidation} from "../../../validation/department/DepartmentValidation";

export class DepartmentForm implements IComponent {

    private departmentService = new DepartmentService();
    private departmentValidation = new DepartmentValidation();

    render(params) {
        const department: IDepartment = params == null ? {} : params;
        $(AppConst.ID).append($('<p/>', {text: 'Create a new department'}));
        const form = $('<form/>',
            {id: 'create-department-form-id', method: 'POST'})
        const inputDiv = $('<div/>').append(
            $('<span/>', {text: 'Department name:'}),
            $('<input/>',
                {type: 'hidden', value: department?.id, name: 'id', id: 'id'}),
            $('<input/>',
                {
                    type: 'text',
                    value: department?.name,
                    maxLength: 20,
                    name: 'departmentName',
                    id: 'departmentName'
                }));
        const errorDiv = $('<div/>', {id: 'error-div'});
        const actionDiv = $('<div/>').append(
            $('<button/>', {text: 'Create', type: 'submit'}),
            $('<a/>', {
                text: 'Back',
                id: 'back-button',
                type: 'button',
                href: '#departments'
            }));
        inputDiv.append(errorDiv);
        form.append(inputDiv, actionDiv);
        $(AppConst.ID).append(form);
        const formId: string = '#create-department-form-id';
        let validator = this.departmentValidation.validate(formId);
        $(formId).on('submit', (event) => {
                event.preventDefault();
                console.log($("#departmentName").val());
                if (validator.errorList.length == 0) {
                    const department: IDepartment = {
                        id: RequestUtils.getNumber($("input[name=id]").val()),
                        name: RequestUtils.getString($("input[name=departmentName]").val())
                    };
                    this.departmentService.createDepartment(department).done(() => {
                        location.hash = "#departments";
                    });
                }
            }
        );
    }
}