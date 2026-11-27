/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package coopetica.presentacion;

import coopetica.logica.GestionSucursales;
import coopetica.logica.GestionAsociados;
import coopetica.logica.GestionAportes;
import javax.swing.SwingUtilities;

/**
 * Clase principal del sistema CoopeTica R.L.
 */
public class Main {

    /**
     * Inicia la aplicación
     *
     * @param args Argumentos de ejecucion.
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            GestionSucursales gestionSucursales =
                    new GestionSucursales();

            GestionAsociados gestionAsociados =
                    new GestionAsociados();
            
            GestionAportes gestionAportes =
                    new GestionAportes();

            VentanaSucursales ventanaSucursales =
                    new VentanaSucursales(gestionSucursales);

            VentanaAsociados ventanaAsociados =
                    new VentanaAsociados(
                            gestionAsociados,
                            gestionSucursales);
            
            VentanaAportes ventanaAportes =
                    new VentanaAportes(
                        gestionAportes,
                        gestionAsociados);

            ventanaSucursales.setVisible(true);
            ventanaAsociados.setVisible(true);
            ventanaAportes.setVisible(true);
        });
    }
}

            
       
        
    

     

