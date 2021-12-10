import {IContainer} from "../../IContainer";
import {NotFoundComponent} from "../../../components/impl/errors/NotFoundComponent";

export class NotFoundContainer implements IContainer {

    private notFound = new NotFoundComponent();

    public renderContent(param) {
        this.notFound.render(param);
    }
}