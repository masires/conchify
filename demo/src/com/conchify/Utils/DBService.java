package com.conchify.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBService {

    private static DBService instancia;

    public static DBService getInstancia(){

        if (instancia == null)
            instancia = new DBService();
        return instancia;
    }

    public Connection connection(){
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://108.178.54.58:3306/contrune_contru";
            connection = DriverManager.getConnection(url, "contrune", "0KhH2;v4D(mp1K");
            System.out.println("conectado");
        }catch (Exception e){
            Logger.getLogger(DBService.class.getName()).log(Level.SEVERE, null, e);
        }

        return connection;
    }
}
