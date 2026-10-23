/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

/**
 *
 * @author terem
 */
public class Time {
    private int hours, minuts, seconds;
    
    public Time(int h, int m, int s){
        hours=h;
        minuts=m;
        seconds=s;
    }
    
    public int getHours(){ return hours; }
    public int getMinuts(){ return minuts; }
    public int getSeconds(){ return seconds; }
}
