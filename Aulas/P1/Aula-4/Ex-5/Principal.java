/**
 * TODO 1: Crie 20 novas instancias de threads.
 * TODO 2: Execute as instancias no pool de threads.
 * 
 */

 import java.util.concurrent.ExecutorService;
 import java.util.concurrent.Executors;
 
 public class Principal {
 
     public class AThread implements Runnable {
         
         public void run(){
             System.out.println("Ola Mundo");
         }
     }
 
     public Principal(){
         ExecutorService pool = Executors.newCachedThreadPool();
 
        // AThread t1 = new AThread();
        // Thread t = new Thread(t1);
        // TAM = 50
         Thread [] lista = new Thread[50];
         for(int i = 0; i < 50; i++)
             lista[i] = new Thread(new AThread());
             
         for (Thread t : lista)
             pool.execute(t);
             
         pool.shutdown();
     }
 
     public static void main(String[] args) {
         new Principal();
     }
 }
 
 