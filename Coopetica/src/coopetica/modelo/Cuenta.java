/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coopetica.modelo;

import java.util.Objects;

/**
 * Representa una cuenta financiera de un asociado en CoopeTica R.L.
 * Define los atributos y operaciones comunes para los distintos tipos de cuenta.
 *
 * @author Gabriel Molina
 */
public abstract class Cuenta {

    private final String numeroCuenta;
    private final Asociado asociado;
    private double saldo;

    /**
     * Crea una cuenta base asociada a un miembro de la cooperativa.
     *
     * @param numeroCuenta Identificador unico de la cuenta.
     * @param asociado Asociado titular de la cuenta.
     * @param saldoInicial Saldo inicial con el que se abre la cuenta.
     */
    public Cuenta(String numeroCuenta, Asociado asociado, double saldoInicial) {
        if (numeroCuenta == null || numeroCuenta.isBlank()) {
            throw new IllegalArgumentException("El numero de cuenta es obligatorio.");
        }
        if (asociado == null) {
            throw new IllegalArgumentException("La cuenta debe pertenecer a un asociado.");
        }
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo.");
        }
        this.numeroCuenta = numeroCuenta.trim();
        this.asociado = asociado;
        this.saldo = saldoInicial;
    }

    /**
     * Obtiene el numero unico de la cuenta.
     *
     * @return Numero de cuenta.
     */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * Obtiene el asociado titular de la cuenta.
     *
     * @return Asociado propietario.
     */
    public Asociado getAsociado() {
        return asociado;
    }

    /**
     * Obtiene el saldo disponible en la cuenta.
     *
     * @return Saldo actual.
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * Realiza un deposito en la cuenta validando que el monto sea positivo.
     *
     * @param monto Cantidad de dinero a depositar.
     */
    public void depositar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser mayor a cero.");
        }
        this.saldo += monto;
    }

    /**
     * Realiza un retiro de la cuenta validando monto positivo y fondos suficientes.
     *
     * @param monto Cantidad de dinero a retirar.
     */
    public void retirar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser mayor a cero.");
        }
        if (monto > this.saldo) {
            throw new IllegalArgumentException("Fondos insuficientes. Saldo disponible: " + this.saldo);
        }
        this.saldo -= monto;
    }

    /**
     * Metodo que aplica el ajuste mensual propio de cada tipo de cuenta.
     *
     * @return Monto del ajuste aplicado (positivo si suma, negativo si resta).
     */
    public abstract double aplicarAjusteMensual();

    /**
     * Devuelve el tipo especifico de la cuenta ("AHORRO" o "CORRIENTE").
     *
     * @return Identificador del tipo de cuenta.
     */
    public abstract String getTipoCuenta();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Cuenta otra = (Cuenta) obj;
        return Objects.equals(this.numeroCuenta, otra.numeroCuenta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroCuenta);
    }

    @Override
    public String toString() {
        return "[" + getTipoCuenta() + "] Cuenta: " + numeroCuenta
                + " | Titular: " + asociado.getNombre() + " (" + asociado.getCedula() + ")"
                + " | Saldo: " + String.format("%.2f", saldo);
    }
}