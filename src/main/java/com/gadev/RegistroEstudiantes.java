package com.gadev;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RegistroEstudiantes {
    List<Estudiante> estudiantes;

    public void registrarEstudiante(Estudiante estudiante) {
        if (estudiante == null) {
            throw new IllegalArgumentException("Estudiante no puede ser null");
        }
        estudiantes.add(estudiante);
    }

    public void eliminarEstudiante(String dni) {
        // Validar el DNI
        validarString(dni, "Dni");

        estudiantes.removeIf(estudiante -> dni.equals(estudiante.getDni()));
    }

    public Optional<Estudiante> buscarPorDni(String dni) {
        // Validar el DNI
        validarString(dni, "Dni");

        // Buscar el estudiante por DNI
        for (Estudiante estudiante : estudiantes) {
            if (dni.equals(estudiante.getDni())) {
                return Optional.of(estudiante);
            }
        }

        return Optional.empty();
    }

    public List<Estudiante> buscarPorNombre(String nombre) {
        // Validar el nombre
        validarString(nombre, "Nombre");

        List<Estudiante> resultado = new ArrayList<>();

        // Buscar estudiantes por nombre (puede haber varios con el mismo nombre)
        for(Estudiante e : estudiantes){
            if (e.getNombre().equals(nombre.trim()))
                resultado.add(e);
        }

        return resultado;
    }

    public List<Estudiante> listarPorPromedio() {
        return null;
    }

    public void estadisticas() {

    }

    // Metodo auxiliar para validar String como DNI o Nombre
    private void validarString(String valor, String nombreCampo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(nombreCampo + " no puede ser null o vacío");
        }
    }
}
