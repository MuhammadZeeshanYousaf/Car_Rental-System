/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop.classes;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Zeeshan
 */
public class BookingManagement extends Booking{
    
    //Paths to store data files
    private final String bookingsFilePath = ".\\data_files\\bookings.txt";
    private final String unbookedFilePath = ".\\data_files\\unbooked.txt";
    private final String directoryPath = ".\\data_files";
    //File declarations
    private final File theDirectory;  //to make directory ""data_files" if not exists
    private final File bookingsFile;
    private File unbookedFile;
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
    
    public boolean UnBook(String carName) throws IOException
    {
        //this method removes the data from bookings.txt and save data in unbooked.txt file
        
        //make file unbooked.txt
        unbookedFile = new File(unbookedFilePath);
        unbookedFile.createNewFile();
        
        //Array which will have all the file data 
        ArrayList<String> bookings_dataArr = new ArrayList<>();
        
        //read all the data from file
        while(reader.hasNext()){
            bookings_dataArr.add(reader.nextLine());
        }
        reader.close();
        
        //now find the data of the booking which have car registeration number "regNo"
        int index = 0;
        String carFullName = carName;
        for(String line : bookings_dataArr)
        {
            if(line.split(";")[2].equalsIgnoreCase(carFullName))
            {
                //here we found the cnic
                   //first remove the existing data from file
                    try (FileWriter makeFileEmpty = new FileWriter(bookingsFile, false)) {
                        makeFileEmpty.flush();
                        makeFileEmpty.close();
                    }
                    //Now write all the data of cars except this car
                    for(int i = 0; i < bookings_dataArr.size(); i++)
                        if(i != index)  //this will skip the data of the customer to remove
                            pen.write(bookings_dataArr.get(i)+'\n');
                    pen.close();
                    
                    //Now write this data in unbooked.txt
                    try(FileWriter writeUnbookedData = new FileWriter(unbookedFile, true)){
                        writeUnbookedData.write(line + '\n');   //write the data of the car found, in unbooked.txt file
                        writeUnbookedData.close();
                    }
               return true;
            }
            index++;
        }
        JOptionPane.showMessageDialog(null, "Booking not found!");
        return false;
    }
    
    //to get all current bookings
    public String[] getAllBookedCarNames()
    {
        //customerID+';'+customerName+';'+carName+';'+rentTime;
        String[] bookingsArray = new String[0x64];
        int index = 0;
        
        while(reader.hasNext()){
            bookingsArray[index] = reader.nextLine().split(";")[2];
            index++;
        }
        reader.close();
        return bookingsArray;
    }
    
    //to get all current bookings
    public String[] getAllUnbookedCarNames()
    {
        //customerID+';'+customerName+';'+carName+';'+rentTime;
        //get unbooked file
        String[] unbookingsArray = new String[0x64];
        int index = 0;
        //try to get access to unbooked file
        unbookedFile = new File(unbookedFilePath);
        try(Scanner unbookedReader = new Scanner(unbookedFile)){
            while (unbookedReader.hasNext()) {
                unbookingsArray[index] = reader.nextLine().split(";")[2];
                index++;
            }
            unbookedReader.close();
        }
        catch(FileNotFoundException ex)
        {
            JOptionPane.showMessageDialog(null, "unbooked.txt File/Data does not Exist!", "FileNotFound", 1);
        }
        return unbookingsArray;
    }
}
