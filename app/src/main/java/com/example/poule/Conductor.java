package com.example.poule;

public class Conductor extends Usuario{
    String fechaVenciPase;
    Integer edad;

    public String getFechaVenciPase() {
        return fechaVenciPase;
    }

    public void setFechaVenciPase(String fechaVenciPase) {
        this.fechaVenciPase = fechaVenciPase;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }
}
