/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coopetica.modelo;

/**
 * Estrategia de calculo de interes basada en una tasa anual fija simple.
 *
 * @author Gabriel Molina
 */
public class InteresTasaFija implements EstrategiaInteres {

    private final double tasaAnual;

    /**
     * Construye una estrategia de tasa fija.
     *
     * @param tasaAnual Tasa de interes anual en formato decimal (ej. 0.12 para 12%).
     */
    public InteresTasaFija(double tasaAnual) {
        if (tasaAnual < 0) {
            throw new IllegalArgumentException("La tasa de interes no puede ser negativa.");
        }
        this.tasaAnual = tasaAnual;
    }

    /**
     * Obtiene la tasa anual configurada.
     *
     * @return Tasa anual en decimal.
     */
    public double getTasaAnual() {
        return tasaAnual;
    }

    @Override
    public double calcularInteresTotal(double monto, int plazoMeses) {
        double anios = plazoMeses / 12.0;
        return monto * tasaAnual * anios;
    }

    @Override
    public String getNombreEstrategia() {
        return "Tasa Fija (" + (tasaAnual * 100) + "% anual)";
    }

    @Override
    public String toString() {
        return getNombreEstrategia();
    }
}