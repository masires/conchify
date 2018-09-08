package com.conchify.modelo.dao.servicios;

import com.conchify.modelo.Queja;
import com.conchify.modelo.dao.QuejaDAO;
import com.conchify.modelo.implementations.QuejaDAOImpl;

public class QuejaService implements QuejaDAO {
    private QuejaDAOImpl quejaDAO;

    public QuejaService(){
        quejaDAO = new QuejaDAOImpl();
    }

    @Override
    public void insert(Queja e) {
        quejaDAO.insert(e);

    }

    @Override
    public void update(Queja e) {

    }

    @Override
    public long getNextID() {
        return 0;
    }

    @Override
    public void delete(Queja e) {

    }
}
