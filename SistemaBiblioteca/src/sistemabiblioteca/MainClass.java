package SistemaBiblioteca;
import javax.swing.JOptionPane; 
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
        String[] opciones = {"Biblioteca", "Préstamos", "Catálogo", "Socios", "Reportes", "Salir"};

        while (true) {
            int seleccion = JOptionPane.showOptionDialog(
                null, 
                "Seleccione una opción:", 
                "Menú Principal",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opciones,
                opciones[0]
            );

            if (seleccion == 0) {
                System.out.println("Biblioteca seleccionada");
                // Llama al método o clase Biblioteca aquí
            } else if (seleccion == 1) {
                System.out.println("Préstamos seleccionada");
                // Llama al método o clase Préstamos aquí
            } else if (seleccion == 2) {
                System.out.println("Catálogo seleccionada");
                // Llama al método o clase Catálogo aquí
            } else if (seleccion == 3) {
                System.out.println("Socios seleccionada");
                // Llama al método o clase Socios aquí
            } else if (seleccion == 4) {
                System.out.println("Reportes seleccionada");
                // Llama al método o clase Reportes aquí
            } else if (seleccion == 5 || seleccion == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(null, "Gracias por usar el sistema. ¡Hasta luego!");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Opción no válida");
            }
        }
    }
}