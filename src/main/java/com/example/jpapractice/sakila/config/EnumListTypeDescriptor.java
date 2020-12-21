package com.example.jpapractice.sakila.config;

import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractTypeDescriptor;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * https://joshefin.xyz/map-a-collection-of-enums-to-a-column-with-hibernate/
 */
@SuppressWarnings("rawtypes")
public class EnumListTypeDescriptor extends AbstractTypeDescriptor<List> {

    private static final String SEPARATOR = ",";

    private final Object[] enumConstants;

    @SuppressWarnings({"rawtypes"})
    protected EnumListTypeDescriptor(Class enumClass) {
        super(List.class);
        this.enumConstants = enumClass.getEnumConstants();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public String toString(List value) {
        return (String) value.stream()
                .map(Enum.class::cast)
                .map(v -> Integer.toString(((Enum) v).ordinal()))
                .collect(Collectors.joining(SEPARATOR));
    }

    @SuppressWarnings({"rawtypes", "ConstantConditions"})
    @Override
    public List fromString(String string) {
        if (string == null)
            return null;

        List<Enum> list = Arrays.stream(StringUtils.split(string, SEPARATOR))
                .map(ordinal -> enumConstants[Integer.parseInt(ordinal)])
                .map(Enum.class::cast)
                .collect(Collectors.toList());

        if (list.isEmpty())
            return null;

        return list;
    }

    @Override
    public int extractHashCode(List value) {
        return Objects.hashCode(value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <X> X unwrap(List value, Class<X> type, WrapperOptions options) {
        if (value == null)
            return null;

        if (List.class.isAssignableFrom(type))
            return (X) value;

        if (String.class.isAssignableFrom(type))
            return (X) toString(value);

        throw unknownUnwrap(type);
    }

    @SuppressWarnings({"RedundantClassCall", "rawtypes"})
    @Override
    public <X> List wrap(X value, WrapperOptions options) {
        if (value == null)
            return null;

        if (List.class.isInstance(value))
            return (List) value;

        if (String.class.isInstance(value))
            return fromString((String) value);

        throw unknownWrap(value.getClass());
    }
}