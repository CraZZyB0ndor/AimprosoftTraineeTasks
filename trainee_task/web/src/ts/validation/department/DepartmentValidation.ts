import {IValidation} from "../IValidation";
import {IDepartment} from "../../models/IDepartment";
import {Validators} from "../Validators";

export class DepartmentValidation implements IValidation<IDepartment> {

    validate(department: IDepartment): boolean {

        const errors = $('#error-div');
        errors.html('');

        if (!Validators.nameValidation(department.name)) {
            errors.append($('<span/>', {text: 'Name is invalid!'}))
            return false;
        }

        return true;
    }
}