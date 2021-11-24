import {IComponent} from "../../IComponent";
import {AppConst} from "../../../const/AppConst";
import "./EmployeeList.css"
import {Routers} from "../../../routing/Routers";

export class EmployeeList implements IComponent {

    public render(params) {

        const actionDiv = $('<div/>').addClass('action-div');
        actionDiv.append($('<button/>', {text: 'Create a new employee'}));
        actionDiv.append($('<button/>', {text: 'Back'})
            .on('click', () => {
                Routers.searchHash('#department');
            }));

        if (params === null || params.length === 0) {
            AppConst.ID.append($('<p/>', {text: 'There is no employee!'})
                .addClass('no-content'));
            AppConst.ID.append(actionDiv);
            return;
        }

        const table = $('<table/>');
        const tr = $('<tr/>').addClass('head-tr');

        table.append($('<caption/>', {text: 'Employees table'}));
        tr.append($('<th/>', {text: 'ID'}));
        tr.append($('<th/>', {text: 'Name'}));
        tr.append($('<th/>', {text: 'Email'}));
        tr.append($('<th/>', {text: 'Age'}));
        tr.append($('<th/>', {text: 'Start working date'}));
        tr.append($('<th/>', {text: 'Action'}));
        table.append(tr);

        for (let employee of params) {
            const tr = $('<tr/>');
            tr.append($('<td/>', {text: employee.id}));
            tr.append($('<td/>', {text: employee.name}));
            tr.append($('<td/>', {text: employee.email}));
            tr.append($('<td/>', {text: employee.age}));
            tr.append($('<td/>', {
                text: new Date(params[0].startWorkingDate).toISOString().slice(0, 10)
            }));
            tr.append(
                $('<td/>').addClass('action-td')
                    .append($('<button/>', {text: 'EDIT'})
                        .on('click', () => {
                            console.log("--edit--");
                        }))
                    .append($('<button/>', {text: 'DELETE'})
                        .on('click', () => {
                            console.log("--delete--");
                        })));
            table.append(tr);
        }

        AppConst.ID.append(table);
        AppConst.ID.append(actionDiv);
    }
}
