import {IComponent} from "../../IComponent";
import {AppConst} from "../../../const/AppConst";
import "../style/list-style.css"
import {EmployeeService} from "../../../services/impl/EmployeeService";
import {IDepartment} from "../../../models/IDepartment";
import {Routers} from "../../../routing/Routers";

export class EmployeeList implements IComponent {

    private employeeService = new EmployeeService();

    public render(params) {
        const department: IDepartment = params.department;
        const actionDiv = $('<div/>').addClass('action-div');
        actionDiv.append($('<a/>', {
            text: 'Create a new employee',
            href: '#employee/' + department.id
        }));
        actionDiv.append($('<a/>', {
            text: 'Back',
            href: '#departments'
        }));
        if (department.employees.length === 0) {
            $(AppConst.ID).append($('<p/>', {text: 'There is no employee!'})
                .addClass('no-content'));
            $(AppConst.ID).append(actionDiv);
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
        table.append(tr, this.getEmployeeTR(department));
        $(AppConst.ID).append(table);
        $(AppConst.ID).append(actionDiv);
    }

    private getEmployeeTR(department: IDepartment): Array<any> {
        const trs = [];
        for (let employee of department.employees) {
            const tr = $('<tr/>');
            tr.append($('<td/>', {text: employee.id}));
            tr.append($('<td/>', {text: employee.name}));
            tr.append($('<td/>', {text: employee.email}));
            tr.append($('<td/>', {text: employee.age}));
            tr.append($('<td/>', {
                text: new Date(employee.startWorkingDate).toISOString().slice(0, 10)
            }));
            tr.append(
                $('<td/>').addClass('action-td')
                    .append($('<a/>', {
                        text: 'EDIT',
                        href: `#employee/${employee.id}/department/${department.id}`
                    }))
                    .append($('<button/>', {text: 'DELETE'})
                        .on('click', () => {
                            this.employeeService.deleteById(employee.id).done(() => {
                                    Routers.searchHash(`#employees/${department.id}`);
                                }
                            )
                        })));
            trs.push(tr);
        }
        return trs;
    }
}
