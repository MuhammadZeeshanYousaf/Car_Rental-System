/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop.classes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Zeeshan
 */
public class BookingManagement extends Booking{
    
    //Paths to store data files
    private final String bookingsFilePath = ".\\data_files\\bookings.txt";
    private final String directoryPath = ".\\data_files";
    //File declarations
    private final File theDirectory;  //to make directory ""data_files" if not exists
    private final File bookingsFile;
    private final FileWriter pen;
    private final Scanner reader;   //file reader 
    
    //parameterized Constructor    
    public BookingManagement(String customerID, String customerName, String carName) throws IOException{
        super(customerID, customerName, carName);
        theDirectory = new File(directoryPath);
        theDirectory.mkdir();       //if files directory not exists then create one
        bookingsFile = new File(bookingsFilePath);
        bookingsFile.createNewFile();
        pen = new FileWriter(bookingsFile, true);
        reader = new Scanner(bookingsFile);
    }
    
    //Non parameterized constructor
    public BookingManagement() throws IOException {
        theDirectory = new File(directoryPath);
        theDirectory.mkdir();       //if files directory not exists then create one
        bookingsFile = new File(bookingsFilePath);
        bookingsFile.createNewFile();
        pen = new FileWriter(bookingsFile, true);
        reader = new Scanner(bookingsFile);
    }
    
    //Functions
    public boolean MakeBooking(Booking book)
    {
        //This method write the booking attributes into the file named "bookings.txt"
        try {
            pen.write(book.toString()+'\n');
            pen.close();
            return true;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Booking cannot be made!", 1);
        }
        return false;
    }
    
    //overloaded function
    //if booking parameters passed through constructor
    public boolean MakeBooking()
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
