/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop.finalproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hp
 */
public class main{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        LogginForm form = new LogginForm();
//        form.setVisible(true);


Car c = new Car("Suzuki", "honda", "2344-sf", "23433299", 0, 0, CarType.SPORTS, 0, CarColor.BLACK, true);
        System.out.println("caR STRING:"+ c.toString());
        
        
        File f = new File(".\\files\\cars.txt");
        
        
        try {
            f.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
}
