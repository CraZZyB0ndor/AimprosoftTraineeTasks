import {IComponent} from "../../IComponent";
import {AppConst} from "../../../const/AppConst";
import "../style/list-style.css"
import {DepartmentService} from "../../../services/impl/DepartmentService";
import {Routers} from "../../../routing/Routers";

export class DepartmentList implements IComponent {

    private departmentService = new DepartmentService();

    public render(params) {
        const actionDiv = $('<div/>').addClass('action-div');
        actionDiv.append($('<a/>',
            {
                text: 'Create a new department',
                href: '#department'
            }));
        if (params === null || params.length === 0) {
            $(AppConst.ID).append($('<p/>', {text: 'There is no department!'})
                .addClass('no-content'));
            $(AppConst.ID).append(actionDiv);
            return;
        }
        const table = $('<table/>');
        const tr = $('<tr/>').addClass('head-tr');
        table.append($('<caption/>', {text: 'Departments table'}));
        tr.append($('<th/>', {text: 'ID'}));
        tr.append($('<th/>', {text: 'Name'}));
        tr.append($('<th/>', {text: 'Action'}));
        table.append(tr, this.getDepartmentTR(params));
        $(AppConst.ID).append(table);
        $(AppConst.ID).append(actionDiv);
    }

    private getDepartmentTR(departments): Array<any> {
        const trs = [];
        for (let department of departments) {
            const tr = $('<tr/>');
            tr.append($('<td/>', {text: department.id}));
            tr.append($('<td/>').append($('<a/>',
                {
                    text: department.name,
                    href: `#employees/${department.id}`
                })
                .addClass('item-name')));
            tr.append(
                $('<td/>').addClass('action-td')
                    .append($('<a/>', {text: 'EDIT', href: `#department/${department.id}`}))
                    .append($('<button/>', {text: 'DELETE'})
                        .on('click', () => {
                            this.departmentService.deleteById(department.id).done(() => {
                                Routers.searchHash('#departments')
                            });
                        })));
            trs.push(tr);
        }
        return trs;
    }
}