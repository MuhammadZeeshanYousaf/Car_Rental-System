/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop.finalproject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Zeeshan
 */
public class CarManagment extends Car{
    
    private final String carsFilePath;
    private final File carsFile;
    private final FileWriter pen;
    private final Scanner reader;
    
    //Constructor
    public CarManagment(String maker, String name, String regNo, String ownerId, int rentPerHour, int model, String carType, int seatingCapacity, String carColor, String condition) throws IOException {
        super(maker, name, regNo, ownerId, rentPerHour, model, carType, seatingCapacity, carColor, condition);
        carsFilePath = ".\\files\\cars.txt";
        carsFile = new File(carsFilePath);
        carsFile.createNewFile();
        pen = new FileWriter(carsFile);
        reader = new Scanner(carsFile);
    }
    
    //Here are the methods which will perform CRUD operations on car using File handling
    
    public boolean AddCar()
    {
        //This method write the car attributes into the file named "cars.txt"
        try {
            pen.write(super.toString()+'\n');
            pen.close();
            return true;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Car cannot be added", 1);
        }
        return false;
    }
}
