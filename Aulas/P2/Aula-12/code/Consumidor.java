/**
* Programa produtor/consumidor
* 
* @author Lucio A. Rocha
* @since 2023-04-09
*
*/
public class Consumidor implements Runnable { 
   public Thread thrd;
   private compartilhado compartilhado;

   Consumidor(String name, compartilhado compartilhado) {
       thrd = new Thread(this, name); 		
       thrd.start(); 
       
       this.compartilhado = compartilhado;
   }

   public void run() {
       System.out.println(thrd.getName() + ": READY");
       
       //Tenta consumir 10 vezes
       for(int i=0;i<10;i++){			
           //dorme por um intervalo aleatorio
           dorme();
           
           System.out.println(thrd.getName() + ": Read["+i+"]");
           compartilhado.ler(thrd.getName());
       }
       
   }
   public void dorme(){
       try{
           Thread.sleep( (int) (Math.random() * 1000));
       } catch (InterruptedException e){
           System.err.println(e.toString());
       }
   }
}
