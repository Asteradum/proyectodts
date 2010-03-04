/*
 * @(#)DiccionarioDemonio.html
 * Proyecto Diccionario remoto
 */


package server;

import java.net.*;
import java.io.*;
import java.util.*;
import data.*;

/**
 * La clase DiccionarioDemonio contiene la implementación del proceso
 * demonio que escucha solicitudes de conexión.
 *
 */
public class DaemonServer {

  /**
  * Inicializa la tabla de correspondencias del diccionario, realiza
  * el proceso de espera de solicitudes de conexión y crea
  * instancias de DiccionarioServer para atenderlas.
  * @param args Argumentos de línea de comandos.
  */
  public static void main(String args[]){
    Hashtable h = new Hashtable();
    try{
      ServerSocket ss = new ServerSocket(DataConnection.PORT);
      for(;;){
        Server ds = new Server(h,ss.accept());
        ds.start();
      }
    }catch(IOException ioe){
      System.err.println(ioe);
    }
  }
}