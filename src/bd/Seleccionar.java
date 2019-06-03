
package bd;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author aperezcesar
 */
public class Seleccionar {
    
    public ArrayList seleccionarTodo() {
        ArrayList<Object> aux = new ArrayList<>();
        Object[] datos = new Object[4];
        try {
            Conndatos.conectar();
            //Declarar consulta
            Conndatos.s = Conndatos.con.createStatement();
            //Ejecutar consulta
            Conndatos.rs = Conndatos.s.executeQuery("select * from jugadores");
            while (Conndatos.rs.next()) {
                      datos[0] = Conndatos.rs.getString("nombre");
                      datos[1] = Conndatos.rs.getString("score");
                      aux.add(datos);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR ---> " + ex);
        } 
        return aux;
    }
}
