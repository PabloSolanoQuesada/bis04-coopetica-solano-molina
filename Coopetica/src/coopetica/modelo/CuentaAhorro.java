/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coopetica.modelo;

/**
 * Representa una cuenta de ahorro que genera intereses mensuales sobre el saldo.
 *
 * @author Gabriel Molina
 */
public class CuentaAhorro extends Cuenta {

    private final double tasaInteresMensual;

    /**
     * Construye una cuenta de ahorro con tasa de interes mensual.
     *
     * @param numeroCuenta Numero unico de la cuenta.
     * @param asociado Asociado titular.
     * @param saldoInicial Saldo de apertura.
     * @param tasaInteresMensual Tasa mensual en decimal (ej. 0.01 para 1%).
     */
    public CuentaAhorro(String numeroCuenta, Asociado asociado, double saldoInicial, double tasaInteresMensual) {
        super(numeroCuenta, asociado, saldoInicial);
        if (tasaInteresMensual < 0) {
            throw new IllegalArgumentException("La tasa de interes no puede ser negativa.");
        }
        this.tasaInteresMensual = tasaInteresMensual;
    }

    /**
     * Obtiene la tasa de interes mensual de la cuenta.
     *
     * @return Tasa mensual en decimal.
     */
    public double getTasaInteresMensual() {
        return tasaInteresMensual;
    }

    @Override
    public double aplicarAjusteMensual() {
        double interesGanado = getSaldo() * tasaInteresMensual;
        if (interesGanado > 0) {
            depositar(interesGanado);
        }
        return interesGanado;
    }

    @Override
    public String getTipoCuenta() {
        return "AHORRO";
    }

    @Override
    public String toString() {
        return super.toString() + " | Tasa mensual: " + (tasaInteresMensual * 100) + "%";
    }
}