/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coopetica.modelo;

import java.time.LocalDate;

/**
 * Representa una cuota mensual que pertenece a un prestamo de CoopeTica R.L.
 * Su ciclo de vida depende exclusivamente del prestamo que la genera.
 *
 * @author Gabriel Molina
 */
public class Cuota {

    public static final String ESTADO_PENDIENTE = "PENDIENTE";
    public static final String ESTADO_PAGADA = "PAGADA";
    public static final String ESTADO_ANULADA = "ANULADA";

    private final int numeroCuota;
    private final double monto;
    private final LocalDate fechaVencimiento;
    private String estado;

    /**
     * Construye una cuota mensual.
     *
     * @param numeroCuota Numero consecutivo de la cuota (1, 2, ...).
     * @param monto Monto a pagar en esta cuota.
     * @param fechaVencimiento Fecha limite de pago.
     */
    public Cuota(int numeroCuota, double monto, LocalDate fechaVencimiento) {
        if (numeroCuota <= 0) {
            throw new IllegalArgumentException("El numero de cuota debe ser positivo.");
        }
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto de la cuota debe ser positivo.");
        }
        if (fechaVencimiento == null) {
            throw new IllegalArgumentException("La fecha de vencimiento es obligatoria.");
        }
        this.numeroCuota = numeroCuota;
        this.monto = monto;
        this.fechaVencimiento = fechaVencimiento;
        this.estado = ESTADO_PENDIENTE;
    }

    /**
     * @return Numero de la cuota.
     */
    public int getNumeroCuota() {
        return numeroCuota;
    }

    /**
     * @return Monto de la cuota.
     */
    public double getMonto() {
        return monto;
    }

    /**
     * @return Fecha de vencimiento de la cuota.
     */
    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * @return Estado actual (PENDIENTE, PAGADA o ANULADA).
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Restaura el estado de la cuota al cargar datos desde archivo.
     *
     * @param estado Estado guardado en persistencia.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Marca la cuota como pagada si se encuentra pendiente.
     */
    public void marcarPagada() {
        if (ESTADO_ANULADA.equals(this.estado)) {
            throw new IllegalStateException("No se puede pagar una cuota de un prestamo anulado.");
        }
        if (ESTADO_PAGADA.equals(this.estado)) {
            throw new IllegalStateException("La cuota #" + numeroCuota + " ya fue pagada.");
        }
        this.estado = ESTADO_PAGADA;
    }

    /**
     * Anula la cuota cuando el prestamo padre es anulado.
     */
    public void anular() {
        if (!ESTADO_PAGADA.equals(this.estado)) {
            this.estado = ESTADO_ANULADA;
        }
    }

    @Override
    public String toString() {
        return "Cuota #" + numeroCuota + " | Monto: " + String.format("%.2f", monto)
                + " | Vence: " + fechaVencimiento + " | Estado: " + estado;
    }
}