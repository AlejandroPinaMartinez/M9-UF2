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

    // método para comprobar y establecer la potencia en los 4 motores
    public void passaAPotencia(int p) {
        System.out.println("Passant a potència " + p);

        // establecer la potencia de los 4 motores
        for (int i = 0; i < 4; i++) {
            motors[i].setPotencia(p);
        }

        // Crear un hilo para cada motor
        Thread[] threads = new Thread[4];
        for (int i = 0; i < 4; i++) {
            threads[i] = new Thread(motors[i]);
            threads[i].start(); // Iniciar el hilo
        }

        // Esperar que todos los hilos terminen
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // método para arrancar los motores
    public void arranca() {
        for (int i = 0; i < 4; i++) {
            motors[i].setPotencia(motors[i].getPotenciaObjectiu());
        }
    }

    // método principal para leer la potencia desde la consola y establecerla en todos los motores
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Coet coet = new Coet();

        // leer y asignar potencias continuamente
        while (true) {
            System.out.print("Introduce la potencia objectiu: ");
            int potencia = sc.nextInt();

            // establecer la potencia de todos los motores
            coet.passaAPotencia(potencia);

            // si la potencia es 0, para todos los ciclos
            if (potencia == 0) {
                break;
            }
        }

        sc.close();
    }
}
