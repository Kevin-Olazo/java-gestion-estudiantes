package com.gadev;

import java.util.Scanner;

public class MenuRegistro {
    Scanner scanner = new Scanner(System.in);

    public void start(){

        int command = 0;

        do {
            printMenu();
            command = Integer.parseInt(scanner.nextLine());

        } while (command != 8);

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
