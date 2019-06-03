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

/**
 *
 * @author r618b
 */
public class Mostrar {
  
    public static Connection getConnection(){
        Connection cn=null;
        try {
            cn=DriverManager.getConnection("jdbc:sqlite:datos.db");
        } catch (Exception e) {
            System.out.println(String.valueOf(e));
        }
        return cn;
    }
    public static ResultSet getTabla(String consulta) throws SQLException {
        Connection cn=getConnection();
        Statement st;
        ResultSet datos=null;
        try{
            st=cn.createStatement();
            datos=st.executeQuery(consulta);
        }catch(Exception e){
            System.out.println(e.toString());
        }
    return datos;
    }
    
    
}
