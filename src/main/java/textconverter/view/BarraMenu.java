/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textconverter.view;

import textconverter.logic.Proyecto;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import persistencia.dao.ProyectoDao;
import persistencia.factory.DAOFactory;
import persistencia.factory.TipoBD;
import textconverter.logic.Archivo;
import textconverter.logic.Paquete;

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
        boolean ocupado = false;
        for (int i = 0; i < pnp.proyectos.size(); i++) {
            if (ax.toString().equals(pnp.proyectos.get(i).getNombre())) {
                ocupado = true;
            }
        }

        if (!ax.equalsIgnoreCase("") && !ocupado) {
            Proyecto pro = new Proyecto();
            pro.setNombre(ax);
            pnp.guardarProyecto(pro);
            pnp.cargarProyectos();

        } else {
            JOptionPane.showMessageDialog(null, "Ingrese un nombre correcto");
        }

    }

    private void crearPaqueteActionPerformed(java.awt.event.ActionEvent evt) {

        JComboBox combo = new JComboBox();

        String ax = "";
        boolean ocupado = false;
        int index = 0;
        for (int i = 0; i < pnp.proyectos.size(); i++) {
            combo.addItem(pnp.proyectos.get(i).getNombre());
        }
        ax = JOptionPane.showInputDialog(null, combo);
        combo.addActionListener((ActionEvent e) -> {
        });

        String select = combo.getSelectedItem().toString();

        for (int i = 0; i < pnp.proyectos.size(); i++) {
            for (int j = 0; j < pnp.proyectos.get(i).getPaquetes().size(); j++) {
                if (ax.toString().equals(pnp.proyectos.get(i).getPaquete(j).getNombre())) {
                    ocupado = true;
                }
            }
        }

        for (int i = 0; i < pnp.proyectos.size(); i++) {
            if (combo.getSelectedItem().toString().equals(pnp.proyectos.get(i).getNombre())) {
                index = pnp.proyectos.get(i).getId();
            }
        }

        if (ocupado == false) {
            Paquete paq = new Paquete();
            paq.setNombre(ax);
            pnp.guardarPaquete(paq, index);
            pnp.cargarProyectos();
        }else {
            JOptionPane.showMessageDialog(null, "Ingrese un nombre correcto");
        }

    }

    private void cargarArchivosActionPerformed(java.awt.event.ActionEvent evt) {
        JComboBox combo = new JComboBox();

        String ax = "";
        boolean ocupado = false;
        int index = 0;

        for (int i = 0; i < pnp.proyectos.size(); i++) {
            for (int j = 0; j < pnp.proyectos.get(i).getPaquetes().size(); j++) {
                combo.addItem(pnp.proyectos.get(i).getPaquetes().get(j).getNombre());
            }
        }

        ax = JOptionPane.showInputDialog(null, combo);
        combo.addActionListener((ActionEvent e) -> {
        });

        String select = combo.getSelectedItem().toString();

        for (int i = 0; i < pnp.proyectos.size(); i++) {
            for (int j = 0; j < pnp.proyectos.get(i).getPaquetes().size(); j++) {
                for (int k = 0; k < pnp.proyectos.get(i).getPaquetes().get(j).getArchivos().size(); k++) {
                    if (ax.toString().equals(pnp.proyectos.get(i).getPaquetes().get(j).getArchivos().get(k).getNombre())) {
                        ocupado = true;
                    }
                }
            }
        }

        for (int i = 0; i < pnp.proyectos.size(); i++) {
            for (int j = 0; j < pnp.proyectos.get(i).getPaquetes().size(); j++) {
                if (combo.getSelectedItem().toString().equals(pnp.proyectos.get(i).getPaquetes().get(j).getNombre())) {
                    index = pnp.proyectos.get(i).getId();
                }

            }
        }
        if (ocupado == false) {
            Archivo arch = new Archivo();
            arch.setNombre(ax);
            pnp.guardarArchivo(arch, index);
            //pnp.setTransformPDF();
            pnp.cargarProyectos();
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese un nombre correcto");
        }

    }
}
