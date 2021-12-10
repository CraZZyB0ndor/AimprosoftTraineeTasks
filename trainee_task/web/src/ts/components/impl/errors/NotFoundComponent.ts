import {IComponent} from "../../IComponent";
import {AppConst} from "../../../const/AppConst";
import "../styles/not-found.css"

export class NotFoundComponent implements IComponent {

    public render(params) {
        const mainDiv = $('<div/>', {id: "not-found-container"});
        mainDiv.append($('<p/>', {text: "Page not found #404"}));
        mainDiv.append($('<a/>', {text: "Main menu", href: "#departments"}));
        $(AppConst.ID).append(mainDiv);
    }
}