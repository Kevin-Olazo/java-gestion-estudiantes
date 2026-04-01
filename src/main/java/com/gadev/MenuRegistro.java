package com.gadev;

import java.util.Optional;
import java.util.Scanner;

public class MenuRegistro {
    private final Scanner scanner = new Scanner(System.in);
    private RegistroEstudiantes registroEstudiantes = new RegistroEstudiantes();

    public void start() {

        Estudiante e1 = new Estudiante("Juan Perez", "12345678", "Ingenieria de Sistemas");
        Estudiante e2 = new Estudiante("Maria Gomez", "87654321", "Administracion");
        Estudiante e3 = new Estudiante("Carlos Sanchez", "11223344", "Derecho");
        Estudiante e4 = new Estudiante("Ana Rodriguez", "44332211", "Medicina");
        Estudiante e5 = new Estudiante("Luis Fernandez", "55667788", "Arquitectura");
        Estudiante e6 = new Estudiante("Sofia Martinez", "99887766", "Psicologia");

        registroEstudiantes.registrarEstudiante(e1);
        registroEstudiantes.registrarEstudiante(e2);
        registroEstudiantes.registrarEstudiante(e3);
        registroEstudiantes.registrarEstudiante(e4);
        registroEstudiantes.registrarEstudiante(e5);
        registroEstudiantes.registrarEstudiante(e6);


        int command;

        do {
            printMenu();
            System.out.print("Seleccione una opción: ");
            command = Integer.parseInt(scanner.nextLine());
            switch (command) {
                case 1 -> registrarNuevo();
                case 2 -> agregarNota();
                case 3 ->
                        buscarEstudiante().ifPresentOrElse(e -> System.out.println(e.toString()), () -> System.out.println("No se encontró  estudiante con ese dni"));
                case 6 -> verEstadisticas();
                case 8 -> System.out.println("Adios!");
                default -> System.out.println("Ingrese un comando valido");
            }

        } while (command != 8);

    }

    public void registrarNuevo() {

        System.out.println("Ingresa nombre: ");
        String nombre = scanner.nextLine();
        System.out.println("Ingresa DNI: ");
        String dni = scanner.nextLine();
        System.out.println("Ingresa Carrera: ");
        String carrera = scanner.nextLine();

        registroEstudiantes.registrarEstudiante(new Estudiante(nombre, dni, carrera));
    }

    public void agregarNota() {
        Optional<Estudiante> estudiante = buscarEstudiante();

        if (estudiante.isEmpty()) {
            System.out.println("No se encontro el estudiante con ese DNI");
            return;
        }


        try {
            System.out.print("Ingrese la nota: ");
            double nota = Integer.parseInt(scanner.nextLine());
            estudiante.get().agregarNota(nota);
            System.out.println("Nota agregada correctamente");
        } catch (NumberFormatException e) {
            System.out.println("Ingrese un valor valido.");
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }


    }

    public Optional<Estudiante> buscarEstudiante() {
        System.out.print("Ingrese dni del estudiante: ");
        String dni = scanner.nextLine();
        return registroEstudiantes.buscarPorDni(dni);
    }

    public void verEstadisticas() {
        for (Estudiante e : registroEstudiantes.getLista()) {
            System.out.println(e.toString());
        }
    }

    public void printMenu() {
        System.out.println("------- Menu de registro de estudiantes --------");
        System.out.println("1. Registrar nuevo estudiante");
        System.out.println("2. Agregar nota a estudiante");
        System.out.println("3. Buscar por DNI");
        System.out.println("4. Buscar por nombre");
        System.out.println("5. Listar todos los promedios");
        System.out.println("6. Ver estadísticas");
        System.out.println("7. Eliminar estudiante");
        System.out.println("8. Salir");
    }


}
