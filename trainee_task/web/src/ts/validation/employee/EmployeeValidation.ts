import {IValidator} from "../IValidator";
import {IEmployee} from "../../models/IEmployee";

export class EmployeeValidation implements IValidator {

    validate(formId): any {
        return $(formId).validate({
            rules: {
                'employee-name': {
                    nameValidation: true
                },
                'email': {
                    required: true,
                    email: true,
                    remote: {
                        url: `/employees/exist`,
                        type: "GET",
                        contentType: 'application/json',
                        dataType: 'json',
                        dataFilter: (data) => {
                            if (!!data) {
                                const employee: IEmployee = JSON.parse(data);
                                return employee.id == $('#id').val();
                            }
                            return true;
                        },
                    }
                },
                'employee-age': {
                    required: true,
                    min: 18,
                    max: 60
                },
                'employee-date': {
                    required: true,
                    dateValidation: true
                }
            },
            messages: {
                'employee-name': 'Name is not correct!',
                'email': {
                    required: 'Email is required!',
                    email: 'Email is not correct!',
                    remote: $.validator.format("Email: {0} is exist!"),
                },
                'employee-age': 'Age must be greater 18 and lower 60!',
                'employee-date': 'Date cannot be greater than today date!'
            },
            focusInvalid: true,
            errorClass: "input_error"
        });
    }
}