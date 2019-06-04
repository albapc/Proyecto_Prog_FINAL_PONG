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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import objetosClase.Jugador;

/**
 *
 * @author r618b
 */
public class Seleccionar {

    static Object[] jugadores;
     ArrayList<Object[]> lista = new ArrayList<Object[]>();
    
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:datos.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    
    public ArrayList selectAll(){
        ArrayList<Jugador> aux = new ArrayList();
        try {
            //Declarar consulta
            Conexion.s = Conexion.con.createStatement();
            //Ejecutar consulta
            Conexion.rs = Conexion.s.executeQuery("select * from jugadores");
            while (Conexion.rs.next()) {
                aux.add(new Jugador(
                        Conexion.rs.getString("nombre"), 
                        Integer.parseInt(Conexion.rs.getString("puntos"))));
            }
        } catch (SQLException ex) {
            System.out.println("ERROR ---> " + ex);
        }
        return aux;
    }

}
