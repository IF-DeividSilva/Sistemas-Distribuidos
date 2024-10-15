/**
 * DONE: Principal: Crie 2 (duas) novas threads 
 * 
 * DONE: AThread2: Modifique a classe para que ela extenda da classe Thread.
 * 
 * TODO3: AThread2: Crie no construtor da classe um identificador unico para cada thread.
 *                  O construtor devera atribuir a variavel de instancia ID.
 * 
 * TODO4: Principal: Crie 20 threads que entram em estado de sleep por ate 1 segundo, mas apenas entrarao em
 *        sleep as que tiverem ID impar.
 */

//Threads extendem a classe Thread
public class APrincipal2 {

    public static void main(String[] args) {
        
        Thread t1 = new Thread(new AThread2("t1")); //.start sai do estado 'Novo' para o estado 'Executar'
        
        //TODO1
        Thread t2 = new Thread(new AThread2("t2"));
        Thread t3 = new Thread(new AThread2("t3"));
        
        t1.start();
        t2.start();
        t3.start();
        
        System.out.println(t1.getId());
        System.out.println(t2.getId());
        System.out.println(t3.getId());
        
        //TODO4
 
    }
}
