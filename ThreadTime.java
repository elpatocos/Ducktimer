package Utils;


import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.swing.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author terem
 */
public class ThreadTime extends Thread{
    
    JLabel lbl_time,lbl_fecha;
    
    public ThreadTime(JLabel lbl_time, JLabel lbl_fecha){
        this.lbl_time=lbl_time;
        this.lbl_fecha=lbl_fecha;
    }
    
    @Override
    public void run(){
        
         while(true){
        
        LocalDateTime tim=LocalDateTime.now();
            LocalDate fecha=LocalDate.now();
            
            String hora=String.format("%02d:%02d:%02d", 
                tim.getHour(), tim.getMinute(), tim.getSecond());
            String fechaStr=fecha.getDayOfMonth()+"/"+fecha.getMonthValue()+"/"+fecha.getYear();
            
            SwingUtilities.invokeLater(() -> {
                if(lbl_time != null) lbl_time.setText(hora);
                if(lbl_fecha != null) lbl_fecha.setText(fechaStr);
            });
            
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
