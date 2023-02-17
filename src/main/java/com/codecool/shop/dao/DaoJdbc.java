package com.codecool.shop.dao;

import javax.sql.DataSource;

public interface DaoJdbc {
    void init(DataSource dataSource);
}
