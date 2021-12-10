import {Validators} from "./validation/Validators";
import {Router} from "./routing/Router";
require('jquery-validation');

class Index {

    public static start(): void {
        Validators.setValidationMethods();
        window.addEventListener('load', () => Router.address(location.hash));
        window.addEventListener('hashchange', () => Router.address(location.hash));
    }
}

Index.start();