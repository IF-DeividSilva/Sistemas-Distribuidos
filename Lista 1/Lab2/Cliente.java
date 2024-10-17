
/**
 * Laboratorio 1 de Sistemas Distribuidos
 */

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
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
            
            
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String opcao = new String();
            do{
                // Menu
                System.out.println("Menu:");
                System.out.println("1 - Leitura");
                System.out.println("2 - Escrita");
                System.out.println("0 - Sair");
                System.out.print("Digite sua escolha: ");
                opcao = br.readLine();

            switch (opcao) {
                case "1":
                    leitura();
                    break;
                case "2":
                    escrita();
                    break;
                case "0":
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
            }   while(!opcao.equals("0"));
                socket.close(); 
            
            //Recebe-se o resultado do servidor
            //String resultado = entrada.readUTF();
            
            //Mostra o resultado na tela
            //System.out.println(resultado);
            //socket.close();
            
        } catch (EOFException eof) {
            System.out.println("Fim do arquivo alcançado.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        
    }
    public void leitura(){
        try {
            String read = "{\n \"method\": \"read\",\n \"args\": [\"  \"]\n}";
            saida.writeUTF(read);
            BufferedReader reader = new BufferedReader(new InputStreamReader(entrada));
            String resultado = reader.readLine();
            System.out.println(resultado);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public void escrita(){
        try {
            System.out.println("Digite a fortuna: ");
            try (Scanner sc = new Scanner(System.in)) {
                String newFort = sc.nextLine();
                
                String write = "\n \"method\": \"write\",\n \"args\": [\""+newFort+"\"]\n}";
                saida.writeUTF(write);
                String resultado = entrada.readUTF();
                System.out.println(resultado);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        new Cliente().iniciar();
    }
    
}
