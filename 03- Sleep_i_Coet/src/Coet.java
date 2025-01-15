package src;

import java.util.Scanner;

public class Coet {

    // variable
    private Motor[] motors;

    // constructor
    public Coet() {
        motors = new Motor[4];
        for (int i = 0; i < 4; i++) {
            motors[i] = new Motor(i);
        }
    }

    // mètode per comprovar i establir la potència als 4 motors
    public void passaAPotencia(int p) {
        System.out.println("Passant a potència " + p);

        // establir la potència dels 4 motors
        for (int i = 0; i < 4; i++) {
            motors[i].setPotencia(p);
        }

        // Crear un fil per a cada motor
        Thread[] threads = new Thread[4];
        for (int i = 0; i < 4; i++) {
            threads[i] = new Thread(motors[i]);
            threads[i].start(); // Iniciar el fil
        }

        // Espera activa fins que tots els fils acabin
        boolean totsElsFilsAcabats;
        do {
            totsElsFilsAcabats = true;
            for (Thread thread : threads) {
                if (thread.isAlive()) {
                    totsElsFilsAcabats = false;
                    break;
                }
            }
        } while (!totsElsFilsAcabats);
    }

    // mètode per arrencar els motors
    public void arranca() {
        for (int i = 0; i < 4; i++) {
            motors[i].setPotencia(motors[i].getPotenciaObjectiu());
        }
    }

    // mètode per llegir la potència des de la consola i establir-la en tots els motors
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Coet coet = new Coet();

        // llegir i assignar les potències contínuament
        while (true) {
            System.out.print("Introdueix la potencia objectiu: ");
            int potencia = sc.nextInt();

            // establir la potència de tots els motors
            coet.passaAPotencia(potencia);

            // si la potència és 0, sortir del bucle
            if (potencia == 0) {
                break;
            }
        }

        sc.close();
    }
}
