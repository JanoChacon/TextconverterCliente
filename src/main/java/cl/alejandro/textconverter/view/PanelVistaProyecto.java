/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.alejandro.textconverter.view;

import cl.alejandro.textconverter.logic.Proyecto;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Alejandro
 */
public class PanelVistaProyecto extends JScrollPane{
    
    public String text;
    public JTextArea jtx;


    public PanelVistaProyecto() { 
        this.initComponents();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        jtx.setText(text); 
    }
    
    public void initComponents(){
        
        this.jtx = new JTextArea();         
        this.add(jtx);
        this.setViewportView(jtx);
        this.jtx.setEditable(false);
    }
    

}
