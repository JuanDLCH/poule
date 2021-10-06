package com.example.poule.Fabricas;

import com.example.poule.Interfaces.Vehiculo;
import com.example.poule.Interfaces.VehiculoTransporte;

public class FabricaDeVehiculos {
    public static void crearFabricaDeVehiculo(VehiculoTransporte factory){

        // Polimorfismo

        Vehiculo objetoVehiculo = factory.crearVehiculo();
        objetoVehiculo.codigoVehiculo();

    }


}
