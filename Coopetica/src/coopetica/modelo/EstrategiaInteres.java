/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coopetica.modelo;

/**
 * Define el contrato para las estrategias de calculo de interes en prestamos.
 * Permite intercambiar politicas de calculo sin modificar la clase Prestamo.
 *
 * @author Gabriel Molina
 */
public interface EstrategiaInteres {

    /**
     * Calcula el monto total de intereses para un prestamo dado.
     *
     * @param monto Monto principal del prestamo.
     * @param plazoMeses Cantidad de meses del prestamo.
     * @return Monto total de intereses generados.
     */
    double calcularInteresTotal(double monto, int plazoMeses);

    /**
     * Devuelve el nombre descriptivo de la politica de interes.
     *
     * @return Nombre de la estrategia.
     */
    String getNombreEstrategia();
}