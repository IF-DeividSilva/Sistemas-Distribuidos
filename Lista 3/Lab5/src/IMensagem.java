/**
 * Lab05: Sistema P2P
 * 
 * Autor: Deivid da Silva e João Vitor Yoshida
 * Adaptado de Lucio A. Rocha
 * 
 * Referencias: 
 * https://docs.oracle.com/javase/tutorial/essential/io
 * http://fortunes.cat-v.org/
 */

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface IMensagem extends Remote {
    
    public Mensagem enviar(Mensagem mensagem) throws RemoteException;
    
}
