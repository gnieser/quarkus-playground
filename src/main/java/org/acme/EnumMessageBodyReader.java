package org.acme;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Scanner;

@Provider
@Consumes({MediaType.MULTIPART_FORM_DATA, MediaType.TEXT_PLAIN})
public class EnumMessageBodyReader<E extends Enum> implements MessageBodyReader<Enum> {

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return type.isEnum();
    }

    @Override
    public E readFrom(Class<Enum> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        // \A matches start of a string
        try (Scanner s = new Scanner(entityStream).useDelimiter("\\A")) {
            return (E) Enum.valueOf(type, s.next());
        }
    }
}

