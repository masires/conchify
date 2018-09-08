package com.conchify.modelo.dao;

import com.conchify.modelo.Queja;

public interface QuejaDAO {

    void insert(Queja e);

    void update(Queja e);

    long getNextID();

    void delete(Queja e);

}
