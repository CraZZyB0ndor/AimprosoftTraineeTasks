import {Routers} from "./routing/Routers";
import {Validators} from "./validation/Validators";

class Index {

    public static start(): void {
        Validators.setValidationMethods();
        window.addEventListener('load', () => Routers.searchHash(location.hash));
        window.addEventListener('hashchange', () => Routers.searchHash(location.hash));
    }
}
Index.start();