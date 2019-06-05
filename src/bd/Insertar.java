/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import excepciones.Metodos;
import excepciones.ValidacionNombreException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author socamporomani
 */
public class Insertar {

    public static void crear() {
        String url = "jdbc:sqlite:datos.db";
    
        
  String sql = "CREATE TABLE IF NOT EXISTS jugadores (\n"
               + "	nombre text,\n"
               + "      puntos integer);";

                
     try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);   
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            
        }
    
    }
        public static void insercion(String nombre, int puntos) throws ValidacionNombreException {
            try {
            //Declarar consulta
            Conexion.s = Conexion.con.createStatement();
            //Ejecutar consulta
            Conexion.s.executeUpdate("INSERT INTO jugadores values ('" + nombre + "'," + puntos + ")");
            //Confirmacion
            System.out.println("Inserción realizada");
            
        } catch (SQLException ex) {
            System.out.println("ERROR ---> " + ex);
            

}
}
}
