import {Validators} from "./validation/Validators";
import {Router} from "./routing/Router";
require('jquery-validation');

class Index {

    public static start(): void {
        Validators.setValidationMethods();
        window.addEventListener('load', () => Router.getRoute(location.hash));
        window.addEventListener('hashchange', () => Router.getRoute(location.hash));
    }
}
Index.start();