//Sonius, este es el servidor de la practica diccionario copiado aqui, no hay nada implementado
// cuando le pille un poco mas el truco ya lo cambiare como debe :-)

package server;

import java.net.*;
import java.io.*;
import java.util.*;

/**
 * La clase DiccionarioServer contiene la implementación del proceso
 * servidor que se ejecuta como un hilo independiente. Dicha implementación
 * se encuentra en el método run().
 *
 */
public class Server extends Thread{

  /** Socket por el que se realiza la comunicación. */
  private Socket s;

  /** Filtro de recogida de datos del socket. */
  private BufferedReader br;

  /** Filtro de escritura de datos al socket. */
  private DataOutputStream dos;

  /** Referencia a la tabla de correspondencias del diccionario. */
  private Hashtable  dic;
///**hola*
  /**
  * Constructor de DiccionarioServer, para atender a un cliente.
  * @param d Tabla de correspondencias de diciconario a utilizar.
  * @param sc Socket que comunica con el cliente conectado.
  */
  public Server( Hashtable d, Socket sc) {
    try{
      dic = d;
      s = sc;
      br = new BufferedReader(new InputStreamReader(s.getInputStream()));
      dos = new DataOutputStream(s.getOutputStream());
    }catch(IOException ioe){
      System.err.println(ioe);
    }
  }

  /**
  * Implementación del proceso de comunicación con el cliente.
  */
  public void run(){
    try{
      String linea,comando,usuario="",clave="";
      int estado = 0;
      for (;estado<3;){
        linea = br.readLine();
        if (linea==null) return;
        System.out.println("Recibida desde " + s.getInetAddress().getHostAddress() + " la linea: " + linea);
        StringTokenizer sTok= new StringTokenizer(linea," ");
        comando = sTok.nextToken().toUpperCase();

        switch(estado){
          case 0:
            if(comando.equals("USUARIO")){
              usuario = sTok.nextToken();
              dos.writeBytes("200 OK Espero clave\r\n");
              estado = 1;
            }else if(comando.equals("SALIR")){
              estado = 3;
            }else{
              dos.writeBytes("500 ERR Comando incorrecto\r\n");
            }
            break;

          case 1:
            if(comando.equals("CLAVE")){
              clave = sTok.nextToken();
              if(usuario.equals("std") && clave.equals("practica")){
                dos.writeBytes("201 OK Acceso concedido\r\n");
                estado = 2;
              }else{
                dos.writeBytes("400 ERR Acceso denegado. Repita\r\n");
                estado = 0;
              }
            }else if(comando.equals("SALIR")){
              estado = 3;
            }else{
              dos.writeBytes("500 ERR Comando incorrecto\r\n");
            }
            break;

          case 2:
            if(comando.equals("LISTA")){
              Enumeration ky = dic.keys();
              Enumeration el = dic.elements();
              for(;ky.hasMoreElements();){
                dos.writeBytes((String)ky.nextElement() + " " + (String)el.nextElement() + "\r\n");
              }
              dos.writeBytes("202 OK Fin de la lista\r\n");
            }else if(comando.equals("INCLUIR")){
              dic.put(sTok.nextToken(),sTok.nextToken());
              dos.writeBytes("203 OK Palabra incluida\r\n");
            }else if(comando.equals("BORRAR")){
              String ke=sTok.nextToken();
              if(dic.containsKey(ke)){
                dic.remove(ke) ;
                dos.writeBytes("204 OK Palabra borrada\r\n");
              }else{
                dos.writeBytes("401 ERR Palabra no encontrada\r\n");
              }
            }else if(comando.equals("SALIR")){
              estado = 3;
            }else{
              dos.writeBytes("500 ERR Comando incorrecto\r\n");
            }
            break;
        }
      }
    dos.writeBytes("204 OK Fin de la sesion\r\n");
    dos.close();
    br.close();
    s.close();
    }catch(IOException ioe){
      System.err.println(ioe);
    }catch(NoSuchElementException nsee){
      System.err.println(nsee);
    }
  }
}