import "../components/impl/styles/list-style.css";
import {IContainer} from "../containers/IContainer";
import {DisplayDepartments} from "../containers/impl/departments/DisplayDepartments";
import {DisplayEmployee} from "../containers/impl/employees/DisplayEmployee";
import {CreateUpdateDepartment} from "../containers/impl/departments/CreateUpdateDepartment";
import {CreateUpdateEmployee} from "../containers/impl/employees/CreateUpdateEmployee";
import {AppConst} from "../const/AppConst";
import {NotFoundContainer} from "../containers/impl/errors/NotFoundContainer";

export class Router {

    private static routers: Map<string, IContainer> = new Map<string, IContainer>()
        .set("#departments", new DisplayDepartments())
        .set("#departments/employees", new DisplayEmployee())
        .set("#departments/", new CreateUpdateDepartment())
        .set("#departments/employees/", new CreateUpdateEmployee())
        .set("#notFound", new NotFoundContainer());

    public static address(hashWithParam: string) {
        console.log(hashWithParam);
        $(AppConst.ID).html('');
        const hash = this.getHash(hashWithParam);
        const params = this.getParams(hashWithParam);
        if (this.routers.has(hash)) {
            return this.routers.get(hash).renderContent(params);
        }
        if(hashWithParam === '') {
            return new DisplayDepartments().renderContent(params);
        }
        new NotFoundContainer().renderContent(params);
    }

    private static getHash(hash: string): string {
        return hash.replace(/([0-9]+\/)/, '').replace(/\/[0-9]+/, '/');
    }

    private static getParams(hash: string): Array<any> {
        const pattern = /(\/?#?[a-zA-Z]+\/?)/;
        const result =
            hash.replace(pattern, '')
                .replace(pattern, '_')
                .replace(/_$/, '');
        return result.split('_');
    }
}