package com.example.model;

public class Cupo {

    private int moto;
    private int ocupadoMoto;
    private int camion;
    private int ocupadoCamion;
    private int automovil;
    private int ocupadoAutomovil;

    public Cupo(int moto, int ocupadoMoto, int camion, int ocupadoCamion, int automovil, int ocupadoAutomovil) {
        this.moto = moto;
        this.ocupadoMoto = ocupadoMoto;
        this.camion = camion;
        this.ocupadoCamion = ocupadoCamion;
        this.automovil = automovil;
        this.ocupadoAutomovil = ocupadoAutomovil;
    }

    public int getMoto() {
        return moto;
    }

    public void setMoto(int moto) {
        this.moto = moto;
    }

    public int getOcupadoMoto() {
        return ocupadoMoto;
    }

    public void setOcupadoMoto(int ocupadoMoto) {
        this.ocupadoMoto = ocupadoMoto;
    }

    public int getCamion() {
        return camion;
    }

    public void setCamion(int camion) {
        this.camion = camion;
    }

    public int getOcupadoCamion() {
        return ocupadoCamion;
    }

    public void setOcupadoCamion(int ocupadoCamion) {
        this.ocupadoCamion = ocupadoCamion;
    }

    public int getAutomovil() {
        return automovil;
    }

    public void setAutomovil(int automovil) {
        this.automovil = automovil;
    }

    public int getOcupadoAutomovil() {
        return ocupadoAutomovil;
    }

    public void setOcupadoAutomovil(int ocupadoAutomovil) {
        this.ocupadoAutomovil = ocupadoAutomovil;
    }

    //------------------------------------//


}
