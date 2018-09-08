package com.conchify.modelo.implementations;

import com.conchify.Utils.DBService;
import com.conchify.modelo.Queja;
import com.conchify.modelo.dao.QuejaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuejaDAOImpl implements QuejaDAO {

    @Override
    public void insert(Queja e) {
        Connection con = null;

        try {

            con = DBService.getInstancia().connection();


            String query = "insert into quejas(numero_placa_id, numero_carnet_id, numero_franja_id, fecha, hora, comentario, tipo_queja, estado, comentario_cierre) values (?,?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = con.prepareStatement(query);

            preparedStatement.setInt(1, e.getNumero_placa_id());
            preparedStatement.setInt(2, e.getNumero_carnet_id());
            preparedStatement.setInt(3, e.getNumero_franja_id());

            preparedStatement.setDate(4, e.getFecha());
            preparedStatement.setTimestamp(5, e.getHora());
            preparedStatement.setString(6, e.getComentario());
            preparedStatement.setString(7, e.getTipo_queja().name());
            preparedStatement.setString(8, e.getEstado().name());
            preparedStatement.setString(9, e.getComentario_cierre());

            preparedStatement.execute();


        } catch (Exception e1) {
            Logger.getLogger(Queja.class.getName()).log(Level.SEVERE, null, e1);
        }finally{

            try {
                con.close();
            } catch (SQLException e1) {
                Logger.getLogger(Queja.class.getName()).log(Level.SEVERE, null, e1);
            }
        }

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
