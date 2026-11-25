/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coopetica.presentacion;

import coopetica.logica.GestionSucursales;
import coopetica.modelo.Sucursal;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;

/**
 * Ventana para administrar las sucursales de CoopeTica R.L.
 *
 * @author Pablo Solano
 */
public class VentanaSucursales extends JFrame {

    private final GestionSucursales gestion;
    private final JTextField campoCodigo;
    private final JTextField campoNombre;
    private final JTextArea areaResultados;

    /**
     * Construye la ventana de sucursales.
     *
     * @param gestion Gestor que administra las sucursales.
     */
    public VentanaSucursales(GestionSucursales gestion) {

        this.gestion = gestion;

        setTitle("CoopeTica R.L. - Sucursales");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel para ingresar los datos.
        JPanel formulario = new JPanel(new GridLayout(2, 2, 10, 10));

        campoCodigo = new JTextField(15);
        campoNombre = new JTextField(15);

        formulario.add(new JLabel("Codigo:"));
        formulario.add(campoCodigo);
        formulario.add(new JLabel("Nombre:"));
        formulario.add(campoNombre);

        // Panel con los botones.
        JPanel panelBotones = new JPanel();

        JButton botonRegistrar = new JButton("Registrar");
        JButton botonBuscar = new JButton("Buscar");
        JButton botonListar = new JButton("Listar");

        panelBotones.add(botonRegistrar);
        panelBotones.add(botonBuscar);
        panelBotones.add(botonListar);

        // Agrupar formulario y botones.
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(formulario, BorderLayout.CENTER);
        panelSuperior.add(panelBotones, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);

        // Area donde se muestran los resultados.
        areaResultados = new JTextArea(12, 40);
        areaResultados.setEditable(false);

        add(new JScrollPane(areaResultados), BorderLayout.CENTER);

        // Conectar los botones con sus metodos.
        botonRegistrar.addActionListener(e -> registrar());
        botonBuscar.addActionListener(e -> buscar());
        botonListar.addActionListener(e -> listar());

        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Registra una sucursal con los datos ingresados.
     */
    private void registrar() {

        String codigo = campoCodigo.getText().trim();
        String nombre = campoNombre.getText().trim();

        try {
            boolean registrado = gestion.registrarSucursal(codigo, nombre);

            if (registrado) {
                areaResultados.setText("Sucursal registrada correctamente.\n");
                campoCodigo.setText("");
                campoNombre.setText("");
                campoCodigo.requestFocus();
                listar();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Ya existe una sucursal con ese codigo.",
                        "Codigo duplicado",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Datos incorrectos",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /**
     * Busca una sucursal por su codigo.
     */
    private void buscar() {

        String codigo = campoCodigo.getText().trim();

        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese el codigo de la sucursal."
            );
            return;
        }

        Sucursal encontrada = gestion.buscarSucursal(codigo);

        if (encontrada != null) {
            areaResultados.setText(
                    "Sucursal encontrada:\n" + encontrada
            );
        } else {
            areaResultados.setText("No se encontro la sucursal.");
        }
    }

    /**
     * Muestra todas las sucursales registradas.
     */
    private void listar() {

        areaResultados.setText("=== SUCURSALES REGISTRADAS ===\n\n");

        if (gestion.listarSucursales().isEmpty()) {
            areaResultados.append("No hay sucursales registradas.");
            return;
        }

        for (Sucursal sucursal : gestion.listarSucursales()) {
            areaResultados.append(sucursal + "\n");
        }
    }
}
