package hilos;

public class Hilo implements Runnable {
    // Método para mostrar los datos del hilo en ejecución
    public void run() {
        System.out.println(Thread.currentThread().getPriority());
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getState());
        System.out.println(Thread.currentThread().getStackTrace());
            
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            System.out.println(e);
        }

        // Operaciones de prueba
        int a = 2;
        int b = 2;
        int suma = a + b;
        System.out.println("El resultado es: " + suma);
    }
}
