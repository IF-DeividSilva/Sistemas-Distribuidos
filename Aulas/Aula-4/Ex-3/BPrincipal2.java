//Soma de vetor nao sincronizado

/**
 * DONE1: BPrincipal2: instancie 2 (duas) threads.
 * 
 * DONE2: BPrincipal2: insira o trecho de codigo:
 *           't1.thrd.join();' 
 *        no bloco try-catch 
 * 
 * DONE3: BThread2: define que a classe implemente a interface Runnable.
 * 
 * TODO4: BThread2: insira o trecho de codigo no construtor:
 *          'thrd.start();'
 * 
 */

 class BPrincipal2 { 
	public static void main(String args[]) {
		
		int a[] = {1, 2, 3, 4, 5};
		
		BThread2 [] lista = new BThread2[200];
		for(int i=0; i<200; i++)
		    lista[i] = new BThread2("Filho #" + i, a);
		
		BThread2 t1 = new BThread2("Filho #1", a); 
		
		//TODO1
		BThread2 t2 = new BThread2("Filho #2", a);
		
		try { 
		    for(BThread2 i: lista){
		        i.thrd.join();
		    }
			//TODO2
			//t1.thrd.join();
			//t2.thrd.join();
			
			
		} catch(InterruptedException exc) { 
			System.out.println("Thread principal interrompida.");
		}
	}
}
