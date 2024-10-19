import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Random;

public class Servidor {

	public final static Path path = Paths.get("fortune-br.txt");
	private int NUM_FORTUNES = 0;

	

	private static Socket socket;
	private static ServerSocket server;

	private static DataInputStream entrada;
	private static DataOutputStream saida;

	private int porta = 1025;

	public class FileReader {

		public int countFortunes() throws FileNotFoundException {

			int lineCount = 0;

			InputStream is = new BufferedInputStream(new FileInputStream(
					path.toString()));
			try (BufferedReader br = new BufferedReader(new InputStreamReader(
					is))) {

				String line = "";
				while (!(line == null)) {

					if (line.equals("%"))
						lineCount++;

					line = br.readLine();
					//System.out.println("SHOW: Leitura Ok.");


				}// fim while

				System.out.println(lineCount);
			} catch (IOException e) {
				System.out.println("SHOW: Excecao na leitura do arquivo.");
			}
			return lineCount;
		}

		public void parser(HashMap<Integer, String> hm)
				throws FileNotFoundException {

			InputStream is = new BufferedInputStream(new FileInputStream(
					path.toString()));
			try (BufferedReader br = new BufferedReader(new InputStreamReader(
					is))) {

				int lineCount = 0;

				String line = "";
				while (!(line == null)) {

					if (line.equals("%"))
						lineCount++;

					line = br.readLine();
					StringBuffer fortune = new StringBuffer();
					while (!(line == null) && !line.equals("%")) {
						fortune.append(line + "\n");
						line = br.readLine();
						// System.out.print(lineCount + ".");
					}

					hm.put(lineCount, fortune.toString());
					System.out.println(fortune.toString());

					System.out.println(lineCount);
				}// fim while

			} catch (IOException e) {
				System.out.println("SHOW: Excecao na leitura do arquivo.");
			}
		}	

		
		public String read(HashMap<Integer, String> hm)
			throws FileNotFoundException {
					String resultado = "";

			 		if (hm.isEmpty()) {
                		System.out.println("Erro: Nao existem fortunas.");
                		return "";
            		}
            		Random random = new Random();
            		int index = random.nextInt(hm.size()) + 1; 
            		String randomFort = hm.get(index);
					resultado = randomFort;	

					// envio de dados
					return (resultado);
    	}
		
		public String write(HashMap<Integer, String> hm, String newFort)
			throws FileNotFoundException{
					String resultado = "";

					try (FileWriter fw = new FileWriter("fortune-br.txt", true);
					BufferedWriter bw = new BufferedWriter(fw);
					PrintWriter pw = new PrintWriter(bw)){
						pw.println(newFort + "\n%");
						resultado = newFort;
					//	System.out.println( "AQQUUUIII");

						return (resultado);

					}catch (IOException e) {
						System.out.println("SHOW: Excecao na escrita do arquivo.");	
					}
					return "Erro";
		}
	}
		
	 public void iniciar() {
        System.out.println("Servidor iniciado na porta: " + porta);
        try {
            // Criar porta de recepção
            server = new ServerSocket(porta);
            socket = server.accept();  // Processo fica bloqueado, à espera de conexões

            // Criar os fluxos de entrada e saída
            entrada = new DataInputStream(socket.getInputStream());
            saida = new DataOutputStream(socket.getOutputStream());

            FileReader fr = new FileReader();
            int NUM_FORTUNES = fr.countFortunes();
            HashMap<Integer, String> hm = new HashMap<>();
            fr.parser(hm);

            while (true) {
                // Recebimento do valor inteiro
                String valor = entrada.readUTF();
                System.out.println(valor);

                String method = valor.split("\"method\": \"")[1].split("\"")[0];

                // Extração do valor de newFort
                String searchString = "\"args\": [\"";
                int startIndex = valor.indexOf(searchString) + searchString.length();
                int endIndex = valor.indexOf("\"]", startIndex);
                String args = valor.substring(startIndex, endIndex);

                // Processamento do valor
                if ("read".equals(method)) {
                    String resultado = fr.read(hm);
                    saida.writeUTF("{\n \"result\": \"" + resultado + "\" \n}");
                } else if ("write".equals(method)) {
                    System.out.println("SHOW: Escrevendo...");
                    String resultado = fr.write(hm, args);
                    saida.writeUTF("{\n \"result\": \"" + resultado + "\" \n}");
                } else {
                    String resultado = "False";
                    saida.writeUTF("{\n \"result\": \"" + resultado + "\" \n}");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
                if (server != null) {
                    server.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
		public static void main(String[] args) {

		new Servidor().iniciar();

	   }
}