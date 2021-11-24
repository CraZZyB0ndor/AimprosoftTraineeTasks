import {IComponent} from "../../IComponent";
import {AppConst} from "../../../const/AppConst";
import {Routers} from "../../../routing/Routers";
import "./DepartmentForm.css";

export class DepartmentForm implements IComponent {

    render(params) {

        AppConst.ID.append($('<p/>', {text: 'Create a new department'}));

        const form = $('<form/>',
            {id: 'create-department-form-id', method: 'POST', action: '/create-update-department'})
        const inputDiv = $('<div/>').append(
            $('<span/>', {text: 'Department name:'}),
            $('<input/>',
                {type: 'text', value: params?.department.name, maxLength: 20, name: 'department-name'}));
        const errorDiv = $('<div/>', {id: 'error-div'});
        const actionDiv = $('<div/>').append(
            $('<button/>', {text: 'Create', type: 'submit'}),
            $('<button/>', {text: 'Back'}).on('click', () => {
                Routers.searchHash('#department');
            })
        );
        inputDiv.append(errorDiv);
        form.append(inputDiv, actionDiv);
        AppConst.ID.append(form);
    }

}