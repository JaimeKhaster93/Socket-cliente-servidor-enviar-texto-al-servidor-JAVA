package socket_text;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class servidor {
	public static void main (String [] args) {

		remitente();
	}
	
	public static void remitente () {
		
		String mensaje = "", nmensaje = "";
		
		ServerSocket servidor = null;
		Socket sc = null;
		DataInputStream entrada;
		DataOutputStream salida;
		
		final int PUERTO = 501;
		
		try {
			servidor = new ServerSocket();
			servidor = new ServerSocket(PUERTO);
			System.out.println("*SE HA INICIADO EL SERVIDOR*");
			
			
			
			while(true) {
				
				
				sc = servidor.accept();
				System.out.println("*EL CLIENTE SE HA CONECTADO*");
				entrada = new DataInputStream(sc.getInputStream());
				salida = new DataOutputStream(sc.getOutputStream());
				
				System.out.println("------------------------------------------");
				mensaje = entrada.readUTF();
				System.out.println("EL CLIENTE ENVÍA: " + mensaje);
				
								if(mensaje.equals("salir")) {
									
								salida.writeUTF("*******");	
								System.out.println("EL CLIENTE RECIBE: " + "*******");	
								System.out.println("------------------------------------------");
								sc.close();
								System.out.println("*EL CLIENTE SE HA DESCONECTADO*");
								
								
								}else {
									nmensaje = mensaje.toUpperCase();
									System.out.println("EL CLIENTE RECIBE: " + nmensaje);
									System.out.println("------------------------------------------");
									salida.writeUTF(""+nmensaje);
									
								}
			}
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}