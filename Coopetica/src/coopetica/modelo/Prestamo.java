/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coopetica.modelo;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Representa un prestamo otorgado a un asociado de CoopeTica R.L.
 * Genera automaticamente sus cuotas mensuales mediante composicion.
 *
 * @author Gabriel Molina
 */
public class Prestamo {

    public static final String ESTADO_ACTIVO = "ACTIVO";
    public static final String ESTADO_CANCELADO = "CANCELADO";
    public static final String ESTADO_ANULADO = "ANULADO";

    private final String codigoPrestamo;
    private final Asociado asociado;
    private final double montoPrincipal;
    private final int plazoMeses;
    private final LocalDate fechaSolicitud;
    private final EstrategiaInteres estrategiaInteres;
    private final ArrayList<Cuota> cuotas;
    private String estado;

    /**
     * Crea un nuevo prestamo activo y genera automaticamente todas sus cuotas mensuales.
     *
     * @param codigoPrestamo Codigo identificador unico del prestamo.
     * @param asociado Asociado solicitante.
     * @param montoPrincipal Monto solicitado.
     * @param plazoMeses Cantidad de meses para pagar el prestamo.
     * @param fechaSolicitud Fecha de aprobacion del prestamo.
     * @param estrategiaInteres Estrategia utilizada para calcular intereses.
     */
    public Prestamo(String codigoPrestamo, Asociado asociado, double montoPrincipal,
            int plazoMeses, LocalDate fechaSolicitud, EstrategiaInteres estrategiaInteres) {
        if (codigoPrestamo == null || codigoPrestamo.isBlank()) {
            throw new IllegalArgumentException("El codigo del prestamo es obligatorio.");
        }
        if (asociado == null) {
            throw new IllegalArgumentException("Debe indicar el asociado del prestamo.");
        }
        if (montoPrincipal <= 0) {
            throw new IllegalArgumentException("El monto del prestamo debe ser mayor a cero.");
        }
        if (plazoMeses <= 0) {
            throw new IllegalArgumentException("El plazo en meses debe ser mayor a cero.");
        }
        if (fechaSolicitud == null || estrategiaInteres == null) {
            throw new IllegalArgumentException("La fecha y la estrategia de interes son obligatorias.");
        }

        this.codigoPrestamo = codigoPrestamo.trim();
        this.asociado = asociado;
        this.montoPrincipal = montoPrincipal;
        this.plazoMeses = plazoMeses;
        this.fechaSolicitud = fechaSolicitud;
        this.estrategiaInteres = estrategiaInteres;
        this.estado = ESTADO_ACTIVO;
        this.cuotas = new ArrayList<>();

        generarCuotas();
    }

    /**
     * Genera automaticamente una cuota por cada mes del plazo.
     */
    private void generarCuotas() {
        double interesTotal = estrategiaInteres.calcularInteresTotal(montoPrincipal, plazoMeses);
        double montoTotal = montoPrincipal + interesTotal;
        double montoCuota = Math.round((montoTotal / plazoMeses) * 100.0) / 100.0;

        for (int i = 1; i <= plazoMeses; i++) {
            LocalDate vencimiento = fechaSolicitud.plusMonths(i);
            cuotas.add(new Cuota(i, montoCuota, vencimiento));
        }
    }

    /**
     * Registra el pago de una cuota especifica y actualiza el estado del prestamo
     * a CANCELADO si todas las cuotas quedaron pagadas.
     *
     * @param numeroCuota Numero de cuota a pagar.
     */
    public void pagarCuota(int numeroCuota) {
        if (!ESTADO_ACTIVO.equals(this.estado)) {
            throw new IllegalStateException("Solo se pueden pagar cuotas de un prestamo ACTIVO.");
        }
        for (Cuota cuota : cuotas) {
            if (cuota.getNumeroCuota() == numeroCuota) {
                cuota.marcarPagada();
                verificarCancelacionTotal();
                return;
            }
        }
        throw new IllegalArgumentException("No existe la cuota #" + numeroCuota + " en este prestamo.");
    }

    /**
     * Verifica si todas las cuotas estan pagadas para cerrar el prestamo.
     */
    private void verificarCancelacionTotal() {
        for (Cuota cuota : cuotas) {
            if (!Cuota.ESTADO_PAGADA.equals(cuota.getEstado())) {
                return;
            }
        }
        this.estado = ESTADO_CANCELADO;
    }

    /**
     * Anula el prestamo y en cascada anula todas sus cuotas pendientes.
     */
    public void anularPrestamo() {
        if (ESTADO_CANCELADO.equals(this.estado)) {
            throw new IllegalStateException("No se puede anular un prestamo ya cancelado.");
        }
        this.estado = ESTADO_ANULADO;
        for (Cuota cuota : cuotas) {
            cuota.anular();
        }
    }

    /**
     * Calcula el saldo pendiente sumando las cuotas que aun no se han pagado.
     *
     * @return Saldo pendiente total.
     */
    public double calcularSaldoPendiente() {
        if (ESTADO_ANULADO.equals(this.estado)) {
            return 0.0;
        }
        double pendiente = 0.0;
        for (Cuota cuota : cuotas) {
            if (Cuota.ESTADO_PENDIENTE.equals(cuota.getEstado())) {
                pendiente += cuota.getMonto();
            }
        }
        return pendiente;
    }

    /**
     * @return Codigo del prestamo.
     */
    public String getCodigoPrestamo() {
        return codigoPrestamo;
    }

    /**
     * @return Asociado titular del prestamo.
     */
    public Asociado getAsociado() {
        return asociado;
    }

    /**
     * @return Monto principal solicitado.
     */
    public double getMontoPrincipal() {
        return montoPrincipal;
    }

    /**
     * @return Plazo en meses.
     */
    public int getPlazoMeses() {
        return plazoMeses;
    }

    /**
     * @return Fecha en que se solicito el prestamo.
     */
    public LocalDate getFechaSolicitud() {
        return fechaSolicitud;
    }

    /**
     * @return Estrategia de interes aplicada.
     */
    public EstrategiaInteres getEstrategiaInteres() {
        return estrategiaInteres;
    }

    /**
     * @return Estado actual del prestamo (ACTIVO, CANCELADO, ANULADO).
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Restaura el estado del prestamo al cargar desde persistencia.
     *
     * @param estado Estado guardado.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Devuelve la lista de cuotas del prestamo.
     *
     * @return Lista de cuotas.
     */
    public ArrayList<Cuota> getCuotas() {
        return cuotas;
    }

    @Override
    public String toString() {
        return "Prestamo " + codigoPrestamo + " | Asociado: " + asociado.getNombre()
                + " | Monto: " + String.format("%.2f", montoPrincipal)
                + " | Plazo: " + plazoMeses + " meses | Estado: " + estado
                + " | Saldo pendiente: " + String.format("%.2f", calcularSaldoPendiente());
    }
}