/**
 * @author Luo Bao Ding
 * @since 12/11/2020
 */
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
@GenericGenerator(name = "customUuid", strategy = "com.example.jpapractice.sakila.config.MyAirportIdGenerator")
@TypeDef(name = "enumSet", typeClass = EnumSetType.class, defaultForType = EnumSet.class)
package com.example.jpapractice.sakila.model;

import com.example.jpapractice.sakila.config.EnumSetType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.TypeDef;

import java.util.EnumSet;