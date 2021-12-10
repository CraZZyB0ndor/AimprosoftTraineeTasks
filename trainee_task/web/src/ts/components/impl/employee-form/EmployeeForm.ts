import {IComponent} from "../../IComponent";
import {AppConst} from "../../../const/AppConst";
import "../styles/form-style.css";
import {IEmployee} from "../../../models/IEmployee";
import {RequestUtils} from "../../../utils/RequestUtils";
import {EmployeeValidation} from "../../../validation/employee/EmployeeValidation";
import {EmployeeService} from "../../../services/impl/EmployeeService";

export class EmployeeForm implements IComponent {

    private employeeValidation = new EmployeeValidation();
    private employeeService = new EmployeeService();

    render(params) {
        const employee: IEmployee = params.employee != null ? params.employee : {};
        const headerName = employee?.id == null ? 'Create a new employee' : 'Edit employee';
        const buttonName = employee?.id == null ? 'Create' : 'Edit';
        $(AppConst.ID).append($('<p/>', {text: headerName}));
        const form = $('<form/>',
            {id: 'create-employee-form-id', method: 'POST'})
        const inputNameDiv = $('<div/>').append(
            $('<span/>', {text: 'Name:'}),
            $('<input/>',
                {type: 'hidden', value: employee.id, name: 'id', id: 'id'}),
            $('<input/>',
                {type: 'hidden', value: params.departmentId, name: 'departmentId'}),
            $('<input/>',
                {
                    type: 'text',
                    value: employee?.name,
                    maxLength: 20,
                    name: 'employee-name',
                    id: 'employee-name'
                }));
        const inputEmailDiv = $('<div/>').append(
            $('<span/>', {text: 'Email:'}),
            $('<input/>',
                {
                    type: 'email',
                    value: employee?.email,
                    name: 'email',
                    id: 'email'
                }));
        const inputAgeDiv = $('<div/>').append(
            $('<span/>', {text: 'Age:'}),
            $('<input/>', {
                type: 'number',
                value: employee?.age,
                ariaValueMax: '60',
                ariaValueMin: '18',
                name: 'employee-age',
                id: 'employee-age',
            }));
        const date: string = employee?.startWorkingDate != null ?
            new Date(employee?.startWorkingDate).toISOString().slice(0, 10) : '';
        const inputDateDiv = $('<div/>').append(
            $('<span/>', {text: 'Start working date:'}),
            $('<input/>', {
                type: 'date',
                value: date,
                name: 'employee-date',
                id: 'employee-date'
            }));
        const actionDiv = $('<div/>').append(
            $('<button/>', {text: buttonName, type: 'submit'}),
            $('<a/>', {
                text: 'Back',
                id: 'back-button',
                href: `#departments/${params.departmentId}/employees`,
            }));
        form.append(inputNameDiv, inputEmailDiv, inputAgeDiv, inputDateDiv, actionDiv);
        $(AppConst.ID).append(form);
        const formId: string = '#create-employee-form-id';
        const validator = this.employeeValidation.validate(formId);
        $(formId).on('submit', (event) => {
                event.preventDefault();
                if (validator.errorList.length == 0) {
                    const employee: IEmployee = {
                        id: RequestUtils.getNumber($("input[name=id]").val()),
                        name: RequestUtils.getString($("input[name=employee-name]").val()),
                        department: {id: params.departmentId},
                        email: RequestUtils.getString($("input[name=email]").val()),
                        age: RequestUtils.getNumber($("input[name=employee-age]").val()),
                        startWorkingDate: RequestUtils.getDate($("input[name=employee-date]").val())
                    };
                    this.employeeService.createEmployee(employee).done(() => {
                        location.hash = `#departments/${params.departmentId}employees`;
                    });
                }
            }
        )
    }
}