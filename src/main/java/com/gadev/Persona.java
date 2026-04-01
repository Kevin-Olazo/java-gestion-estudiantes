package com.gadev;

public abstract class Persona {
    protected String nombre;
    protected String dni;

    public Persona(String nombre, String dni) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El campo nombre no puede estar vacío");
        }
        if (dni == null || dni.isBlank()) {
            throw new IllegalArgumentException("El campo DNI no puede estar vacío");
        }
        if (!dni.matches("\\d{8}")) {
            throw new IllegalArgumentException("DNI debe tener exactamente 8 dígitos numéricos");
        }
        this.nombre = nombre;
        this.dni = dni;
    }

    public abstract String getTipoPersona();

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    @Override
    public String toString() {
        return "[" + getTipoPersona() + "] " + nombre + " (DNI: " + dni + ")";
    }
}
