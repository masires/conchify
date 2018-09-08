package com.conchify.modelo;

import com.conchify.modelo.enums.Estado;
import com.conchify.modelo.enums.Tipo_Queja;

import java.sql.Date;
import java.sql.Timestamp;

public class Queja {

    private int numero_placa_id;
    private int numero_carnet_id;
    private int numero_franja_id;
    private Date fecha;
    private Timestamp hora;
    private String comentario;
    private Tipo_Queja tipo_queja;
    private Estado estado;
    private String comentario_cierre;


    public Queja(){

    }

    public Queja(int numero_placa_id, int numero_carnet_id, int numero_franja_id, String comentario, Tipo_Queja tipo_queja, Estado estado, String comentario_cierre) {

        this.numero_placa_id = numero_placa_id;
        this.numero_carnet_id = numero_carnet_id;
        this.numero_franja_id = numero_franja_id;
        this.comentario = comentario;
        this.tipo_queja = tipo_queja;
        this.estado = estado;
        this.comentario_cierre = comentario_cierre;
    }

    public int getNumero_placa_id() {
        return numero_placa_id;
    }

    public void setNumero_placa_id(int numero_placa_id) {
        this.numero_placa_id = numero_placa_id;
    }

    public int getNumero_carnet_id() {
        return numero_carnet_id;
    }

    public void setNumero_carnet_id(int numero_carnet_id) {
        this.numero_carnet_id = numero_carnet_id;
    }

    public int getNumero_franja_id() {
        return numero_franja_id;
    }

    public void setNumero_franja_id(int numero_franja_id) {
        this.numero_franja_id = numero_franja_id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Tipo_Queja getTipo_queja() {
        return tipo_queja;
    }

    public void setTipo_queja(Tipo_Queja tipo_queja) {
        this.tipo_queja = tipo_queja;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getComentario_cierre() {
        return comentario_cierre;
    }

    public void setComentario_cierre(String comentario_cierre) {
        this.comentario_cierre = comentario_cierre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Timestamp getHora() {
        return hora;
    }

    public void setHora(Timestamp hora) {
        this.hora = hora;
    }
}
