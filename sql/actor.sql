alter table actor
    add fulltext fk_first_name_last_name (first_name, last_name);
