import {Router} from "./Router";
import {AppConst} from "../const/AppConst";

export class Routers {

    private static router = new Router();

    public static searchHash(hash: string) {
        $(AppConst.ID).html('');
        const params = this.getParams(hash);
        console.log(params);
        const path = params == null ? hash.substr(hash.indexOf('#')) :
            hash.substr(hash.indexOf('#'), hash.indexOf('/'));
        this.router.getRoute(path).getContent(params);
    }

    private static getParams(hash: string): Array<string> {
        if (hash.indexOf('/') == -1) {
            return null;
        }
        return hash.substr(hash.indexOf('/') + 1).split(/\//);
    }
}