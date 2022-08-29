package socket_text;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class cliente {
	
	public static String texto_a_enviar; // Declaramos el mensaje de forma global
	public static Scanner teclado = new Scanner(System.in); // Inicializamos el Scanner
	
	
	public static void main (String [] args) {
		
		

		System.out.println("*ENTRÓ AL SERVIDOR*");
		do {
			
			System.out.print("Escribe el texto a enviar o escribe salir para desconectar: ");
			texto_a_enviar = teclado.nextLine(); //Entrada de texto 
			
				solicitud(); //Método del cliente para solicitar la entrada del servidor
			
		}while(!texto_a_enviar.equals("salir")); //Sale cuando se inserta salir
		
		
		System.out.print("*SALIÓ DEL SERVIDOR*");
		
	}
	
	public static void solicitud() {
		
		
		final String HOST = "127.0.0.1";
		final int PUERTO = 501;
		DataInputStream entrada;
		DataOutputStream salida;
		
		
		try {
			
			Socket socket_cliente = new Socket (HOST, PUERTO);
				
				entrada = new DataInputStream(socket_cliente.getInputStream());
				salida = new DataOutputStream(socket_cliente.getOutputStream());
				
				System.out.println("------------------------------------------");
				salida.writeUTF(texto_a_enviar); //Se envia al servidor el texto
				System.out.print("EL SERVIDOR RECIBE: " + texto_a_enviar +"\n");
				
				
				String mensaje_recibido = entrada.readUTF(); //Se recibe del servidor el texto pero en mayus
				System.out.print("EL SERVIDOR ENVIA: " + mensaje_recibido +"\n");
				System.out.println("------------------------------------------");
				
				
				socket_cliente.close();

			
		
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	


	
}
