export class RequestUtils {

    public static getNumber(value: any): number {
        return parseInt(value, 10);
    }

    public static getString(value: any): string {
        return String(value);
    }

    public static getDate(value: any): Date {
        return new Date(value);
    }
}
