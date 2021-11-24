export class RequestUtils {

    public static getNumber(value): number {
        return parseInt(value, 10);
    }

    public static getString(value): string {
        return String(value);
    }
}