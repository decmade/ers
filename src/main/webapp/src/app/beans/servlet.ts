export class Servlet {

    public static getRootUri() {
        const element = document.getElementsByTagName('base')[0];
        const pattern = new RegExp('http://.*:4200.*');
        let uri = '';

        if ( pattern.test( element.baseURI ) ) {
            uri = 'http://localhost:8080/ers';
        } else {
            uri = '/ers';
        }

        return uri;
    }

    public static getServiceUrl(serviceName: string): string {
        return [
            this.getRootUri(),
            serviceName
        ].join('/');
    }

}
