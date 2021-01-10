package com.example.jpapractice.sakila.config;

import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.spi.MetadataBuilderContributor;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

public class SqlFunctionsMetadataBuilderContributor
        implements MetadataBuilderContributor {

    @Override
    public void contribute(
            MetadataBuilder metadataBuilder) {

        metadataBuilder.applySqlFunction("ftsTwo", new SQLFunctionTemplate(StandardBasicTypes.DOUBLE,
                "match(?1,?2) against  (?3 in boolean mode)"));
        metadataBuilder.applySqlFunction("ftsOne", new SQLFunctionTemplate(StandardBasicTypes.DOUBLE,
                "match(?1) against  (?2 in boolean mode)"));
        metadataBuilder.applySqlFunction("group_concat", new SQLFunctionTemplate(StandardBasicTypes.STRING, "group_concat(?1)"));
        metadataBuilder.applySqlFunction("json_contains", new SQLFunctionTemplate(StandardBasicTypes.BOOLEAN, "json_contains(?1,?2,?3)"));

        metadataBuilder.applySqlFunction("match", new VarArgsConjugateSQLFunction(StandardBasicTypes.DOUBLE, "match(", ",", ") against(", " in boolean mode)"));

    }
}
