/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop.classes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Zeeshan
 */
public class CustomerManagement extends Person{
    ////Paths to store data files
    private final String customersFilePath = ".\\data_files\\customers.txt";
    private final String directoryPath = ".\\data_files";
    //File declarations
    private final File theDirectory;  //to make directory ""data_files" if not exists
    private final File customersFile;
    private final FileWriter pen;
    private final Scanner reader;
    
    //Parameterized constructor

    public CustomerManagement(String cnic, String Name, String phone) throws IOException {
        super(cnic, Name, phone);
        theDirectory = new File(directoryPath);
        theDirectory.mkdir();   //if files directory not exists then create one
        customersFile = new File(customersFilePath);
        customersFile.createNewFile();
        pen = new FileWriter(customersFile, true);
        reader = new Scanner(customersFile);
    }
    
    //non-parameterized constructor
    public CustomerManagement() throws IOException {
        theDirectory = new File(directoryPath);
        theDirectory.mkdir();   //if files directory not exists then create one
        customersFile = new File(customersFilePath);
        customersFile.createNewFile();
        pen = new FileWriter(customersFile, true);
        reader = new Scanner(customersFile);
    }
    
    //Methods to perform CRUD Operations

    public boolean AddCustomer(Person cust)
    {
        //This method write the customers attributes into the file named "customers.txt"
        try {
            pen.write(cust.toString()+'\n');
            pen.close();
            return true;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Customer cannot be added", 1);
        }
        return false;
    }
    
    public boolean RemoveCustomer(String cnic){
        //this method will remove the data of that customer which have cnic "cnic"
        
        //Array which will have all the file data 
        ArrayList<String> customer_dataArr = new ArrayList<>();
        //read all the data from file
        while(reader.hasNext()){
            customer_dataArr.add(reader.nextLine());
        }
        reader.close();
        
        //now find the data of the car which have registeration no "regNo"
        int index = 0;
        for(String line : customer_dataArr)
        {
            if(line.split(";")[0].equals(cnic))
            {
                //here we found the cnic
                try {
                    pen.flush();    //first remove the existing data from file
                    
                    //Now write all the data of cars except this car
                    for(int i = 0; i < customer_dataArr.size(); i++)
                        if(i != index)  //this will skip the data of the customer to remove
                            pen.write(customer_dataArr.get(i)+'\n');
                    
                    pen.close();
                }
                catch(IOException ex)
                {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Customer cannot be removed", 1);
                    return false;
                }
               return true;
            }
            index++;
        }
        return false;
    }
}
