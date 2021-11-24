export class Validators {

    public static nameValidation(name: string): boolean {
        return new RegExp('^[A-zА-яїЇєЄіІґҐ]{2,20}$').test(name);
    }
}