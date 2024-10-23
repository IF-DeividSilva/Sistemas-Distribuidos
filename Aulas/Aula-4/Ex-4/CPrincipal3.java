/**
 * DONE1: CPrincipal3.java: instancie 2 (duas) threads.
 * 
 * DONE2: CPrincipal3.java: Adicione o seguinte trecho de codigo:
 *          't1.thrd.join();'
 * 
 * TODO3: CThread3.java: adicione a sincronizacao da regiao critica.
 *        'synchronized(sa) //Acesso ah regiao critica'
 * 
 */

//Soma de vetor sincronizado
class CPrincipal3 { 
	public static void main(String args[]) {
		
		int a[] = {1, 2, 3, 4, 5};
		int TAM = 50;
		
		CThread3 [] lista = new CThread3[TAM];
		
		for(int i=0; i < TAM ; i++)
		    lista[i] = new CThread3 ("Filho #"+i, a);
		    
		
		//CThread3 t1 = new CThread3("Filho #1", a); 
		//CThread3 t2 = new CThread3("Filho #2", a);
		
		try { 
			//TODO2
		//	t1.thrd.join();
		//	t2.thrd.join();
			for (CThread3 i : lista)
			    i.thrd.join();
			
		} catch(InterruptedException exc) { 
			System.out.println("Thread principal interrompida.");
		}
	}
}
