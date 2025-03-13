class Client {
    private final String nom;
    
    public Client(int id) {
        this.nom = "Client-" + id;
    }
    
    public void tallarseElCabell() {
        try {
            System.out.println("Tallant cabell a " + nom);
            Thread.sleep(900 + (int) (Math.random() * 100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public String getNom() {
        return nom;
    }

}