package client;


import java.net.*;
import java.io.*;
import java.util.*;
import data.*;

/**
* La clase DiccionarioClient contiene la implementación del cliente
* con los métodos entrar(), lista(), anadir() y salir().
*
*/
public class ClientController {

/** Socket por el que se realiza la comunicación. */
private Socket socket = null;

/** Filtro de recogida de datos del socket. */
private BufferedReader dataReader = null;

/** Filtro de escritura de datos al socket. */
private DataOutputStream dataWriter = null;

/**
* Constructor de DiccionarioClient que realiza la conexión con la dirección IP suministrada.
* @param IP Dirección IP del servidor de diccionario al que conectar.
*/
public ClientController(String IP) {
 try{
     socket = new Socket(IP,DataConnection.PORT);
     dataReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
     dataWriter = new DataOutputStream(socket.getOutputStream());
 }catch(IOException ioe){
   System.err.println(ioe);
 }
}

/**
* Envía las primitivas USUARIO y CLAVE al servidor con los valores apropiados.
* @param u Nombre del usuario con el cual conectar.
* @param c Clave con la cual conectar.
* @return true si se ha permitido el acceso, false en caso contrario.
*/
public boolean entrar(String u, String c){
 try{
   dataWriter.writeBytes("usuario " + u + "\r\n");
   System.out.println(dataReader.readLine()); 
   dataWriter.writeBytes("clave " + c + "\r\n");
   String r = dataReader.readLine();
   System.out.println(r);
   if (r.startsWith("201"))
     return true;
   else
     return false;
 }catch(IOException ioe){
   System.err.println(ioe);
 }
 return false;
}

/**
* Envía la primitiva LISTA al servidor, para obtener la tabla de correspondencias del diccionario..
* @return Un array de String con los contenidos de la tabla de correspondencias del diccionario.

public String[] lista(){
 try{
   Vector vs = new Vector();
   dos.writeBytes("lista\r\n");
   String linea = br.readLine();
   System.out.println(linea);
   for(;!linea.startsWith("202");){
     vs.addElement(linea);
     linea = br.readLine();
     System.out.println(linea);
   }
   String[] as = new String[vs.size()];
   for(int i=0;i<as.length;i++)
     as[i] = (String)vs.elementAt(i);
   return as;
 }catch(IOException ioe){
   System.err.println(ioe);
 }
 return null;
}

/**
* Envía la primitiva INCLUIR al servidor, para añadir una palabra.
* @param pal Palabra a añadir.
* @param trad Traducción correspondiente.

public void incluir(String pal, String trad){
 try{
   dos.writeBytes("incluir "+ pal + " " + trad + "\r\n");
   System.out.println(br.readLine());
 }catch(IOException ioe){
   System.err.println(ioe);
 }
}
*/
/**
* Envía la primitiva SALIR al servidor y realiza la desconexión.
*/
		public void salir(){
		 try{
		   dataWriter.writeBytes("salir\r\n");
		   System.out.println(dataReader.readLine());
		   dataWriter.close();
		   dataReader.close();
		   socket.close();
		 }catch(IOException ioe){
		   System.err.println(ioe);
		 }
		}
}