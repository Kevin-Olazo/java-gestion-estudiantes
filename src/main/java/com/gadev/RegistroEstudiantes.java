package com.gadev;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RegistroEstudiantes {
    List<Estudiante> lista;
    Map<String, Estudiante> porDni;

    public void registrarEstudiante(Estudiante estudiante) {
        if (estudiante == null) {
            throw new IllegalArgumentException("Estudiante no puede ser null");
        }

        if (porDni.containsKey(estudiante.getDni())){
            throw new IllegalArgumentException("Ya existe un estudiante con DNI: " + estudiante.getDni());
        }

        lista.add(estudiante);
        porDni.put(estudiante.getDni(), estudiante);
    }

    public void eliminarEstudiante(String dni) {
        // Validar el DNI
        validarString(dni, "Dni");
        Estudiante e = porDni.remove(dni);
        if (e != null){
            lista.remove(e);
        }
    }

    public Optional<Estudiante> buscarPorDni(String dni) {
        // Validar el DNI
        validarString(dni, "Dni");

        // Buscar el estudiante por DNI
        for (Estudiante estudiante : lista) {
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
        for(Estudiante e : lista){
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
