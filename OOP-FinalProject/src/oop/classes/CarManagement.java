
package oop.classes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;


public class CarManagement extends Car{
    
    private final String carsFilePath;
    private final File carsFile;
    private final FileWriter pen;
    private final Scanner reader;
    
    //parameterized Constructor    
    public CarManagement(String maker, String name, String regNo, String ownerId, Integer rentPerHour, Integer model, String carType, Integer seatingCapacity, String carColor, String condition) throws IOException {
        super(maker, name, regNo, ownerId, rentPerHour, model, carType, seatingCapacity, carColor, condition);
        carsFilePath = ".\\files\\cars.txt";
        carsFile = new File(carsFilePath);
        carsFile.createNewFile();
        pen = new FileWriter(carsFile, true);
        reader = new Scanner(carsFile);
    }
    //Non parameterized constructor
    public CarManagement() throws IOException{
        super();
        carsFilePath = ".\\files\\cars.txt";
        carsFile = new File(carsFilePath);
        carsFile.createNewFile();
        pen = new FileWriter(carsFile, true);
        reader = new Scanner(carsFile);
    }
    
    //Here are the methods which will perform CRUD operations on car using File handling
    
    public boolean AddCar(Car car)
    {
        //This method write the car attributes into the file named "cars.txt"
        try {
            pen.write(car.toString()+'\n');
            pen.close();
            return true;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Car cannot be added", 1);
        }
        return false;
    }
    
    public boolean RemoveCar(String regNo){
        //this method will remove the data of that car which have registeration no "regNo"
        
        //Array which will have all the file data 
        ArrayList<String> dataArr = new ArrayList<>();
        //read all the data from file
        while(reader.hasNext()){
            dataArr.add(reader.nextLine());
        }
        reader.close();
        
        //now find the data of the car which have registeration no "regNo"
        int index = 0;
        for(String line : dataArr)
        {
            if(line.split(";")[2].equalsIgnoreCase(regNo))
            {
                //here we found the regNo
                try {
                    pen.flush();    //first remove the existing data
                    
                    //Now write all the data of cars except this car
                    for(int i = 0; i < dataArr.size(); i++)
                        if(i != index)  //this will skip the data of the car to remove
                            pen.write(dataArr.get(i)+'\n');
                    
                    pen.close();
                }
                catch(IOException ex)
                {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Car cannot be removed", 1);
                    return false;
                }
               return true;
            }
            index++;
        }
        return false;
    }
    
}
