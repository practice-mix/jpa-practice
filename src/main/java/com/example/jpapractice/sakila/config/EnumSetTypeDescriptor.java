package com.example.jpapractice.sakila.config;

import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractTypeDescriptor;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * https://joshefin.xyz/map-a-collection-of-enums-to-a-column-with-hibernate/
 */
@SuppressWarnings("rawtypes")
public class EnumSetTypeDescriptor extends AbstractTypeDescriptor<EnumSet> {

	private static final String SEPARATOR = ",";

	private final Object[] enumConstants;

	@SuppressWarnings({"rawtypes"})
	protected EnumSetTypeDescriptor(Class enumClass) {
		super(EnumSet.class);
		this.enumConstants = enumClass.getEnumConstants();
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public String toString(EnumSet value) {
		return (String) value.stream()
				.map(Enum.class::cast)
				.map(v -> Integer.toString(((Enum) v).ordinal()))
				.collect(Collectors.joining(SEPARATOR));
	}

	@SuppressWarnings({"rawtypes", "ConstantConditions", "unchecked"})
	@Override
	public EnumSet fromString(String string) {
		if (string == null)
			return null;

		List<Enum> list = Arrays.stream(string.split(SEPARATOR))
				.map(ordinal -> enumConstants[Integer.parseInt(ordinal)])
				.map(Enum.class::cast)
				.collect(Collectors.toList());

		if (list.isEmpty())
			return null;

		return EnumSet.copyOf(list);
	}

	@Override
	public int extractHashCode(EnumSet value) {
		return Objects.hashCode(value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <X> X unwrap(EnumSet value, Class<X> type, WrapperOptions options) {
		if (value == null)
			return null;

		if (EnumSet.class.isAssignableFrom(type))
			return (X) value;

		if (String.class.isAssignableFrom(type))
			return (X) toString(value);

		throw unknownUnwrap(type);
	}

	@SuppressWarnings({"RedundantClassCall", "rawtypes"})
	@Override
	public <X> EnumSet wrap(X value, WrapperOptions options) {
		if (value == null)
			return null;

		if (EnumSet.class.isInstance(value))
			return (EnumSet) value;

		if (String.class.isInstance(value))
			return fromString((String) value);

        throw unknownWrap(value.getClass());
    }
}