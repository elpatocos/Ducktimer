/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ducktimer;

import Utils.ThreadTime;
import java.awt.GridBagConstraints;
import java.awt.*;
import java.time.*;
import javax.swing.*;

/**
 *
 * @author pato_coder 11:09 pm 01/10/2026 finalizado
 */
public class Reloj extends JInternalFrame{
    JLabel lbl_time,lbl_fecha;
    ThreadTime hilo;
    
    public Reloj(){
        setTitle(null);
        setFrameIcon(null);
        setBorder(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        addd();
        
        hilo=new ThreadTime(lbl_time, lbl_fecha);
        hilo.start();
        
        addInternalFrameListener(new javax.swing.event.InternalFrameAdapter(){
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent e){
                if(hilo != null){
                    hilo.interrupt();  
                    hilo=null;
                }
            }
        });
        
        setVisible(true);
    }
    
    void addd(){
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.gridx=0;
        
        LocalDateTime tim=LocalDateTime.now();
        LocalDate fecha=LocalDate.now();
        
        lbl_time=new JLabel(tim.getHour()+":"+tim.getMinute()+":"+tim.getSecond());
        lbl_time.setFont(new Font("Arial", Font.BOLD, 48));
        gbc.gridy=0;
        this.add(lbl_time, gbc);
        
        lbl_fecha=new JLabel(fecha.getDayOfMonth()+"/"+fecha.getMonthValue()+"/"+fecha.getYear());
        lbl_fecha.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridy=1;
        this.add(lbl_fecha, gbc);
        
        gbc.gridy=2;
        
    }
}
