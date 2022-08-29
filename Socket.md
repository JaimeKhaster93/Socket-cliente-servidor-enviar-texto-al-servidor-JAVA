# Socket-cliente-servidor-enviar-texto-al-servidor-JAVA
Programa desarrollado de la IDE de Java Eclipse donde el Cliente envía un texto personalizado al Servidor y el cliente lo devuelve el mensaje al Cliente en Mayús

CLIENTE:


//Importación de librerias:
#import java.io.DataInputStream;

#import java.io.DataOutputStream;

#import java.io.IOException;

#import java.net.Socket;

#import java.net.UnknownHostException;

#import java.util.Scanner;

//Declaración global de Scanner y Del mensaje a enviar por parte del cliente
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


SERVIDOR

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
