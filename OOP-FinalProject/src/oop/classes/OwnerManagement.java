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
public class OwnerManagement extends Person{
    private final String ownersFilePath;
    private final File ownersFile;
    private final FileWriter pen;
    private final Scanner reader;
    
    //Parameterized constructor

    public OwnerManagement(String cnic, String Name, String phone) throws IOException {
        super(cnic, Name, phone);
        ownersFilePath = ".\\files\\owners.txt";
        ownersFile = new File(ownersFilePath);
        ownersFile.createNewFile();
        pen = new FileWriter(ownersFile, true);
        reader = new Scanner(ownersFile);
    }
    
    //non-parameterized constructor
    public OwnerManagement() throws IOException {
        super();
        ownersFilePath = ".\\files\\owners.txt";
        ownersFile = new File(ownersFilePath);
        ownersFile.createNewFile();
        pen = new FileWriter(ownersFile, true);
        reader = new Scanner(ownersFile);
    }
    
    //Methods to perform CRUD Operations

    public boolean AddCustomer(Person owner)
    {
        //This method write the customers attributes into the file named "customers.txt"
        try {
            pen.write(owner.toString()+'\n');
            pen.close();
            return true;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Owner cannot be added", 1);
        }
        return false;
    }
    
    public boolean RemoveCustomer(String cnic){
        //this method will remove the data of that customer which have cnic "cnic"
        
        //Array which will have all the file data 
        ArrayList<String> owner_dataArr = new ArrayList<>();
        //read all the data from file
        while(reader.hasNext()){
            owner_dataArr.add(reader.nextLine());
        }
        reader.close();
        
        //now find the data of the car which have registeration no "regNo"
        int index = 0;
        for(String line : owner_dataArr)
        {
            if(line.split(";")[0].equals(cnic))
            {
                //here we found the cnic
                try {
                    pen.flush();    //first remove the existing data from file
                    
                    //Now write all the data of cars except this car
                    for(int i = 0; i < owner_dataArr.size(); i++)
                        if(i != index)  //this will skip the data of the customer to remove
                            pen.write(owner_dataArr.get(i)+'\n');
                    
                    pen.close();
                }
                catch(IOException ex)
                {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Owner cannot be removed", 1);
                    return false;
                }
               return true;
            }
            index++;
        }
        return false;
    }
}
