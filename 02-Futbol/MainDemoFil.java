public class MainDemoFil {
    public static void main(String[] args) {
        Thread actual = Thread.currentThread();
        System.out.println("MainDemoFil.main:");
        System.out.println("Prioritat -> " + actual.getPriority() + ", Nom -> " + actual.getName());
        System.out.println("toString() -> " + actual.toString());
    }
}