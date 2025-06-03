/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Factory;
import Domain.Vehicle ;
/**
 *
 * @author hp
 */
public class PlaneFactory extends VehicleFactory{
    public Vehicle createVehicle(){
        return new Plane();
    }
}
