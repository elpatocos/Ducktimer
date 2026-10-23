/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ducktimer;

import Utils.ThreadTime;
import Utils.TimeUtils.*;
import Utils.Time;
import javax.swing.*;
import Utils.*;
import java.awt.*;
import java.util.*;
//import org.jvnet.substance.skin.SubstanceFindingNemoLookAndFeel;
import java.time.*;
/**
 *
 * @author pato_coder 19:26
 */


public class Alarms extends JInternalFrame{
    JLabel lbl_time,lbl_date;
    JButton btn_edit,btn_delete;
    ThreadTime hilo;
    ArrayList<Time>alarms=new ArrayList();
    TimeUtils utils=new TimeUtils();
    public Alarms(){
        setTitle(null);
        setFrameIcon(null);
        setBorder(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        addd();
        fun();
        
        alarms = utils.getAlarms();
        
        addInternalFrameListener(new javax.swing.event.InternalFrameAdapter(){
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent e){
                utils.SaveAlarms(alarms);
                setVisible(false);
            }
        });
        
        setVisible(true);
        
        hilo=new ThreadTime(lbl_time, lbl_date);
        hilo.start();
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
        
        lbl_date=new JLabel(fecha.getDayOfMonth()+"/"+fecha.getMonthValue()+"/"+fecha.getYear());
        lbl_date.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridy=1;
        this.add(lbl_date, gbc);
        
        JPanel panelBotones=new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
        panelBotones.setOpaque(false);
        panelBotones.setBorder(null);

        btn_edit=new JButton("Edit");
        btn_edit.setBorderPainted(false);

        btn_delete=new JButton("Delete");
        btn_delete.setBorderPainted(false);

        panelBotones.add(btn_edit);
        panelBotones.add(Box.createHorizontalStrut(20));
        panelBotones.add(btn_delete);
        
        gbc.gridy=2;
        this.add(panelBotones, gbc);
    }
    
    void fun(){
        btn_edit.addActionListener(e ->{
            JDialog select=new JDialog();
            select.setTitle("MAKE ALARM");
            select.setSize(300, 250);
            select.setLocationRelativeTo(this);
            select.setLayout(new GridBagLayout());
            //select.setModal(true);
            
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets=new Insets(5, 5, 5, 5);
            gbc.gridx=0;
            
            // Hour
            JLabel lbl_hour=new JLabel("Hour:");
            JSpinner sp_hour=new JSpinner(new SpinnerNumberModel(0,0,23,1));
            gbc.gridy=0;
            select.add(lbl_hour,gbc);
            gbc.gridy=1;
            select.add(sp_hour,gbc);

            // Minute
            JLabel lbl_minute=new JLabel("Minute:");
            JSpinner sp_minute=new JSpinner(new SpinnerNumberModel(0,0,59,1));
            gbc.gridy=2;
            select.add(lbl_minute,gbc);
            gbc.gridy=3;
            select.add(sp_minute,gbc);

            // Second
            JLabel lbl_second=new JLabel("Second:");
            JSpinner sp_second=new JSpinner(new SpinnerNumberModel(0,0,59,1));
            gbc.gridy=4;
            select.add(lbl_second,gbc);
            gbc.gridy=5;
            select.add(sp_second,gbc);

            JButton btn_save=new JButton("Save Alarm");
            gbc.gridy=6;
            select.add(btn_save,gbc);
            
            btn_save.addActionListener(ev->{
            
                int h=(int)sp_hour.getValue();
                int m=(int)sp_minute.getValue();
                int s=(int)sp_second.getValue();
                
                if (h < 0 || h > 23 || m < 0 || m > 59 || s < 0 || s > 59) {
                    JOptionPane.showMessageDialog(select, 
                        "La hora o los minutos ingresados no son válidos.", 
                        "Error de Validación", 
                        JOptionPane.ERROR_MESSAGE);
                    return; 
                }
                
                Time neww=new Time(h,m,s);
                alarms.add(neww);
                utils.SaveAlarms(alarms);

                
                JOptionPane.showMessageDialog(null, "ALARM SAVED");
                select.dispose();
            });

            select.setVisible(true);
        });
        
        btn_delete.addActionListener(ev->{
            JDialog select=new JDialog();
            select.setTitle("SELECT ALARMS TO DELETE");
            select.setSize(300, 400);
            select.setLocationRelativeTo(this);
            select.setLayout(new BorderLayout());
            select.setModal(true);
            
            JPanel pnlList=new JPanel();
            pnlList.setLayout(new BoxLayout(pnlList, BoxLayout.Y_AXIS));
            pnlList.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            
            for(Time t : alarms){
                JCheckBox cb=new JCheckBox(t.getHours()+":"+t.getMinuts()+":"+t.getSeconds());
                pnlList.add(cb);
            }
            
            JScrollPane scroll=new JScrollPane(pnlList);
            scroll.setPreferredSize(new Dimension(280, 300));
            
            JPanel panelButtons=new JPanel(new FlowLayout());
            JButton btn_accept=new JButton("Accept");
            JButton btn_cancel=new JButton("Cancel");
            panelButtons.add(btn_accept);
            panelButtons.add(btn_cancel);
            
            btn_cancel.addActionListener(ce->select.dispose());
            
            btn_accept.addActionListener(ce->{
                for(Component c : pnlList.getComponents()){
                    if(c instanceof JCheckBox){
                        JCheckBox cb=(JCheckBox)c;
                        if(cb.isSelected()){
                            String text=cb.getText();
                            String[] parts=text.split(":");
                            int h=Integer.parseInt(parts[0]);
                            int m=Integer.parseInt(parts[1]);
                            int s=Integer.parseInt(parts[2]);
                            alarms.removeIf(t -> t.getHours()==h && t.getMinuts()==m && t.getSeconds()==s);
                        }
                    }
                }
                utils.SaveAlarms(alarms);
                select.dispose();
                JOptionPane.showMessageDialog(null, "ALARMS DELETED");
            });
            
            select.add(scroll, BorderLayout.CENTER);
            select.add(panelButtons, BorderLayout.SOUTH);
            select.setVisible(true);
        });
    }
}