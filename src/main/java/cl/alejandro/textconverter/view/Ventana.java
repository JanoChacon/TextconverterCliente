/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.alejandro.textconverter.view;

import cl.alejandro.textconverter.logic.Archivo;
import cl.alejandro.textconverter.logic.Paquete;
import cl.alejandro.textconverter.logic.Proyecto;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author Alejandro
 */
public class Ventana  extends JFrame implements ActionListener{

    public PanelProyectos panelproyectos;
    public BarraMenu barramenu;
    public PanelVistaProyecto vistaproyecto;

    public Ventana() {
        this.initComponents();
    }
    
    public void initComponents(){

        this.vistaproyecto = new PanelVistaProyecto();
        this.panelproyectos = new PanelProyectos(this.vistaproyecto);
        this.barramenu = new BarraMenu(panelproyectos);
        
        this.add(this.panelproyectos);
        this.add(this.barramenu);
        this.add(this.vistaproyecto);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Gestor de Proyectos");
        this.setSize(800, 600);
        this.setLocation(120, 120);
        this.setVisible(true);
        
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelproyectos, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(vistaproyecto)
                        .addGap(33, 33, 33))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(barramenu.crearProyecto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(barramenu.crearPaquete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(barramenu.cargarArchivos)
                        .addContainerGap(383, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(barramenu.crearProyecto)
                    .addComponent(barramenu.crearPaquete)

                    .addComponent(barramenu.cargarArchivos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(vistaproyecto)
                    .addComponent(panelproyectos, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        pack();
    }
   
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
