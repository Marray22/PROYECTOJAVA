
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package sistemabiblioteca;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

    
enum EstadoSocio{
        ACTIVO,
        MOROSO,
        SUSPENDIDO,
        INACTIVO
}

    
public class Socio {
    
    
    private String idSocio;
    private String nombreCompleto;
    private String fechaRegistro;
    private EstadoSocio estadoSocio;
    private double multasAcumuladas;
    private int cantidadLibrosPrestadosActual;

    // Constructor
    public Socio(String nombreCompleto) {
        this.idSocio = generarIdAleatorio();
        this.nombreCompleto = nombreCompleto;
        this.fechaRegistro = obtenerFechaActual();
        this.estadoSocio = EstadoSocio.ACTIVO;
        this.multasAcumuladas = 0.0;
        this.cantidadLibrosPrestadosActual = 0;
    }

    // Generar ID tipo SOC-XXXX
    private String generarIdAleatorio() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder id = new StringBuilder("SOC-");
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(caracteres.length());
            id.append(caracteres.charAt(index));
        }
        return id.toString();
    }

    // Fecha actual con formato
    private String obtenerFechaActual() {
        Date ahora = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return formato.format(ahora);
    }

    // Getters y Setters
    public String getIdSocio() {
        return idSocio;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public EstadoSocio getEstadoSocio() {
        return estadoSocio;
    }

    public void setEstadoSocio(EstadoSocio estadoSocio) {
        this.estadoSocio = estadoSocio;
    }

    public double getMultasAcumuladas() {
        return multasAcumuladas;
    }

    public void setMultasAcumuladas(double multasAcumuladas) {
        this.multasAcumuladas = multasAcumuladas;
    }

    public int getCantidadLibrosPrestadosActual() {
        return cantidadLibrosPrestadosActual;
    }

    public void setCantidadLibrosPrestadosActual(int cantidadLibrosPrestadosActual) {
        this.cantidadLibrosPrestadosActual = cantidadLibrosPrestadosActual;
    }

    // Mostrar información
    public void mostrarInformacion() {
        System.out.println("ID Socio: " + idSocio);
        System.out.println("Nombre: " + nombreCompleto);
        System.out.println("Fecha de registro: " + fechaRegistro);
        System.out.println("Estado: " + estadoSocio);
        System.out.println("Multas acumuladas: " + multasAcumuladas);
        System.out.println("Libros prestados actualmente: " + cantidadLibrosPrestadosActual);
        System.out.println("-------------------------------");
    }
    

}
