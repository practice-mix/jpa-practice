package com.example.jpapractice.sakila.config;

import com.example.jpapractice.sakila.model.AbstractCustomIdGenerate;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.UUID;

public class MyAirportIdGenerator implements IdentifierGenerator {


    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        AbstractCustomIdGenerate customIdGenerate = (AbstractCustomIdGenerate) object;
        if (customIdGenerate.getId() != null) {
            return customIdGenerate.getId();
        } else {
            return UUID.randomUUID().toString().replaceAll("-", "");
        }

    }
}