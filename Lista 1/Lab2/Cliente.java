
/**
 * Laboratorio 1 de Sistemas Distribuidos
 */

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    
    private static Socket socket;
    private static DataInputStream entrada;
    private static DataOutputStream saida;
    
    private int porta=1025;
    
    public void iniciar(){
    	System.out.println("Cliente iniciado na porta: "+porta);
    	
    	try {
            
            socket = new Socket("127.0.0.1", porta);
            
            entrada = new DataInputStream(socket.getInputStream());
            saida = new DataOutputStream(socket.getOutputStream());
            
            // Menu
            System.out.println("Menu:");
            System.out.println("1 - Leitura");
            System.out.println("2 - Escrita");
            System.out.println("0 - Sair");

            Scanner scanner = new Scanner(System.in);
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    //leitura();
                    break;
                case 2:
                    //escrita();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opcao invalida");
                    break;
            }
            


            //Recebe do usuario algum valor
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Digite um numero: ");
            int valor = Integer.parseInt(br.readLine());
            
            //O valor eh enviado ao servidor
            saida.writeInt(valor);
            
            //Recebe-se o resultado do servidor
            String resultado = entrada.readUTF();
            
            //Mostra o resultado na tela
            System.out.println(resultado);
            
            socket.close();
            
        } catch(Exception e) {
        	e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        new Cliente().iniciar();
    }
    
}
