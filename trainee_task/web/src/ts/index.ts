import {Routers} from "./routing/Routers";

class Index {

    public static start(): void {
        window.addEventListener('load', () => Routers.searchHash(location.hash));
    }
}

Index.start();