import {IComponent} from "../../IComponent";
import {AppConst} from "../../../const/AppConst";
import "./DepartmentList.css"
import {Routers} from "../../../routing/Routers";

export class DepartmentList implements IComponent {

    public render(params) {

        const actionDiv = $('<div/>').addClass('action-div');
        actionDiv.append($('<button/>', {text: 'Create a new department'})
            .on('click', () => {
                AppConst.ID.html('');
                Routers.searchHash('#department-form');
            }));

        if (params === null || params.length === 0) {
            AppConst.ID.append($('<p/>', {text: 'There is no department!'})
                .addClass('no-content'));
            AppConst.ID.append(actionDiv);
            return;
        }

        const table = $('<table/>');
        const tr = $('<tr/>').addClass('head-tr');

        table.append($('<caption/>', {text: 'Departments table'}));
        tr.append($('<th/>', {text: 'ID'}));
        tr.append($('<th/>', {text: 'Name'}));
        tr.append($('<th/>', {text: 'Action'}));
        table.append(tr);

        for (let department of params) {
            const tr = $('<tr/>');
            tr.append($('<td/>', {text: department.id}));
            tr.append($('<td/>', {text: department.name})
                .addClass('department-name'))
                .on('click', () => {
                    Routers.searchHash('#employee', department.employees)
                });
            tr.append(
                $('<td/>').addClass('action-td')
                    .append($('<button/>', {text: 'EDIT'})
                        .on('click', () => {
                            AppConst.ID.html('');
                            Routers.searchHash('#department-form', department);
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