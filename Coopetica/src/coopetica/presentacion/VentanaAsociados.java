/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coopetica.presentacion;

import coopetica.logica.GestionAsociados;
import coopetica.logica.GestionSucursales;
import coopetica.modelo.Asociado;
import coopetica.modelo.Sucursal;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Ventana para administrar los asociados de CoopeTica R.L.
 * @author Pablo Solano
 */
public class VentanaAsociados extends JFrame {

    private final GestionAsociados gestionAsociados;
    private final GestionSucursales gestionSucursales;

    private JTextField txtCedula;
    private JTextField txtNombre;
    private JTextField txtNumero;
    private JComboBox<Sucursal> cmbSucursal;
    private JTextArea areaResultados;
    
    /**
     * Crear ventana de asociados-
     * 
     * @param gestionAsociados Gestion de asociados.
     * @param gestionSucursales Gestion de sucursales.
     */
    
     public VentanaAsociados(GestionAsociados gestionAsociados, 
             GestionSucursales gestionSucursales) {
         
         this.gestionAsociados = gestionAsociados;
         this.gestionSucursales = gestionSucursales;
         
         setTitle("CoopeTica R.L. - Asociado");
         setSize(600, 450);
         setLocationRelativeTo(null);
         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         
         crearComponentes();
     }
     
/**
 * Crear los componentes de la ventana
 */
     private void crearComponentes() {
         setLayout(new BorderLayout(10,10));
         JPanel panelDatos = new JPanel(new GridLayout(4,2,5,5));
         
         txtCedula = new JTextField();
         txtNombre = new JTextField();
         txtNumero = new JTextField();
         cmbSucursal = new JComboBox<>();
         
         panelDatos.add(new JLabel("Cedula: "));
         panelDatos.add(txtCedula);
         
        panelDatos.add(new JLabel("Nombre: "));
        panelDatos.add(txtNombre);
        
        panelDatos.add(new JLabel("Numero de asociado: "));
        panelDatos.add(txtNumero);
        
        panelDatos.add(new JLabel ("Sucursal: "));
        panelDatos.add(cmbSucursal);
        
        add(panelDatos, BorderLayout.NORTH);
        
        areaResultados = new JTextArea();
        areaResultados.setEditable(false);
        
        add(new JScrollPane(areaResultados), BorderLayout.CENTER);
        
        JPanel panelBotones = new JPanel();
        
        JButton btnRegistrar = new JButton("Registrar.");
        JButton btnBuscar = new JButton ("Buscar por cedula.");
        JButton btnListar = new JButton("Listar todos.");
        JButton btnActualizar = new JButton("Actualizar sucursales.");
        
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnListar);
        panelBotones.add(btnActualizar);
        
        add(panelBotones, BorderLayout.SOUTH);
        
        btnRegistrar.addActionListener(e -> registrar());
        btnBuscar.addActionListener (e -> buscar());
        btnListar.addActionListener (e -> listar());
        btnActualizar.addActionListener (e -> cargaSucursales());
        
        cargaSucursales();
     }
     
/**
 * Carga las sucursales registradas.
 */
    private void cargaSucursales() {
    cmbSucursal.removeAllItems();
         for(Sucursal sucursal : gestionSucursales.listarSucursales()) {
             cmbSucursal.addItem(sucursal);
         }
    }
/**
 * Registra un asociado
 */
    private void registrar() {
        try {
            Sucursal sucursal = 
                    (Sucursal) cmbSucursal.getSelectedItem();
            gestionAsociados.registrarAsociado(
            txtCedula.getText(),
            txtNombre.getText(),
            txtNumero.getText(),
            sucursal);
            
            JOptionPane.showMessageDialog(this, "Asociado registrado correctamente");
            
            txtCedula.setText("");
            txtNombre.setText("");
            txtNumero.setText("");
            
            listar();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Buscar un asociado por cedula.
     */
    private void buscar() {
        String cedula = txtCedula.getText().trim();
        if(cedula.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese una cedula para buscar.");
            return;
        }
    Asociado asociado = 
            gestionAsociados.buscarPorCedula(cedula);
                if(asociado == null) {
                    areaResultados.setText("Asociado no encontrado.");
                } else {
                    areaResultados.setText(asociado.toString());
                }
    }
    /**
     * Muestra todos los asociados registrados
     */
    private void listar() {

        ArrayList<Asociado> lista =
                gestionAsociados.listarAsociados();

        areaResultados.setText("");

        if (lista.isEmpty()) {
            areaResultados.setText(
                    "No hay asociados registrados.");
            return;
        }

        for (Asociado asociado : lista) {
            areaResultados.append(asociado + "\n");
        }
    }
}


    

