/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabiblioteca;
import javax.swing.JOptionPane;
/**
 *
 * @author Valeria
 */
public class MenuSocio {
    
   Socio[] arregloSocios; // Este arreglo viene del main
    int cantidadSocios = 0; // Contador de socios

    // Constructor que recibe el arreglo desde el main
    public MenuSocio(Socio[] arregloSocios) {
        this.arregloSocios = arregloSocios;

        // Cuenta cuántos socios ya hay en el arreglo (si vienen desde DatosIniciales)
        for (Socio s : arregloSocios) {
            if (s != null) {
                cantidadSocios++;
            }
        }
    }

    // submenu principal de socios
    public void mostrarMenuSocios() {
        String textoMenu = "Menu de Socios:\n"
                + "1. Registrar nuevo socio\n"
                + "2. Consultar socio por ID\n"
                + "3. Volver al menu principal";

        String opcionTexto = JOptionPane.showInputDialog(textoMenu);

        int opcionSocio = 0;

        try {
            opcionSocio = Integer.parseInt(opcionTexto);
        } catch (Exception e) {
            // Validacion basica si escriben letras o nada
            JOptionPane.showMessageDialog(null, "Ingrese un numero valido.");
            return;
        }

        // Menu con opciones simples
        switch (opcionSocio) {
            case 1:
                registrarSocio();
                break;
            case 2:
                //consultarSocio();
                break;
            case 3:
                // VUelve al menu principal
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opcion invalida.");
        }
    }

    // Metodo para registrar un nuevo socio
    public void registrarSocio() {
        if (cantidadSocios >= 30) {
            JOptionPane.showMessageDialog(null, "Ya hay 30 socios registrados.");
            return;
        }

        // Se piden los datos al usuario
        String id = JOptionPane.showInputDialog("Ingrese el ID del socio (ej: SOC-A123):");
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre completo:");
        }
}
    
    ///todavia faltan mas metodos...
    
