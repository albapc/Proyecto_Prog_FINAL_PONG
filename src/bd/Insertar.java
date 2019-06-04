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
               + "	nombre text);";
  String sql2 = "CREATE TABLE IF NOT EXISTS score (\n"
               + "	puntos integer);";
                
     try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);   
            stmt.execute(sql2); 
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            
        }
    
    }
        public static void insercion() throws ValidacionNombreException {
            String nombre = null;
            String url = "jdbc:sqlite:datos.db";
        String tabla = "jugadores";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        do {
         nombre = JOptionPane.showInputDialog(null, "Introduce el nombre del ganador");
        } while(!Metodos.validarNombre(nombre));
        
        String sql = "INSERT INTO " + tabla + "(nombre) VALUES(?);";
        
         try (Connection connect = conn;
                PreparedStatement pstmt = connect.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            
            pstmt.executeUpdate();           
         }catch(SQLException e) {
            System.out.println(e.getMessage());
             
         }
         
         
}
        public static void insercions(int puntos) {
//            System.out.println(puntos);
            String url = "jdbc:sqlite:datos.db";
        String tabla = "score";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String sql = "INSERT INTO " + tabla + "(puntos) VALUES(?);";
        
         try (Connection connect2 = conn;
                PreparedStatement pstmt2 = connect2.prepareStatement(sql)) {
            pstmt2.setInt(1, puntos);
            
            pstmt2.execute();           
         }catch(SQLException e) {
            System.out.println(e.getMessage());
             
         }
}
}