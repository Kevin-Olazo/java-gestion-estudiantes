package com.gadev;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MenuRegistro {
    private final Scanner scanner = new Scanner(System.in);
    private RegistroEstudiantes registroEstudiantes = new RegistroEstudiantes();

    public void start() {

        Estudiante e1 = new Estudiante("Juan Perez", "12345678", "Ingeniería de Sistemas");
        Estudiante e2 = new Estudiante("Maria Gomez", "87654321", "Administración");
        Estudiante e3 = new Estudiante("Carlos Sanchez", "11223344", "Derecho");
        Estudiante e4 = new Estudiante("Ana Rodriguez", "44332211", "Medicina");
        Estudiante e5 = new Estudiante("Luis Fernandez", "55667788", "Arquitectura");
        Estudiante e6 = new Estudiante("Sofia Martinez", "99887766", "Psicología");
        Estudiante e7 = new Estudiante("Pedro Ramirez", "66778899", "Economía");
        // Genera estudiantes con nombres similares pero distintos dnis y carreras para probar el metodo buscarNombre
        Estudiante e8 = new Estudiante("Juan Gomez", "12349876", "Ingeniería de Sistemas");
        Estudiante e9 = new Estudiante("Maria Perez", "87651234", "Administración");
        Estudiante e10 = new Estudiante("Carlos Rodriguez", "11224433", "Derecho");


        e1.agregarNota(10.50);
        e1.agregarNota(15.00);
        e1.agregarNota(20.00);
        e2.agregarNota(12.00);
        e2.agregarNota(14.50);
        e2.agregarNota(19.00);
        e3.agregarNota(18.00);
        e3.agregarNota(17.00);
        e3.agregarNota(16.50);
        e4.agregarNota(9.00);
        e4.agregarNota(11.00);
        e4.agregarNota(13.50);
        e5.agregarNota(16.00);
        e5.agregarNota(17.50);
        e5.agregarNota(18.00);
        e6.agregarNota(13.00);
        e6.agregarNota(14.00);
        e6.agregarNota(15.50);

        registroEstudiantes.registrarEstudiante(e1);
        registroEstudiantes.registrarEstudiante(e2);
        registroEstudiantes.registrarEstudiante(e3);
        registroEstudiantes.registrarEstudiante(e4);
        registroEstudiantes.registrarEstudiante(e5);
        registroEstudiantes.registrarEstudiante(e6);
        registroEstudiantes.registrarEstudiante(e7);
        registroEstudiantes.registrarEstudiante(e8);
        registroEstudiantes.registrarEstudiante(e9);
        registroEstudiantes.registrarEstudiante(e10);


        int command;

        do {
            printMenu();

            command = leerOpcion();

            switch (command) {
                case 1 -> registrarNuevo();
                case 2 -> agregarNota();
                case 3 ->
                        buscarEstudiante().ifPresentOrElse(e -> System.out.println(e.toString()), () -> System.out.println("No se encontró  estudiante con ese dni"));
                case 4 -> buscarNombre();
                case 5 -> listarPorPromedio();
                case 6 -> registroEstudiantes.estadisticas();
                case 7 -> eliminarEstudiante();
                case 8 -> System.out.println("Adios!");
                default -> System.out.println("Ingrese un comando valido");
            }

        } while (command != 8);

    }

    private int leerOpcion() {
        while (true){
            System.out.print("Seleccione una opción: ");
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e){
                System.out.println("Error: Ingresa una opción valida");
            }
        }
    }

    public void registrarNuevo() {
        try {
            System.out.println("Ingresa nombre: ");
            String nombre = scanner.nextLine();
            System.out.println("Ingresa DNI: ");
            String dni = scanner.nextLine();
            System.out.println("Ingresa Carrera: ");
            String carrera = scanner.nextLine();
            registroEstudiantes.registrarEstudiante(new Estudiante(nombre, dni, carrera));
        } catch (IllegalArgumentException e) {
            System.out.println("Error al registrar");
        }
    }

    private void listarPorPromedio(){
        List<Estudiante> listaPromedio = registroEstudiantes.listarPorPromedio();

        if (listaPromedio.isEmpty()){
            System.out.println("No hay estudiantes registrados.");
        } else {
            for(Estudiante e : listaPromedio){
                System.out.println(e.toString());
            }
        }
    }

    private void buscarNombre(){
        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine();
        List<Estudiante> resultado = registroEstudiantes.buscarPorNombre(nombre);

        if (resultado.isEmpty()){
            System.out.println("No se encontraron resultados.");
        } else {
            for(Estudiante e : resultado){
                System.out.println(e.toString());
            }
            System.out.println("Total de resultados: " + resultado.size());
        }

    }

    private void agregarNota() {
        Optional<Estudiante> estudiante = buscarEstudiante();

        if (estudiante.isEmpty()) {
            System.out.println("No se encontró el estudiante con ese DNI");
            return;
        }


        try {
            System.out.print("Ingrese la nota: ");
            double nota = Double.parseDouble(scanner.nextLine());
            estudiante.get().agregarNota(nota);
            System.out.println("Nota agregada correctamente");
        } catch (NumberFormatException e) {
            System.out.println("Ingrese un valor valido.");
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }


    }

    private Optional<Estudiante> buscarEstudiante() {
        System.out.print("Ingrese dni del estudiante: ");
        String dni = scanner.nextLine();
        return registroEstudiantes.buscarPorDni(dni);
    }

    private void eliminarEstudiante(){
        Optional<Estudiante> estudiante = buscarEstudiante();

        if (estudiante.isEmpty()) {
            System.out.println("No se encontró el estudiante con ese DNI");
            return;
        }

        System.out.println("Estas seguro? S/N");

        String confirmation = scanner.nextLine();
        if (confirmation.trim().equalsIgnoreCase("s")){
            registroEstudiantes.eliminarEstudiante(estudiante.get().getDni());
            System.out.println("Estudiante eliminado exitosamente");
        } else if (confirmation.trim().equalsIgnoreCase("n")) {
            System.out.println(" - ");
        } else {
            System.out.println("Ingrese un valor correcto (S/N)");
        }


    }


    private void printMenu() {
        System.out.println("------- Menu de registro de estudiantes --------");
        System.out.println("1. Registrar nuevo estudiante");
        System.out.println("2. Agregar nota a estudiante");
        System.out.println("3. Buscar por DNI");
        System.out.println("4. Buscar por nombre");
        System.out.println("5. Listar todos por promedio");
        System.out.println("6. Ver estadísticas");
        System.out.println("7. Eliminar estudiante");
        System.out.println("8. Salir");
    }


}
