package com.example.model;

public class Tarifa {

    private int tarifaHoraMoto;
    private int tarifaHoraAuto;
    private int tarifaHoraCamion;

    private int tarifaDiaMoto;
    private int tarifaDiaAuto;
    private int tarifaDiaCamion;

    private int horasParaDia;

    public Tarifa(int tarifaHoraMoto, int tarifaHoraAuto, int tarifaHoraCamion, int tarifaDiaMoto, int tarifaDiaAuto, int tarifaDiaCamion, int horasParaDia) {
        this.tarifaHoraMoto = tarifaHoraMoto;
        this.tarifaHoraAuto = tarifaHoraAuto;
        this.tarifaHoraCamion = tarifaHoraCamion;
        this.tarifaDiaMoto = tarifaDiaMoto;
        this.tarifaDiaAuto = tarifaDiaAuto;
        this.tarifaDiaCamion = tarifaDiaCamion;
        this.horasParaDia = horasParaDia;
    }

    public int getTarifaHoraMoto() {
        return tarifaHoraMoto;
    }

    public void setTarifaHoraMoto(int tarifaHoraMoto) {
        this.tarifaHoraMoto = tarifaHoraMoto;
    }

    public int getTarifaHoraAuto() {
        return tarifaHoraAuto;
    }

    public void setTarifaHoraAuto(int tarifaHoraAuto) {
        this.tarifaHoraAuto = tarifaHoraAuto;
    }

    public int getTarifaHoraCamion() {
        return tarifaHoraCamion;
    }

    public void setTarifaHoraCamion(int tarifaHoraCamion) {
        this.tarifaHoraCamion = tarifaHoraCamion;
    }

    public int getTarifaDiaMoto() {
        return tarifaDiaMoto;
    }

    public void setTarifaDiaMoto(int tarifaDiaMoto) {
        this.tarifaDiaMoto = tarifaDiaMoto;
    }

    public int getTarifaDiaAuto() {
        return tarifaDiaAuto;
    }

    public void setTarifaDiaAuto(int tarifaDiaAuto) {
        this.tarifaDiaAuto = tarifaDiaAuto;
    }

    public int getTarifaDiaCamion() {
        return tarifaDiaCamion;
    }

    public void setTarifaDiaCamion(int tarifaDiaCamion) {
        this.tarifaDiaCamion = tarifaDiaCamion;
    }

    public int getHorasParaDia() {
        return horasParaDia;
    }

    public void setHorasParaDia(int horasParaDia) {
        this.horasParaDia = horasParaDia;
    }
}
