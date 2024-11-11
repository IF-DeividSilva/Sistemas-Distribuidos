/**
 * Programa produtor/consumidor
 * 
 * @author Lucio A. Rocha
 * @since 2023-04-09
 *
 */
public class compartilhado {
	
	private int valor;
	
	private boolean leitura=true; 
	private boolean escrita=true;

	public synchronized void ler(String nome){
		
		while(!leitura){

		    try{
		    	System.out.println(nome + ": WAIT" );
		    	wait();
		    } catch (InterruptedException e ){
		    	System.err.println(e.toString());
		    }
		}//fim while
		System.out.println(nome + " leu valor:" + valor);
		leitura=false;
		escrita=true;
		
		notifyAll();  //'notify()' method wakes only one thread that is waiting for a notify
	}
	public synchronized void escrever(String nome, int valor){
	//public synchronized void escrever(String nome){		
	    //setValor(valor);
		
		while (!escrita){
			try{
		    	System.out.println(nome + ": WAIT" );
		    	wait();
		    } catch (InterruptedException e ){
		    	System.err.println(e.toString());
		    }
		}//fim while
		System.out.println(nome + " escreveu valor:"+valor);
		
		escrita=false;	
		leitura=true;
		notifyAll(); //'notify()' method wakes only one thread that is waiting for a notify
    //}
      //  public void setValor(int valor){

        //}
	}	
	
}//fim classe

