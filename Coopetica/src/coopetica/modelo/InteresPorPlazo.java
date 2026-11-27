/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coopetica.modelo;

/**
 * Estrategia de calculo de interes escalonada segun el plazo en meses:
 * hasta 12 meses aplica 10% anual, de 13 a 36 meses aplica 14% anual,
 * y mas de 36 meses aplica 18% anual.
 *
 * @author Gabriel Molina
 */
public class InteresPorPlazo implements EstrategiaInteres {

    @Override
    public double calcularInteresTotal(double monto, int plazoMeses) {
        double tasaAnual;
        if (plazoMeses <= 12) {
            tasaAnual = 0.10;
        } else if (plazoMeses <= 36) {
            tasaAnual = 0.14;
        } else {
            tasaAnual = 0.18;
        }
        double anios = plazoMeses / 12.0;
        return monto * tasaAnual * anios;
    }

    @Override
    public String getNombreEstrategia() {
        return "Tasa Escalonada por Plazo";
    }

    @Override
    public String toString() {
        return getNombreEstrategia();
    }
}
