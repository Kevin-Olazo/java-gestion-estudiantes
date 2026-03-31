package com.gadev;

import java.util.Arrays;
import java.util.List;

public class Estudiante extends Persona implements Comparable<Estudiante> {
    private String carrera;
    private List<Double> notas;

    public Estudiante(String nombre, String dni, String carrera) {
        super(nombre, dni);
        this.carrera = carrera;
    }

    private void agregarNota(double nota){
        if (nota < 0 || nota > 20){
            throw new IllegalArgumentException("Nota invalida (0-20)");
        }
        notas.add(nota);
    }

    private double getPromedio(){
        // Calcular el promedio de las notas
        return notas.stream()// Convertir la lista de notas a un stream
                .mapToDouble(Double::doubleValue)// Convertir cada nota a un valor double
                .average() // Calcular el promedio de las notas
                .orElse(0.0); // Si no hay notas, retornar 0.0
    }

    public List<Double> getNotas() {
        return List.copyOf(notas);
    }

    public String getCarrera() {
        return carrera;
    }

    @Override
    String getTipoPersona() {
        return "";
    }

    @Override
    public int compareTo(Estudiante o) {
        return Double.compare(this.getPromedio(), o.getPromedio());
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", carrera='" + carrera + '\'' +
                ", notas=" + notas +
                '}';
    }
}
