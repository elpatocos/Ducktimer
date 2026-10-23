/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ducktimer;

import javax.swing.*;
import java.awt.*;
//import org.jvnet.substance.skin.SubstanceFindingNemoLookAndFeel;
/**
 *
 * @author terem
 */


public class Ducktimer extends JFrame{
    
    JLabel lbl1,imgIc;
    JPanel north,south,oeste,este,center;
    
    private void addd(){
        north=new JPanel();
        south=new JPanel();
        oeste=new JPanel();
        este=new JPanel();
        center=new JPanel();
        
//        north.setBackground(Color.CYAN);
//        south.setBackground(Color.CYAN);
//        oeste.setBackground(Color.CYAN);
//        este.setBackground(Color.CYAN);
//        center.setBackground(Color.CYAN);
        
        lbl1=new JLabel("bienvenido");
        lbl1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 52));
        lbl1.setForeground(new Color(0, 51, 153));
        lbl1.setOpaque(true);
        
        ImageIcon icon=new ImageIcon(getClass().getResource("azul.png"));
        
        Image imgEscalada=icon.getImage().getScaledInstance(
            560,  
            400,
            Image.SCALE_SMOOTH
        );
        imgIc=new JLabel(new ImageIcon(imgEscalada));
        
        north.add(lbl1);
        center.add(imgIc);
        
        setLayout(new BorderLayout());
        add(north,BorderLayout.NORTH);
        add(south,BorderLayout.SOUTH);
        add(oeste,BorderLayout.WEST);
        add(este,BorderLayout.EAST);
        add(center,BorderLayout.CENTER);
    }
    
    public Ducktimer() throws UnsupportedLookAndFeelException{
        setTitle("duckTimes");
        setSize(900,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //UIManager.setLookAndFeel(new SubstanceFindingNemoLookAndFeel());
        
        addd();
    }
    
    
    
    public static void main(String[] args) throws UnsupportedLookAndFeelException{
        Ducktimer ventana=new Ducktimer();
        ventana.setVisible(true);
        
        try{
            Thread.sleep(2000); 
            ventana.dispose();
            new principal();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
