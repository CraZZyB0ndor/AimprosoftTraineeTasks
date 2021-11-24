import {Router} from "./Router";
import {AppConst} from "../const/AppConst";

export class Routers {

    private static router = new Router();
    private static oldHash: string = '';

    public static searchHash(hash: string, param?) {
        if (hash !== this.oldHash) {
            AppConst.ID.html('');
            this.router.getRoute(hash).getContent(param);
            this.oldHash = hash;
        }
    }
}