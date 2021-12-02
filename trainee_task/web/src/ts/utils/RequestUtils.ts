export class RequestUtils {

    public static getNumber(value): number {
        return parseInt(value, 10);
    }

    public static getString(value): string {
        return String(value);
    }

    public static getDate(value): Date {
        return new Date(value);
    }
}