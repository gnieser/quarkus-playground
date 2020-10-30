package org.acme;

import io.quarkus.qute.TemplateExtension;

import javax.enterprise.inject.Vetoed;
import java.util.EnumSet;

@Vetoed
@TemplateExtension(namespace = "enum")
public class EnumTemplateExtension {

    @SuppressWarnings({"unchecked"})
    static EnumSet<?> values(String enumClass) throws ClassNotFoundException {
        if (enumClass != null) {
            Class<?> e = Class.forName(enumClass);
            if (e.isEnum()) {

                return EnumSet.allOf((Class<? extends Enum>) e);
            }
        }
        throw new IllegalArgumentException("Not an enum type: " + enumClass);
    }
}
