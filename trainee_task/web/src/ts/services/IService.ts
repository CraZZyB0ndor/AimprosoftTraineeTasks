import jqXHR = JQuery.jqXHR;

export interface IService {

    getById(id: number): jqXHR;
    deleteById(id: number);
}