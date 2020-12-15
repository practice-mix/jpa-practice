/**
 * @author Luo Bao Ding
 * @since 12/11/2020
 */
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
@GenericGenerator(name = "customUuid", strategy = "com.example.jpapractice.sakila.config.MyAirportIdGenerator")
package com.example.jpapractice.sakila.model;

import org.hibernate.annotations.GenericGenerator;