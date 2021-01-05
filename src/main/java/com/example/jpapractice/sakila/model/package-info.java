/**
 * @author Luo Bao Ding
 * @since 12/11/2020
 */
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
@GenericGenerator(name = "customUuid", strategy = "com.example.jpapractice.sakila.config.MyAirportIdGenerator")
@TypeDef(name = "EnumSet", typeClass = EnumSetType.class, defaultForType = EnumSet.class)
@TypeDef(name = "EnumList", typeClass = EnumListType.class)
@TypeDef(name = "json", typeClass = JsonStringType.class)
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
package com.example.jpapractice.sakila.model;

import com.example.jpapractice.sakila.config.customtype.EnumListType;
import com.example.jpapractice.sakila.config.customtype.EnumSetType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.TypeDef;

import java.util.EnumSet;
