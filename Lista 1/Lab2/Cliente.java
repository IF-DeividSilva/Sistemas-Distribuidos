
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
             BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
             int opcao = 0;

             do{
                 // Menu
                 System.out.println("Menu:");
                 System.out.println("1 - Leitura");
                 System.out.println("2 - Escrita");
                 System.out.println("0 - Sair");
                 System.out.print("Digite sua escolha: ");
                 opcao = Integer.parseInt(br1.readLine());;
             switch (opcao) {
                 case 1:
                     leitura();
                     break;
                 case 2:
                     escrita(br);
                     break;
                 case 0:
                     System.out.println("Saindo...");
                     break;
                 default:
                     System.out.println("Opcao invalida");
                     String opi = "{\n \"method\": \" \",\n \"args\": [\"  \"]\n}";
                     saida.writeUTF(opi);
                     String resultado = entrada.readUTF();
                     System.out.println(resultado);
                     break;
             }
             }   while(opcao != 0);
                 socket.close(); 
             
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
             String resultado = entrada.readUTF();
             System.out.println(resultado);
             
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
 
     
     public void escrita(BufferedReader br){
         try {
            System.out.println("Digite a fortuna: ");
       
                 String newFort = br.readLine();
                 String write = "\n \"method\": \"write\",\n \"args\": [\""+newFort+"\"]\n}";
                 saida.writeUTF(write);
                 String resultado = entrada.readUTF();
                 System.out.println(resultado);
         } catch (Exception e) {
            System.out.println("SHOW: Excecao na escrita do arquivo.???");
             e.printStackTrace();
         }
     }
     
     public static void main(String[] args) {
         new Cliente().iniciar();
     }
     
 }
