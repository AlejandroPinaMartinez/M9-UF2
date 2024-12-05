# M9-UF2

## Comportament 1

### Codi

```java
    public class Principal {
        public static void main(String[] args) {
            System.out.println("Termina thread main");

            Thread filJuan = new Thread(new Fil("Juan"));
            Thread filPepe = new Thread(new Fil("Pepe"));

            filJuan.start();
            filPepe.start();
        }
    }

    public class Fil extends Thread {
        private String nom;

        public Fil(String nom) {
            this.nom = nom;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 9; i++) {
                System.out.println(nom + " " + i);
                try {
                    Thread.sleep((int) Math.random()); 
                } catch (InterruptedException e) {
                    System.out.println("Error en el fil " + nom);
                }
            }
            System.out.println("Termina el fil " + nom);
        }
    }
```

### Juan i Pepe es van intercalant més o menys de forma equitativa.

Termina thread main
Pepe 1
Juan 1
Pepe 2
Juan 2
Pepe 3
Pepe 4
Juan 3
Pepe 5
Juan 4
Pepe 6
Juan 5
Pepe 7
Pepe 8
Juan 6
Pepe 9
Juan 7
Juan 8
Juan 9
Termina el fil Juan
Termina el fil Pepe

## Comportament 2

### Codi

```java
    public class Principal {
        public static void main(String[] args) {
            System.out.println("Termina thread main");

            Thread filJuan = new Thread(new Fil("Juan"));
            Thread filPepe = new Thread(new Fil("Pepe"));

            try {
                // Inicia i espera que acabi Pepe
                filPepe.start();
                filPepe.join();

                // Inicia i espera que acabi Juan
                filJuan.start();
                filJuan.join();
            } catch (InterruptedException e) {
                System.out.println("Error esperant els fils");
            }

            // Imprimir missatges finals
            System.out.println("Termina el fil Pepe");
            System.out.println("Termina el fil Juan");
        }
    }

    public class Fil extends Thread {
        private String nom;

        public Fil(String nom) {
            this.nom = nom;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 9; i++) {
                System.out.println(nom + " " + i);
                try {
                    Thread.sleep((int) Math.random()); // Controla la velocitat del fil
                } catch (InterruptedException e) {
                    System.out.println("Error en el fil " + nom);
                }
            }
        }
    }
```

### Primer majorment Pepe i després Juan en totes les execucions que faig.

Termina thread main
Pepe 1
Pepe 2
Pepe 3
Pepe 4
Pepe 5
Pepe 6
Pepe 7
Pepe 8
Pepe 9
Juan 1
Juan 2
Juan 3
Juan 4
Juan 5
Juan 6
Juan 7
Juan 8
Juan 9
Termina el fil Pepe
Termina el fil Juan

## Comportament 3

### Codi
```java
    public class Principal {
        public static void main(String[] args) {
            System.out.println("Termina thread main");

            Thread filJuan = new Thread(new Fil("Juan"));
            Thread filPepe = new Thread(new Fil("Pepe"));

                filPepe.start();
                filJuan.start();
        }
    }

    public class Fil extends Thread {
        private String nom;

        public Fil(String nom) {
            this.nom = nom;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 9; i++) {
                System.out.println(nom + " " + i);
                try {
                    Thread.sleep(51); // Controla la velocitat del fil
                } catch (InterruptedException e) {
                    System.out.println("Error en el fil " + nom);
                }
            }
            System.out.println("Termina el fil " + nom);
        }
    }
```

### Execució en ordre estricte altern entre cada fil

Termina thread main
Pepe 1
Juan 1
Pepe 2
Juan 2
Pepe 3
Juan 3
Pepe 4
Juan 4
Pepe 5
Juan 5
Pepe 6
Juan 6
Pepe 7
Juan 7
Juan 8
Pepe 8
Juan 9
Pepe 9
Termina el fil Pepe
Termina el fil Juan

### Conclusions
En Comportament 1, l'ús d'sleep() aleatori, sense cap control explícit, fa que els fils no tinguin un ordre estrictament determinat.
En Comportament 2, el control explícit amb join() obliga a Pepe a completar tot el seu codi abans que Juan comenci, ordre sequencial.
En Comportament 3, l'ús d'un temps de sleep() fixat de 51ms permet una alternança estricta entre els fils.