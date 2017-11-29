export class GenericWrapper {
    public static getFieldByString(object: any, fieldString: string): any {
        const fields: string[] = fieldString.split('.');
        let value: any = object;

        for ( const field of fields ) {
            value = value[field];
        }

        value = String(value).toLowerCase();

        return value;
    }

    public static copy(src: any, target: any ) {
        for ( const prop in src ) {
            if ( src.hasOwnProperty(prop) ) {
                target[prop] = src[prop];
            }
        }
    }

    public static prepareForDao(object: any ): any {
        object = GenericWrapper.flattenObject(object);

        for ( const prop in object ) {
          if ( !(object[prop] instanceof String) ) {
            const value = object[prop];

            object[prop] = String(value);
          }
        }

        return object;
    }

    public static flattenObject(object: any ): any {
        const output = Object.assign({}, object);

       for ( const prop in object ) {
           if ( object.hasOwnProperty( prop ) ) {

               if ( prop === 'state' ) {
                 delete output[prop];
               }

               if ( object[prop] instanceof Object ) {
                   if ( object[prop].hasOwnProperty('id') ) {
                       output[`${prop}Id`] = object[prop].id;
                   }

                   delete output[prop];

               }
           }
       }

       return output;
    }
}
