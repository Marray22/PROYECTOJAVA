/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package sistemabiblioteca;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Prestamo {
    public enum EstadoPrestamo {
        ACTIVO,
        DEVUELTO_A_TIEMPO,
        DEVUELTO_CON_RETRASO,
        CANCELADO_NO_RETIRADO
    }

    private static int contador = 1000;
    private static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

    private int idPrestamo;
    private Socio socio;
    private Libro libro;
    private String fechaPrestamo;
    private String fechaDevolucionEstimada;
    private String fechaDevolucionReal; 
    private EstadoPrestamo estadoPrestamo;
    private double multaGeneradaEstePrestamo;

    public Prestamo(Socio socio, Libro libro) {
        this.idPrestamo = contador++;
        this.socio = socio;
        this.libro = libro;

        Date hoy = new Date();
        this.fechaPrestamo = formato.format(hoy);

        Calendar calendario = Calendar.getInstance();
        calendario.setTime(hoy);
        calendario.add(Calendar.DAY_OF_MONTH, 15);
        this.fechaDevolucionEstimada = formato.format(calendario.getTime());

        this.fechaDevolucionReal = null;
        this.estadoPrestamo = EstadoPrestamo.ACTIVO;
        this.multaGeneradaEstePrestamo = 0.0;

        libro.setEstadoLibro(EstadoLibro.PRESTADO);
    }

    public void devolver(String fechaRealStr) {
        try {
            this.fechaDevolucionReal = fechaRealStr;

            Date fechaEstimada = formato.parse(fechaDevolucionEstimada);
            Date fechaReal = formato.parse(fechaRealStr);

            long diferenciaMs = fechaReal.getTime() - fechaEstimada.getTime();
            long diasRetraso = diferenciaMs / (1000 * 60 * 60 * 24);

            if (diasRetraso > 0) {
                this.estadoPrestamo = EstadoPrestamo.DEVUELTO_CON_RETRASO;
                this.multaGeneradaEstePrestamo = diasRetraso * 100.0; 
            } else {
                this.estadoPrestamo = EstadoPrestamo.DEVUELTO_A_TIEMPO;
            }

            libro.setEstadoLibro(EstadoLibro.DISPONIBLE);

        } catch (ParseException e) {
            System.out.println("Error: Fecha de devolución inválida.");
        }
    }
    
  
    public int getIdPrestamo() {
        return idPrestamo;
    }

    public EstadoPrestamo getEstadoPrestamo() {
        return estadoPrestamo;
    }

    public double getMultaGeneradaEstePrestamo() {
        return multaGeneradaEstePrestamo;
    }
}


