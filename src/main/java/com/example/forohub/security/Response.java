package com.example.forohub.security;

public class Response <T>{
    private String mensaje;
    private T data;

    public Response(String mensaje) {
        this.mensaje = mensaje;
    }

    public Response(String mensaje, T data) {
        this.mensaje = mensaje;
        this.data = data;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}