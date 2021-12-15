import {IDepartment} from "./IDepartment";

export interface IEmployee {
    id?: number;
    name?: string;
    department?: IDepartment;
    email?: string;
    age?: number;
    startWorkingDate?: Date;
}