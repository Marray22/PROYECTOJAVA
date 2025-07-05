package SistemaBiblioteca;
import javax.swing.JOptionPane; 
import sistemabiblioteca.libro.Libro;
/* Estudiantes:
   Valeria Ledezma
   Amanda Calderón 
   Michael Muñoz

 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          while (true) {
            String menu = "Seleccione una opción:\n"
                        + "1. Biblioteca\n"
                        + "2. Préstamos\n"
                        + "3. Catálogo\n"
                        + "4. Socios\n"
                        + "5. Reportes\n"
                        + "6. Salir";

            String opcionMenu = JOptionPane.showInputDialog(null, menu, "Menú Principal", JOptionPane.QUESTION_MESSAGE);

            if (opcionMenu == null) { // Usuario presionó Cancelar
                break;
            }

            int opcion = 0;
            try {
                opcion = Integer.parseInt(opcionMenu);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un número válido", "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            switch (opcion) {
                case 1:
                    JOptionPane.showMessageDialog(null, "Biblioteca");
                    
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Préstamos");
                    
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Catálogo");
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Socios");
                    break;
               
                case 5:
                    JOptionPane.showMessageDialog(null, "Reportes");
                    break;
                case 6:
                    JOptionPane.showMessageDialog(null, "Salir");
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida");
                    break;
            }
        }
    }
}