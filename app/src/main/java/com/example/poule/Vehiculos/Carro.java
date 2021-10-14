package com.example.poule.Vehiculos;

import com.example.poule.Interfaces.Vehiculo;

public class Carro implements Vehiculo {
    private String codigo, placa, cedulaConductor, SoatVigente, marca, modelo;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCedulaConductor() {
        return cedulaConductor;
    }

    public void setCedulaConductor(String cedulaConductor) {
        this.cedulaConductor = cedulaConductor;
    }

    public String getSoatVigente() {
        return SoatVigente;
    }

    public void setSoatVigente(String soatVigente) {
        SoatVigente = soatVigente;
    }

    @Override
    public void codigoVehiculo() {

    }
}
