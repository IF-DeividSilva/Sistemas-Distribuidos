/**
 * DONE 1: Compartilhado.java: Crie um atributo 'valor' 
 * DONE 2: Consumidor.java: Leia o atributo 'valor' e mostre na tela
 * DONE 3: Produtor.java: Leia o atributo 'valor' e mostre na tela
 * DONE 4: Crie 10 (dez) threads 'Produtor' e 10 (dez) threads 'Consumidor'
 */
public class Principal {
		
	public static void main(String args[]) {

		compartilhado recurso = new compartilhado();
		
		int Tam = 10;
		
		Produtor [] listaP = new Produtor [Tam];
		for(int i=0; i<Tam; i++){
		    listaP[i] = new Produtor ("P"+i, recurso);
		}
		
		Consumidor [] listaC = new Consumidor [Tam];
		for(int i=0; i<Tam; i++){
		    listaC[i] = new Consumidor ("C"+i, recurso);
		}
		
		
		//Produtor t1 = new Produtor("Thread1",recurso); 
		//Consumidor t2 = new Consumidor("Thread2",recurso);
		
		try { 
		    
		    for(int i = 0; i<Tam; i++){
		        listaP[i].thrd.join();
		        listaC[i].thrd.join();
		    }
			//t1.thrd.join(); 
			//t2.thrd.join();
		} catch(InterruptedException exc) { 
			System.out.println("Thread principal interrompida.");
		}
		System.out.println("Done.");
	}

    
}//fim classe

