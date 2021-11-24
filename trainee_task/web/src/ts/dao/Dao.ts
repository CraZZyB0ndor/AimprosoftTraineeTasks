import jqXHR = JQuery.jqXHR;
import {IEmployee} from "../models/IEmployee";
import {IDepartment} from "../models/IDepartment";
import {Routers} from "../routing/Routers";

export class Dao {

    public static get(query: string, id?: object): jqXHR {
        return $.ajax({
            url: query,
            data: JSON.stringify(id),
            type: 'GET',
            dataType: 'json',
            contentType: 'application/json',
        });
    }

    public static create(query: string, model: IDepartment | IEmployee) {
        return $.ajax({
            url: query,
            data: JSON.stringify(model),
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json'
        });
    }

    public static delete(query: string, id: object): void {
        $.ajax({
            url: query,
            data: JSON.stringify(id),
            dataType: 'json',
            contentType: 'application/json',
            type: 'DELETE',
        });
    }
}