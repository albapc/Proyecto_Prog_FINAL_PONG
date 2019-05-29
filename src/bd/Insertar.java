/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                + "	nombre2 text);";
                
     try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);       
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            
        }
     insercion();
    }
        public static void insercion() {

            String url = "jdbc:sqlite:datos.db";
        String tabla = "jugadores";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String nombre = JOptionPane.showInputDialog(null, "Introduce El nombre del jugador");
        String nombre2 = JOptionPane.showInputDialog(null,"Jugador 2:", "IA");

        String sql = "INSERT INTO " + tabla + "(nombre, nombre2) VALUES(?,?)";
        
         try (Connection connect = conn;
                PreparedStatement pstmt = connect.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, nombre2);
            pstmt.executeUpdate();           
         }catch(SQLException e) {
            System.out.println(e.getMessage());
             
         }
}
}