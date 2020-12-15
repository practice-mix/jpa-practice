package com.example.jpapractice.sakila.model.projection;

/**
 * this is a a closed projection and a interface-based projection
 * If you use a closed projection, Spring Data can optimize the query execution, because we know about all the attributes that are needed to back the projection proxy.
 *
 * @author Luo Bao Ding
 * @since 12/12/2020
 */
public interface NamesOnly {
    String getFirstName();
    String getLastName();
}
