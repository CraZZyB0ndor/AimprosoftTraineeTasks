import jqXHR = JQuery.jqXHR;
import {IEmployee} from "../../models/IEmployee";
import {IDepartment} from "../../models/IDepartment";

export class DataService {

    public static get(query: string): jqXHR {
        return $.ajax({
            url: query,
            type: 'GET',
            dataType: 'json',
            contentType: 'application/json',
        });
    }

    public static create(query: string, model: IDepartment | IEmployee): jqXHR {
        return $.ajax({
            url: query,
            data: JSON.stringify(model),
            type: 'POST',
            contentType: 'application/json'
        });
    }

    public static delete(query: string, id: object): jqXHR {
        console.log(id);
        return $.ajax({
            url: query,
            data: JSON.stringify(id), // {id: 12}
            contentType: 'application/json',
            type: 'DELETE',
        });
    }
}