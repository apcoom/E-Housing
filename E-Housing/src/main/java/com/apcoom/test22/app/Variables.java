package com.apcoom.test22.app;

public class Variables {
    private static String horaPrendido="";
    private static String horaApagado="";
    private static String fechaPrendido="";
    private static String fechaApagado="";
    private static String nombreDispositivo="";

    public Variables() {
    }

    public void setHoraPrendido(String nave){
        horaPrendido=nave;
    }

    public void setHoraApagado(String nave){
        horaApagado=nave;
    }

    public void setFechaPrendido(String nave){
        fechaPrendido=nave;
    }

    public void setFechaApagado(String nave){
        fechaApagado=nave;
    }

    public void setNombreDispositivo(String nave){
        nombreDispositivo=nave;
    }

    public String getHoraPrendido(){
        return horaPrendido;
    }

    public String getHoraApagado(){
        return horaApagado;
    }

    public String getFechaPrendido(){
        return fechaPrendido;
    }

    public String getFechaApagado(){
        return fechaApagado;
    }

    public String getNombreDispositivo(){
        return nombreDispositivo;
    }
}
