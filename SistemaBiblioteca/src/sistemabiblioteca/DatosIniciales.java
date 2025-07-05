package sistemabiblioteca;
import java.util.Random;


public class DatosIniciales {
    public static String[] nombres = {"Carlos", "Maria", "Luis", "Ana", "Jorge", "Lucia"};
    public static String[] apellidos = {"Sotos", "Ramirez", "Lopez", "Gomez", "Diaz", "Vargas"};

    // Generar nombre aleatorio
    public static String generarNombreCompleto() {
        Random rand = new Random();
        String nombre = nombres[rand.nextInt(nombres.length)];
        String apellido = apellidos[rand.nextInt(apellidos.length)];
        return nombre + " " + apellido;
    }

    // Generar ID de socio unico aleatorio como "SOC-A0B1"
    public static String generarIdSocio() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder id = new StringBuilder();
        Random rand = new Random();

        for (int i = 0; i < 4; i++) {
            int indice = rand.nextInt(caracteres.length());
            id.append(caracteres.charAt(indice));
        }

        return "SOC-" + id;
    }
}  

