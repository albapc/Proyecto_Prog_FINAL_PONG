/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author socamporomani
 */

public class Conndatos {
    
    static Connection con = null;
    static Statement s = null;
    static ResultSet rs = null;
    
    public static Connection conectar() {
//        boolean conectado = false;
        try {
            //Cargar Driver
            Class.forName("org.sqlite.JDBC");
            //Conectar a la base de datos SQLite
            con = DriverManager.getConnection("jdbc:sqlite:datos.db");
            //Confirmamos conexion
//            conectado = true;
            System.out.println("Conexión establecida con el servidor");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("ERROR DE CONEXION ---> " + ex);
//            System.out.println(conectado);
        }
        return con;
    }
    
    
}
