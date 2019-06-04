
package excepciones;

public class Metodos {
    
    public static boolean validarNombre(String str) throws ValidacionNombreException{
        if (str == null || str.equals("")) {
            return false;
            
        }
        for (int i = 0; i < str.length(); i++) { //recorre todos los caracteres para asegurarse de que solo hay letras
            char ch = str.charAt(i);
            if ((!(ch >= 'A' && ch <= 'Z'))
                    && (!(ch >= 'a' && ch <= 'z'))) {
                return false;
            }
        }
        return true;
    }
}
