export class UrlUtils {

  public static getSimpleUrl(hash: string): string {
    return hash.replace(/([0-9]+\/)/, '').replace(/\/[0-9]+/, '/');
  }

  public static getParams(hash: string): Array<any> {
    const pattern = /[a-zA-Z/]+/g;
    const result = hash.replace(pattern, '_').replace(/(^_)|(_$)/g, '');
    return result.split('_');
  }
}
