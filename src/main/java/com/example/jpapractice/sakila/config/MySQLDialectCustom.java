package com.example.jpapractice.sakila.config;

import org.hibernate.dialect.MySQL8Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

public class MySQLDialectCustom extends MySQL8Dialect {

  public MySQLDialectCustom() {
    super();
    registerFunction("ftsTwo", new SQLFunctionTemplate(StandardBasicTypes.DOUBLE,
        "match(?1,?2) against  (?3 in boolean mode)"));
  }
}