/**
 * TODO1: Crie 2 (duas) novas threads 
 * 
 * TODO2: Modifique o tempo de sleep das threads para ate 1 segundo.
 * 
 * TODO3: AThread1: Crie no construtor da classe um identificador unico para cada thread.
 *                  O construtor devera atribuir a variavel de instancia ID.
 * 
 * TODO4: Crie 20 threads que entram em estado de sleep por ate 1 segundo, mas apenas entrarao em
 *        sleep as que tiverem ID impar.
 */

//Threads implementam interface Runnable
public class APrincipal1 {

    public static void main(String[] args) {
        //estado novo
        Thread obj;
        
        //obj = new Thread(new AThread1("t1"));
        
        //Thread obj2 = new Thread(new AThread1("t2"));
        //Thread obj3 = new Thread(new AThread1("t3"));
        
       // obj.start();
        //obj2.start();
        
        
        int TAM = 20;
        Thread [] lista = new Thread[TAM];
        
        for(int i=0; i< TAM;i++){
            lista[i] = new Thread(new AThread1("t"+i, i));
        }
        
        for(Thread t : lista)
            t.start();
        //.start sai do estado 'Novo' para o estado 'Executar'
        
    }
}
