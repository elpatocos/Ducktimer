/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.io.*;
import java.nio.file.*;

/**
 *
 * @author pato_coder 15:46
 */



public class TimeUtils {
    
    
    Time hora_actual(){
        
        LocalDateTime ahora = LocalDateTime.now();
    int hours = ahora.getHour();
    int minuts = ahora.getMinute();
    int seconds = ahora.getSecond();
    
    
    return new Time(hours, minuts, seconds);
    }
    
    public Time makeTime(int h,int m,int s){
        return new Time(h,m,s);
    }
    
    public void SaveAlarms(ArrayList<Time> save_times){
        
        try(PrintWriter writer = new PrintWriter(new FileWriter("alarmas.txt"))){
            System.out.println("Archivo creado");
            
            for(Time t : save_times){
                writer.println(t.getHours() + "," + t.getMinuts() + "," + t.getSeconds());
                System.out.println("Guardado: "+t.getHours()+","+t.getMinuts()+","+t.getSeconds());
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public ArrayList<Time> getAlarms() {
    ArrayList<Time> alarms = new ArrayList<>();
    try(BufferedReader reader = new BufferedReader(new FileReader("alarms.txt"))) {
        String line;
        while((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if(parts.length == 3) {
                try {
                    int h = Integer.parseInt(parts[0].trim());
                    int m = Integer.parseInt(parts[1].trim());
                    int s = Integer.parseInt(parts[2].trim());
                    alarms.add(new Time(h, m, s));
                } catch(NumberFormatException ex) {
                    
                }
            }
        }
    } catch(IOException e) {
        
    }
    return alarms;
}
    
    public void deleteFile(){
        File archive=new File("alarms.txt");
        if(archive.exists()){
            archive.delete();
            System.out.println("deleted ");
        }
    }
    
}
