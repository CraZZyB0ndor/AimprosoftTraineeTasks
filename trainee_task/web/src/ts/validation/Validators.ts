export class Validators {

    public static setValidationMethods() {
        $.validator.methods.nameValidation = function (value) {
            return /^[A-zА-яїЇєЄіІґҐ]{2,20}$/.test(value);
        }
        $.validator.methods.dateValidation = function (date) {
            return Date.parse(date) <= new Date().getTime();
        }
    }
}