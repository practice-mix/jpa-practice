package com.example.jpapractice.sakila.config;

import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.spatial.dialect.mysql.MySQL8SpatialDialect;
import org.hibernate.type.StandardBasicTypes;

//public class MySQLDialectCustom extends MySQL8Dialect {
public class MySQLDialectCustom extends MySQL8SpatialDialect {//use the spatial support

  public MySQLDialectCustom() {
      super();
      registerFunction("ftsTwo", new SQLFunctionTemplate(StandardBasicTypes.DOUBLE,
              "match(?1,?2) against  (?3 in boolean mode)"));
      registerFunction("ftsOne", new SQLFunctionTemplate(StandardBasicTypes.DOUBLE,
              "match(?1) against  (?2 in boolean mode)"));
  }
}