package com.example.jpapractice.sakila.config;

import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;
import org.hibernate.usertype.DynamicParameterizedType;

import java.util.List;
import java.util.Properties;

public class EnumListType extends AbstractSingleColumnStandardBasicType<List> implements DynamicParameterizedType {

    public EnumListType() {
        super(VarcharTypeDescriptor.INSTANCE, null);
    }

    @Override
    public String getName() {
        return "ListType";
    }

    @Override
    public String[] getRegistrationKeys() {
        return new String[]{getName(), "List", List.class.getName()};
    }

    @Override
    public void setParameterValues(Properties parameters) {
        String enumClassName = (String) parameters.get("enumClass");

        try {
            setJavaTypeDescriptor(new EnumListTypeDescriptor(Class.forName(enumClassName)));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}