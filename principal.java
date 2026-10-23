/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ducktimer;

import java.awt.BorderLayout;
import java.awt.*;
import javax.swing.*;
//import org.jvnet.substance.skin.SubstanceFindingNemoLookAndFeel;

/**
 *
 * @author PATO_CODER 15:00
 */

public class principal extends JFrame{
    //ESTRUCTURA GENERAL
    JPanel north,south,oeste,este;
    JPanel o_panel1,o_panel2,o_panel3,o_panel4;
    JDesktopPane center;
    
    //MENU BOTONES
    JButton btn_al,btn_tem,btn_cro,btn_reloj;
    Alarms w_Alarma=null;
    Temporizador w_tem=null;
    Cronometro w_cro=null;
    Reloj w_rel=null;
    
    public principal() throws UnsupportedLookAndFeelException{
        setTitle("duckTimes");
        setSize(900,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //UIManager.setLookAndFeel(new SubstanceFindingNemoLookAndFeel());
        
        addd();
        funn();
        
        setVisible(true);
    }
    
    private void addd(){
        //GENERAL
        north=new JPanel();
        south=new JPanel();
        oeste=new JPanel();
        este=new JPanel();
        center=new JDesktopPane();
        
        //MENU
        o_panel1=new JPanel();
        o_panel2=new JPanel();
        o_panel3=new JPanel();
        o_panel4=new JPanel();
        
        //menu - botones
        btn_al=new JButton("ALARMA");
        btn_al.setBorderPainted(false);
        btn_tem=new JButton("TEMPORIZADOR");
        btn_tem.setBorderPainted(false);
        btn_cro=new JButton("CRONOMETRO");
        btn_cro.setBorderPainted(false);
        btn_reloj=new JButton("RELOJ");
        btn_reloj.setBorderPainted(false);
        
        o_panel1.setLayout(new GridLayout(1, 1));
        o_panel2.setLayout(new GridLayout(1, 1));
        o_panel3.setLayout(new GridLayout(1, 1));
        o_panel4.setLayout(new GridLayout(1, 1));
        
        o_panel1.add(btn_al);
        o_panel2.add(btn_tem);
        o_panel3.add(btn_cro);
        o_panel4.add(btn_reloj);
        
//        //GENERAL
//        north.setBackground(Color.CYAN);
//        south.setBackground(Color.CYAN);
//        oeste.setBackground(Color.CYAN);
//        este.setBackground(Color.CYAN);
//        //center.setBackground(Color.CYAN);
        
//        //MENU
//        o_panel1.setBackground(Color.CYAN);
//        o_panel2.setBackground(Color.CYAN);
//        o_panel3.setBackground(Color.CYAN);
//        o_panel4.setBackground(Color.CYAN);
        
        //GENERAL
        setLayout(new BorderLayout());
        
        //MENU
        oeste.setLayout(new BoxLayout(oeste, BoxLayout.Y_AXIS));
        oeste.add(Box.createVerticalGlue());
        
        //MENU
        JPanel contenedor=new JPanel();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
        //contenedor.setBackground(Color.CYAN);
        
        //MENU
        contenedor.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 30, 5, 30));
        
        //MENU
        Dimension tamaño=new Dimension(150, 50);
        o_panel1.setMaximumSize(tamaño);
        o_panel1.setPreferredSize(tamaño);
        o_panel2.setMaximumSize(tamaño);
        o_panel2.setPreferredSize(tamaño);
        o_panel3.setMaximumSize(tamaño);
        o_panel3.setPreferredSize(tamaño);
        o_panel4.setMaximumSize(tamaño);
        o_panel4.setPreferredSize(tamaño);
        
        //MENU
        o_panel1.setAlignmentX(CENTER_ALIGNMENT);
        o_panel2.setAlignmentX(CENTER_ALIGNMENT);
        o_panel3.setAlignmentX(CENTER_ALIGNMENT);
        o_panel4.setAlignmentX(CENTER_ALIGNMENT);
        
        //MENU
        contenedor.add(o_panel1);
        contenedor.add(Box.createVerticalStrut(10));
        contenedor.add(o_panel2);
        contenedor.add(Box.createVerticalStrut(10));
        contenedor.add(o_panel3);
        contenedor.add(Box.createVerticalStrut(10));
        contenedor.add(o_panel4);
        
        //MENU
        oeste.add(contenedor);
        
        //MENU
        oeste.add(Box.createVerticalGlue());
        
        //MENU
        oeste.setPreferredSize(new Dimension(200, 0));
        
        //GENERAL
        add(north, BorderLayout.NORTH);
        add(south, BorderLayout.SOUTH);
        add(oeste, BorderLayout.WEST);
        add(este, BorderLayout.EAST);
        add(center, BorderLayout.CENTER);
    }
    
    void funn(){
    
        btn_al.addActionListener(l -> { 
            closeAllWindows();
            if(w_Alarma == null){
                w_Alarma=new Alarms();
                w_Alarma.setSize(center.getWidth(), center.getHeight());
                ((javax.swing.plaf.basic.BasicInternalFrameUI)w_Alarma.getUI()).setNorthPane(null);
                w_Alarma.setVisible(true);

                center.add(w_Alarma); 
            }else if(!w_Alarma.isVisible()){
                w_Alarma.setVisible(true);
            }else{
                w_Alarma.setVisible(true);
            }
        });
        
        btn_tem.addActionListener(l -> { 
            closeAllWindows();
            if(w_tem == null){
                w_tem=new Temporizador();
                w_tem.setSize(center.getWidth(), center.getHeight());
                ((javax.swing.plaf.basic.BasicInternalFrameUI)w_tem.getUI()).setNorthPane(null);
                w_tem.setVisible(true);

                center.add(w_tem); 
            }else if(!w_tem.isVisible()){
                w_tem.setVisible(true);
            }else{
                w_tem.dispose();
                w_tem=null;
            }
        });
        
        btn_cro.addActionListener(l -> {
            closeAllWindows();
            if(w_cro == null){
                w_cro=new Cronometro();
                w_cro.setSize(center.getWidth(), center.getHeight());
                ((javax.swing.plaf.basic.BasicInternalFrameUI)w_cro.getUI()).setNorthPane(null);
                w_cro.setVisible(true);

                center.add(w_cro); 
            }else if(!w_cro.isVisible()){
                w_cro.setVisible(true);
            }else{
                w_cro.dispose();
                w_cro=null;
            }
        });
        
        btn_reloj.addActionListener(l -> {
            closeAllWindows();
            if(w_rel == null){
                w_rel=new Reloj();
                w_rel.setSize(center.getWidth(), center.getHeight());
                ((javax.swing.plaf.basic.BasicInternalFrameUI)w_rel.getUI()).setNorthPane(null);
                w_rel.setVisible(true);

                center.add(w_rel); 
            }else if(!w_rel.isVisible()){
                w_rel.setVisible(true);
            }else{
                w_rel.dispose();
                w_rel=null;
            }
        });
    }
    
    private void closeAllWindows(){
    if(w_Alarma!=null){w_Alarma.dispose(); w_Alarma=null;}
    if(w_tem!=null){w_tem.dispose(); w_tem=null;}
    if(w_cro!=null){w_cro.dispose(); w_cro=null;}
    if(w_rel!=null){w_rel.dispose(); w_rel=null;}
}
    
    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        new principal();
    }
}
