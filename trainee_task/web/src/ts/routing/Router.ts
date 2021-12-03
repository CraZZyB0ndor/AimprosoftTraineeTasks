import "../components/impl/style/list-style.css";
import {IContainer} from "../containers/IContainer";
import {DisplayDepartments} from "../containers/impl/department/DisplayDepartments";
import {DisplayEmployee} from "../containers/impl/employee/DisplayEmployee";
import {CreateUpdateDepartment} from "../containers/impl/department/CreateUpdateDepartment";
import {CreateUpdateEmployee} from "../containers/impl/employee/CreateUpdateEmployee";
import {AppConst} from "../const/AppConst";

export class Router {

    private static routers: Map<string, IContainer> = new Map<string, IContainer>()
        .set("#departments", new DisplayDepartments())
        .set("#departments/employees", new DisplayEmployee())
        .set("#departments/", new CreateUpdateDepartment())
        .set("#departments/employees/", new CreateUpdateEmployee())

    public static getRoute(hashWithParam: string): void {
        $(AppConst.ID).html('');
        const hash = this.getHash(hashWithParam);
        const params = this.getParams(hashWithParam);
        if (this.routers.has(hash)) {
            return this.routers.get(hash).getContent(params);
        }
        new DisplayDepartments().getContent(params);
    }

    private static getHash(hash: string): string {
        const result =
            hash.replace(/([0-9]+\/)/, '').replace(/\/[0-9]+/, '/');
        console.log('HASH --> ' + result);
        return result;
    }

    private static getParams(hash: string): Array<any> {
        const pattern = /(\/?#?[a-zA-Z]+\/?)/;
        const result =
            hash.replace(pattern, '')
                .replace(pattern, '_')
                .replace(/_$/, '');
        console.log('PARAMS --> ' + result + '\nLength: ' + result.length);
        return result.split('_');
    }
}