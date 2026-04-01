package com.gadev;

public abstract class Persona {
    protected String nombre;
    protected String dni;

    public Persona(String nombre, String dni) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre no puede ser nulo o vacío");
        }
        if (dni == null || dni.isBlank()) {
            throw new IllegalArgumentException("DNI no puede ser nulo o vacío");
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
