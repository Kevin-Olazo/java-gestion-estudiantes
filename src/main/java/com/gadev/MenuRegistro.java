package com.gadev;

import java.util.Scanner;

public class MenuRegistro {
    private final Scanner scanner = new Scanner(System.in);
    private RegistroEstudiantes registroEstudiantes = new RegistroEstudiantes();

    public void start(){

        int command;

        do {
            printMenu();
            System.out.print("Seleccione una opción: ");
            command = Integer.parseInt(scanner.nextLine());
            switch (command){
                case 1 -> registrarNuevo();
                case 6 -> verEstadisticas();
                case 8 -> System.out.println("Adios!");
                default -> System.out.println("Ingrese un comando valido");
            }

        } while (command != 8);

    }

    public void registrarNuevo(){

        System.out.println("Ingresa nombre: ");
        String nombre = scanner.nextLine();
        System.out.println("Ingresa DNI: ");
        String dni = scanner.nextLine();
        System.out.println("Ingresa Carrera: ");
        String carrera = scanner.nextLine();

        registroEstudiantes.registrarEstudiante(new Estudiante(nombre,dni, carrera));
    }

    public void verEstadisticas(){
        for(Estudiante e : registroEstudiantes.getLista()){
            System.out.println(e.toString());
        }
    }

    public void printMenu(){
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
