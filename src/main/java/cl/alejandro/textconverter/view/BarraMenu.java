/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.alejandro.textconverter.view;

import cl.alejandro.textconverter.logic.Proyecto;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Alejandro
 */
public class BarraMenu extends JPanel {

    public JButton crearProyecto;
    public JButton crearPaquete;
    public JButton cargarArchivos;
    public PanelProyectos pnp;

    public BarraMenu(PanelProyectos pnp) {
        this.pnp = pnp;
        this.initComponents();
    }

    public void initComponents() {

        this.crearProyecto = new JButton("Crear Proyecto");
        this.crearPaquete = new JButton("Crear Paquete");
        this.cargarArchivos = new JButton("Cargar Archivo");

        crearProyecto.setText("Crear Proyecto");
        crearProyecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearProyectoActionPerformed(evt);
            }
        });

        crearPaquete.setText("Crear Paquete");
        crearPaquete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearPaqueteActionPerformed(evt);
            }
        });

        cargarArchivos.setText("Cargar Archivos");
        cargarArchivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarArchivosActionPerformed(evt);
            }
        });

        this.add(crearProyecto);
        this.add(crearPaquete);
        this.add(cargarArchivos);
    }

    //sin conexion no hay fiesta :(
    private void crearProyectoActionPerformed(java.awt.event.ActionEvent evt) {
        String ax = "";
        ax = JOptionPane.showInputDialog("Ingrese un nombre: ");
        if (!ax.equalsIgnoreCase("")) {
            Proyecto pro = new Proyecto(ax);
            pnp.proyectos.add(pro);
            pnp.cargarProyectos();
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese un nombre correcto");
        }

    }

    private void crearPaqueteActionPerformed(java.awt.event.ActionEvent evt) {
        
        JComboBox combo = new JComboBox();
        for (int i = 0; i < pnp.proyectos.size(); i++) {
            combo.addItem(pnp.proyectos.get(i).getNombre());
        }
        String ax = JOptionPane.showInputDialog(combo);
        combo.addActionListener((ActionEvent e) -> {
            
        String select = combo.getSelectedItem().toString();
            
        });
        
        System.out.println(ax);
    }

    private void cargarArchivosActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
}
