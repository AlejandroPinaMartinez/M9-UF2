package src;

import java.util.Random;

public class Motor implements Runnable {

    // variables
    private int id;
    private int potenciaActual;
    private int potenciaObjectiu;

    // constructor
    public Motor(int id) {
        this.id = id;
        this.potenciaActual = 0;
        this.potenciaObjectiu = 0;
    }

    // getter de potenciaObjectiu
    public int getPotenciaObjectiu() {
        return this.potenciaObjectiu;
    }

    // setter de potenciaObjectiu
    public void setPotencia(int p) {
        this.potenciaObjectiu = p;
    }

    // Implementar el método run para el hilo
    @Override
    public void run() {
        // si la potència objectiu és diferent de l'actual, la canvio
        if (this.potenciaActual != this.potenciaObjectiu) {
            // determinar si incrementem o decrementem
            String operacio = (this.potenciaObjectiu > this.potenciaActual) ? "Incre." : "Decre.";

            // mentre no arribem a la potència objectiu
            while (this.potenciaActual != this.potenciaObjectiu) {
                // pausar un temps aleatori entre 0 i 2 segons
                try {
                    Thread.sleep(new Random().nextInt(2001)); // Aleatorio entre 0 y 2000 milisegundos
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // incrementar o decrementar la potència
                if (this.potenciaObjectiu > this.potenciaActual) {
                    this.potenciaActual++;
                } else {
                    this.potenciaActual--;
                }

                // Imprimir l'estat del motor
                System.out.printf("Motor %d: %s Objectiu: %d Actual: %d%n", this.id, operacio, this.potenciaObjectiu, this.potenciaActual);
            }

            // imprimir quan el motor arriba a la potència objectiu
            System.out.printf("Motor %d: FerRes Objectiu: %d Actual: %d%n", this.id, this.potenciaObjectiu, this.potenciaActual);
        }
    }
}
