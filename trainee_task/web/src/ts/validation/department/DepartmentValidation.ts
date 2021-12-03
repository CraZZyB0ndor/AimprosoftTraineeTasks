import {IValidator} from "../IValidator";
import {IDepartment} from "../../models/IDepartment";

export class DepartmentValidation implements IValidator {

    validate(formId: string): any {
        return $(formId).validate({
            rules: {
                departmentName: {
                    nameValidation: true,
                    remote: {
                        url: `/departments/exist`,
                        type: "GET",
                        contentType: 'application/json',
                        dataType: 'json',
                        dataFilter: (data) => {
                            if (!!data) {
                                const department: IDepartment = JSON.parse(data);
                                return department.id == $('#id').val();
                            }
                            return true;
                        }
                    }
                },
            },
            messages: {
                departmentName: {
                    remote: $.validator.format("Name: {0} is exist!"),
                    nameValidation: "Name is not correct!"
                }
            },
            focusInvalid: true,
            errorClass: "input_error"
        });
    }
}