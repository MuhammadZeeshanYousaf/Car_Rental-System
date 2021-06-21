/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop.finalproject;

/**
 *
 * @author Zeeshan
 */
public class CarManagment extends Car{
    
    //Constructor
    public CarManagment(String maker, String name, String regNo, String ownerId, long rentPerHour, int model, CarType carType, int seatingCapacity, CarColor carColor, boolean condition) {
        super(maker, name, regNo, ownerId, rentPerHour, model, carType, seatingCapacity, carColor, condition);
        
    }
    
    //Here are the methods which will perform CRUD operations on car using File handling
    
    public bool AddCar(Car car)
    {
        
    }
}
