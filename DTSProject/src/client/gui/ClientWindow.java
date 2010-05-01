package client.gui;

import java.awt.*;
import java.net.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import client.*;

public class ClientWindow extends JFrame {
  JTextArea txtInfo = new JTextArea();
  JButton btnConectar = new JButton();
  JButton btnLista = new JButton();
  JTextField txtPalabra = new JTextField();
  JTextField txtTraduccion = new JTextField();
  JButton btnAnadir = new JButton();
  JButton btnSalir = new JButton();
  private ClientController dc;
  int estado=-1;
  JTextField txtUsuario = new JTextField();
  JButton btnValidar = new JButton();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JPasswordField txtClave = new JPasswordField();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();


  public ClientWindow() {
    try  {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    ClientWindow ventanaCliente1 = new ClientWindow();
    ventanaCliente1.show();
  }

  private void jbInit() throws Exception {
    this.setTitle("Cliente de diccionario");
    this.addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        this_windowClosing(e);
      }
    });
    btnConectar.setLabel("Conectar");
    btnConectar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnConectar_actionPerformed(e);
      }
    });
    btnLista.setEnabled(false);
    btnLista.setLabel("Lista");
    btnLista.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnLista_actionPerformed(e);
      }
    });
    btnAnadir.setEnabled(false);
    btnAnadir.setLabel("Añadir");
    btnAnadir.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnAnadir_actionPerformed(e);
      }
    });
    this.getContentPane().setLayout(null);
    txtInfo.setBorder(BorderFactory.createEtchedBorder());
    txtInfo.setBounds(new Rectangle(39, 141, 188, 154));
    btnSalir.setEnabled(false);
    btnSalir.setActionCommand("Salir");
    btnSalir.setText("Salir");
    btnSalir.setBounds(new Rectangle(37, 315, 89, 27));
    btnSalir.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        btnSalir_actionPerformed(e);
      }
    });
    btnConectar.setBounds(new Rectangle(308, 19, 88, 27));
    btnLista.setBounds(new Rectangle(148, 314, 78, 27));
    txtPalabra.setEnabled(false);
    txtPalabra.setBounds(new Rectangle(334, 185, 89, 24));
    txtTraduccion.setEnabled(false);
    txtTraduccion.setBounds(new Rectangle(334, 226, 89, 22));
    btnAnadir.setBounds(new Rectangle(342, 263, 78, 27));
    txtUsuario.setEnabled(false);
    txtUsuario.setBounds(new Rectangle(139, 24, 89, 24));
    txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {

      public void keyReleased(KeyEvent e) {
        txtUsuario_keyReleased(e);
      }
    });
    btnValidar.setEnabled(false);
    btnValidar.setText("jButton1");
    btnValidar.setLabel("Validar");
    btnValidar.setBounds(new Rectangle(308, 61, 88, 27));
    btnValidar.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        btnValidar_actionPerformed(e);
      }
    });
    jLabel1.setText("Nombre:");
    jLabel1.setBounds(new Rectangle(48, 30, 68, 15));
    jLabel2.setText("Clave:");
    jLabel2.setBounds(new Rectangle(48, 62, 82, 28));
    txtClave.setEnabled(false);
    txtClave.setBounds(new Rectangle(141, 63, 88, 23));
    txtClave.addKeyListener(new java.awt.event.KeyAdapter() {

      public void keyReleased(KeyEvent e) {
        txtClave_keyReleased(e);
      }
    });
    jLabel3.setText("Palabra:");
    jLabel3.setBounds(new Rectangle(244, 186, 52, 22));
    jLabel4.setText("Traduccion:");
    jLabel4.setBounds(new Rectangle(244, 222, 73, 31));
    this.getContentPane().add(btnValidar, null);
    this.getContentPane().add(jLabel1, null);
    this.getContentPane().add(jLabel2, null);
    this.getContentPane().add(txtUsuario, null);
    this.getContentPane().add(btnConectar, null);
    this.getContentPane().add(txtClave, null);
    this.getContentPane().add(txtInfo, null);
    this.getContentPane().add(btnSalir, null);
    this.getContentPane().add(btnLista, null);
    this.getContentPane().add(jLabel3, null);
    this.getContentPane().add(txtTraduccion, null);
    this.getContentPane().add(txtPalabra, null);
    this.getContentPane().add(jLabel4, null);
    this.getContentPane().add(btnAnadir, null);
    this.setSize(450,420);
  }

  void btnConectar_actionPerformed(ActionEvent e) {
    dc = new ClientController("127.0.0.1");
    estado=0;
    actualizarEstado(estado);
  }

  void btnLista_actionPerformed(ActionEvent e) {
  /*  txtInfo.setText("");
   // String[] as = dc.lista();
    for(int i=0;i<as.length;i++){
      txtInfo.append(as[i]+"\r\n");
    }*/
  }

  void btnAnadir_actionPerformed(ActionEvent e) {
  //  dc.incluir(txtPalabra.getText(),txtTraduccion.getText());
  }

  void this_windowClosing(WindowEvent e) {
    if (dc!=null) dc.salir();
    System.exit(0);
  }

  void btnSalir_actionPerformed(ActionEvent e) {
    dc.salir();
    System.exit(0);
  }

  void actualizarEstado(int estado){
      switch(estado){
        case 0:
            btnConectar.setEnabled(false);
            txtUsuario.setEnabled(true);
            txtClave.setEnabled(true);
            btnSalir.setEnabled(true);
            break;

        case 1:
            btnValidar.setEnabled(true);
            break;
            
        case 2:
            btnValidar.setEnabled(false);
            txtUsuario.setEnabled(false);
            txtClave.setEnabled(false);
            txtPalabra.setEnabled(true);
            txtTraduccion.setEnabled(true);
            btnLista.setEnabled(true);
            btnAnadir.setEnabled(true);
            break;

      }


  }

  void btnValidar_actionPerformed(ActionEvent e) {
       boolean validado=dc.entrar(txtUsuario.getText(),txtClave.getText());
       if(validado) estado=2;
       actualizarEstado(estado);
       
  }

  void txtUsuario_keyReleased(KeyEvent e) {
     if(txtUsuario.getText().equals("")||txtClave.getText().equals("")){}
     else
        actualizarEstado(1);
  }  


  void txtClave_keyReleased(KeyEvent e) {
      if(txtUsuario.getText().equals("")||txtClave.getText().equals("")){}
     else
        actualizarEstado(1);
  }
}
