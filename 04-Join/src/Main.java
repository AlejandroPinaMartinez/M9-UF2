package src;


class Administracio {
    private int num_poblacio_activa;
    private Treballador[] poblacio_activa;

    public Administracio() {
        this.num_poblacio_activa = 50;
        this.poblacio_activa = new Treballador[num_poblacio_activa];
        for (int i = 0; i < num_poblacio_activa; i++) {
            poblacio_activa[i] = new Treballador("Ciutadà-"+i,25000, 20, 65);
        }
    }

    public void executarSimulacio() {

        for (Treballador treballador : poblacio_activa) {
            treballador.start();
        }


        for (Treballador treballador : poblacio_activa) {
            try {
                treballador.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (Treballador treballador : poblacio_activa) {
            System.out.printf("%s -> edat: %d / total: %.2f\n", treballador.getEdat(), treballador.getCobrat());
        }
    }

    public static void main(String[] args) {
        Administracio administracio = new Administracio();
        administracio.executarSimulacio();
    }

}
