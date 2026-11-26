/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coopetica.modelo;

/**
 * Representa una cuenta corriente operativa sujeta a un cargo fijo por administracion mensual.
 *
 * @author Gabriel Molina
 */
public class CuentaCorriente extends Cuenta {

    private final double comisionMantenimiento;

    /**
     * Construye una cuenta corriente con comision mensual por mantenimiento.
     *
     * @param numeroCuenta Numero unico de la cuenta.
     * @param asociado Asociado titular.
     * @param saldoInicial Saldo de apertura.
     * @param comisionMantenimiento Monto fijo descontado mensualmente.
     */
    public CuentaCorriente(String numeroCuenta, Asociado asociado, double saldoInicial, double comisionMantenimiento) {
        super(numeroCuenta, asociado, saldoInicial);
        if (comisionMantenimiento < 0) {
            throw new IllegalArgumentException("La comision no puede ser negativa.");
        }
        this.comisionMantenimiento = comisionMantenimiento;
    }

    /**
     * Obtiene la comision mensual por mantenimiento.
     *
     * @return Monto de la comision.
     */
    public double getComisionMantenimiento() {
        return comisionMantenimiento;
    }

    @Override
    public double aplicarAjusteMensual() {
        double cobroReal = Math.min(getSaldo(), comisionMantenimiento);
        if (cobroReal > 0) {
            retirar(cobroReal);
        }
        return -cobroReal;
    }

    @Override
    public String getTipoCuenta() {
        return "CORRIENTE";
    }

    @Override
    public String toString() {
        return super.toString() + " | Comision mensual: " + String.format("%.2f", comisionMantenimiento);
    }
}