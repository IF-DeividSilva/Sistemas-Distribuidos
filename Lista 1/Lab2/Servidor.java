import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	private static Socket socket;
	private static ServerSocket server;

	private static DataInputStream entrada;
	private static DataOutputStream saida;

	private int porta = 1025;

	public void iniciar() {
		System.out.println("Servidor iniciado na porta: " + porta);
		try {

			// Criar porta de recepcao
			server = new ServerSocket(porta);
			socket = server.accept();  //Processo fica bloqueado, ah espera de conexoes

			// Criar os fluxos de entrada e saida
			entrada = new DataInputStream(socket.getInputStream());
			saida = new DataOutputStream(socket.getOutputStream());

			// Recebimento do valor inteiro
			String valor = entrada.readUTF();
			System.out.println(valor);

			String method = valor.split("\"method\": \"")[1].split("\"")[0];
			String args = valor.split("\"args\": \"")[1].split("\"")[0];


			// Processamento do valor	
			if ("read".equals(method)){

			System.out.println("Leitura");
			System.out.println("Valor: " + args);

			} else if ("write".equals(method)){

			System.out.println("Escrita");
			System.out.println("Valor: " + args);

			} else {
			String resultado = "Opcao invalida";
			// Envio dos dados (resultado)
			saida.writeUTF(resultado);
			socket.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		new Servidor().iniciar();

	}

}
