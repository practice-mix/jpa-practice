package com.example.jpapractice.sakila.config;

import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.spatial.dialect.mysql.MySQL8SpatialDialect;
import org.hibernate.type.StandardBasicTypes;

/**
 * its alternative: MetadataBuilderContributor, see: SqlFunctionsMetadataBuilderContributor
 */
//public class MySQLDialectCustom extends MySQL8Dialect {
public class MySQLDialectCustom extends MySQL8SpatialDialect {//use the spatial support

    public MySQLDialectCustom() {
        super();
        registerFunction("ftsTwo", new SQLFunctionTemplate(StandardBasicTypes.DOUBLE,
                "match(?1,?2) against  (?3 in boolean mode)"));
        registerFunction("ftsOne", new SQLFunctionTemplate(StandardBasicTypes.DOUBLE,
                "match(?1) against  (?2 in boolean mode)"));
        registerFunction("group_concat", new SQLFunctionTemplate(StandardBasicTypes.STRING, "group_concat(?1)"));
        registerFunction("json_contains", new SQLFunctionTemplate(StandardBasicTypes.BOOLEAN, "json_contains(?1,?2,?3)"));

        registerFunction("match", new VarArgsConjugateSQLFunction(StandardBasicTypes.DOUBLE, "match(", ",", ") against(", " in boolean mode)"));
    }
}
