package com.gadev;

import java.util.List;
import java.util.Optional;

public class RegistroEstudiantes {
    List<Estudiante> estudiantes;

    public void registrarEstudiante(Estudiante estudiante){
        if (estudiante == null){
            throw new IllegalArgumentException("Estudiante no puede ser nulo");
        }
        estudiantes.add(estudiante);
    }

    public void eliminarEstudiante(String dni){

    }

    public Optional<Estudiante> buscarPorDni(String dni){
        return Optional.empty();
    }

    public List<Estudiante> buscarPorNombre(String nombre){
        return null;
    }

     public List<Estudiante> listarPorPromedio(){
        return null;
     }

     public void estadisticas(){

     }
}
