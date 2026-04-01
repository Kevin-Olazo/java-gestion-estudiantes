package com.gadev;

import java.util.*;
import java.util.stream.Collectors;

public class RegistroEstudiantes {
    private List<Estudiante> lista;
    private Map<String, Estudiante> porDni;

    public RegistroEstudiantes(){
        this.lista = new ArrayList<>();
        this.porDni = new HashMap<>();
    }

    public void registrarEstudiante(Estudiante estudiante) {
        if (estudiante == null) {
            throw new IllegalArgumentException("Estudiante no puede ser null");
        }

        if (porDni.containsKey(estudiante.getDni())) {
            throw new IllegalArgumentException("Ya existe un estudiante con DNI: " + estudiante.getDni());
        }

        lista.add(estudiante);
        porDni.put(estudiante.getDni(), estudiante);
    }

    public void eliminarEstudiante(String dni) {
        // Validar el DNI
        validarString(dni, "Dni");

        Estudiante e = porDni.remove(dni);
        if (e != null) {
            lista.remove(e);
        }
    }

    public Optional<Estudiante> buscarPorDni(String dni) {
        // Validar el DNI
        validarString(dni, "Dni");

        return Optional.ofNullable(porDni.get(dni.trim()));
    }

    public List<Estudiante> buscarPorNombre(String nombre) {
        // Validar el nombre
        validarString(nombre, "Nombre");

        String busqueda = nombre.trim().toLowerCase();

        return lista.stream()
                .filter(e -> e.getNombre() != null &&
                        e.getNombre().toLowerCase().contains(busqueda))
                .collect(Collectors.toList());
    }

    public List<Estudiante> listarPorPromedio() {
        List<Estudiante> listaPromedios = List.copyOf(lista);

        return listaPromedios.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public void estadisticas() {
        if (lista.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }

        // 1. Promedio general
        double promedioGeneral = lista.stream()
                .mapToDouble(Estudiante::getPromedio)
                .average()
                .orElse(0.0);

        // 2. Estudiante con el mejor promedio
        Estudiante mejorPromedio = lista.stream()
                .max(Comparator.comparingDouble(Estudiante::getPromedio))
                .orElse(null);

        // 3. Cantidad de aprobados (usando filter y count)
        long aprobados = lista.stream()
                .filter(e -> e.getPromedio() >= 13)
                .count();

        System.out.println("Promedio general: " + promedioGeneral);
        System.out.println("Estudiante con mejor promedio: " + (mejorPromedio != null ? mejorPromedio.getNombre() : "N/A"));
        System.out.println("Cantidad de aprobados: " + aprobados);
        System.out.println("Total de estudiantes registrados: " + lista.size());
    }

    public List<Estudiante> getLista() {
        return List.copyOf(lista);
    }

    // Metodo auxiliar para validar String como DNI o Nombre
    private void validarString(String valor, String nombreCampo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(nombreCampo + " no puede ser null o vacío");
        }
    }
}
