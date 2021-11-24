import {IEmployee} from "./IEmployee";

export interface IDepartment {
    id: number;
    employees: IEmployee[];
    name: string;
}