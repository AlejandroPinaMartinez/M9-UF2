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

    // métode para comprobar y establir la potencia als 4 motors
    public void passaAPotencia(int p) {
        System.out.println("Passant a potència " + p);

        // establir la potencia dels 4 motors
        for (int i = 0; i < 4; i++) {
            motors[i].setPotencia(p);
        }

        // Crear un fil per cada motor
        Thread[] threads = new Thread[4];
        for (int i = 0; i < 4; i++) {
            threads[i] = new Thread(motors[i]);
            threads[i].start(); // Iniciar el hilo
        }

        // Esperar a que acabin tots els fils
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // metode per arrencar els motors
    public void arranca() {
        for (int i = 0; i < 4; i++) {
            motors[i].setPotencia(motors[i].getPotenciaObjectiu());
        }
    }

    // métode per llegir la potencia desde la consola i establirla en tots els motors
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Coet coet = new Coet();

        // llegir i assignar les potencies continuament
        while (true) {
            System.out.print("Introdueix la potencia objectiu: ");
            int potencia = sc.nextInt();

            // establir la potencia de tots els motors
            coet.passaAPotencia(potencia);

            // si la potencia es 0, per a tots els cicles
            if (potencia == 0) {
                break;
            }
        }

        sc.close();
    }
}
