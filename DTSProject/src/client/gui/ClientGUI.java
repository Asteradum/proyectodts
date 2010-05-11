package client.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;
import java.util.Observer;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import client.ClientController;
import client.ServerException.ServerException;




public class ClientGUI extends javax.swing.JFrame implements ActionListener  {

	
	private javax.swing.JButton ENTER;
    private javax.swing.JButton GPSOFFButton;
    private javax.swing.JButton GPSONButton;
    private javax.swing.JTextField IPfield;
    private javax.swing.JButton PASS;
    private javax.swing.JLabel coorLabel;
    private javax.swing.JTextField coordinateField;
    private javax.swing.JButton coordinatesButton;
    private javax.swing.JButton currentValueButton;
    private javax.swing.JTextField gpsField;
    private javax.swing.JLabel gpslabel;
    private javax.swing.JLabel imageComponentLabel;
    private javax.swing.JButton imageButton;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLabel ipLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton logButton;
    private javax.swing.JLabel logLabel;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JPasswordField passField;
    private javax.swing.JLabel passLabel;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JButton quitButton;
    private javax.swing.JTextField sensorField;
    private javax.swing.JComboBox sensorList;
    private javax.swing.JLabel sensorListLabel;
    private javax.swing.JLabel sensorNameLabel;
    private javax.swing.JTextField sensorStateField;
    private javax.swing.JButton stateChangeBottonOFF;
    private javax.swing.JButton stateChangeON;
    private javax.swing.JLabel stateLabel;
    private javax.swing.JLabel statusBar;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField userField;
    private javax.swing.JLabel userLabel;
    TableModel modeloTabla = null;
	
    private ClientController controller = null;
    private int state=0;  
    private boolean logged = false;
    
    
    public ClientGUI(ClientController clientController) {
        super();
        initComponents();
        controller = clientController;
        SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                	

                }
        });
        
}

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings({ "unchecked", "serial" })
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

    	 userLabel = new javax.swing.JLabel();
         userField = new javax.swing.JTextField();
         ENTER = new javax.swing.JButton();
         passField = new javax.swing.JPasswordField();
         passLabel = new javax.swing.JLabel();
         PASS = new javax.swing.JButton();
         ipLabel = new javax.swing.JLabel();
         IPfield = new javax.swing.JTextField();
         statusBar = new javax.swing.JLabel();
         jSeparator1 = new javax.swing.JSeparator();
         progressBar = new javax.swing.JProgressBar();
         sensorListLabel = new javax.swing.JLabel();
         loginLabel = new javax.swing.JLabel();
         logButton = new javax.swing.JButton();
         stateChangeBottonOFF = new javax.swing.JButton();
         imageLabel = new javax.swing.JLabel();
         imageButton = new javax.swing.JButton();
         coordinateField = new javax.swing.JTextField();
         coordinatesButton = new javax.swing.JButton();
         coorLabel = new javax.swing.JLabel();
         GPSONButton = new javax.swing.JButton();
         gpslabel = new javax.swing.JLabel();
         logLabel = new javax.swing.JLabel();
         sensorList = new javax.swing.JComboBox();
         sensorField = new javax.swing.JTextField();
         sensorStateField = new javax.swing.JTextField();
         sensorNameLabel = new javax.swing.JLabel();
         stateLabel = new javax.swing.JLabel();
         stateChangeON = new javax.swing.JButton();
         GPSOFFButton = new javax.swing.JButton();
         gpsField = new javax.swing.JTextField();
         currentValueButton = new javax.swing.JButton();
         quitButton = new javax.swing.JButton();
         jScrollPane2 = new javax.swing.JScrollPane();
         tabla = new javax.swing.JTable();
         imageComponentLabel = new javax.swing.JLabel();
        tabla = new javax.swing.JTable();

        
        this.setContentPane(new GUIContainer());
        
        PASS.addActionListener(this);
        ENTER.addActionListener(this);
        stateChangeBottonOFF.addActionListener(this);
        stateChangeON.addActionListener(this);
        logButton.addActionListener(this);
        coordinatesButton.addActionListener(this);
        imageButton.addActionListener(this);
        GPSOFFButton.addActionListener(this);
        GPSONButton.addActionListener(this);
        sensorList.addActionListener(this);
        currentValueButton.addActionListener(this);
        quitButton.addActionListener(this);
        
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Enviromental Car Application");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        setForeground(new java.awt.Color(153, 204, 255));
        setName("Enviromental Car Application");

        userLabel.setText("User");

        userField.setEnabled(false);

        ENTER.setText("Enter");

        passField.setEnabled(false);
        currentValueButton.setText("Current Value");
        passLabel.setText("Password");

        PASS.setIcon(new javax.swing.ImageIcon("C:\\Users\\SoNia\\workspace\\DTSProject\\src\\images\\llave1.jpg")); // NOI18N
        PASS.setText("Login");
        PASS.setEnabled(false);

        ipLabel.setText("IP");
        quitButton.setText("Exit");
        
        IPfield.setText("");

        statusBar.setText("Dts Project");

        sensorListLabel.setText("Sensors");

        loginLabel.setText("Login");

        logButton.setText("History Log");
        logButton.setEnabled(false);
        currentValueButton.setEnabled(false);

        stateChangeBottonOFF.setText("Change State to OFF");
        stateChangeBottonOFF.setEnabled(false);

        

        imageLabel.setText("Car Image");

        imageButton.setText("See Image");
        imageButton.setEnabled(false);
        

        coordinateField.setEditable(false);

        coordinatesButton.setText("Where it is?");
        coordinatesButton.setEnabled(false);

        coorLabel.setText("(coordinates)");

        GPSONButton.setText("GPS ON");
        GPSONButton.setEnabled(false);
        

        gpslabel.setText("  GPS state");

        logLabel.setText("Log List");

        sensorList.setEnabled(false);

        sensorField.setEditable(false);

        sensorStateField.setEditable(false);

        sensorNameLabel.setText("Name");

        stateLabel.setText("State");

        stateChangeON.setText("Change State to ON");
        stateChangeON.setEnabled(false);

        GPSOFFButton.setText("GPS OFF");
        GPSOFFButton.setEnabled(false);

        gpsField.setEditable(false);

        currentValueButton.setText("Current Value");
        currentValueButton.setEnabled(false);

        quitButton.setText("Exit");
        modeloTabla=(new DefaultTableModel(
        		        new String[][] { {null, null, null, null},
        		                {null, null, null, null},
        		                {null, null, null, null},
        		                {null, null, null, null}
        		            },
					    new String [] {"Date", "Hour", "Coordinates", "Value"})
            			{
		                @SuppressWarnings("unused")
						boolean[] canEdit = new boolean [] {
		                    false, false, false, false
		                };
		        	});
        tabla.setModel(modeloTabla);
        jScrollPane2.setViewportView(tabla);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(passLabel)
                                        .addGap(18, 18, 18))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(ipLabel)
                                            .addComponent(userLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(27, 27, 27)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(IPfield)
                                    .addComponent(passField, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(userField, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(PASS, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ENTER)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addComponent(loginLabel))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(coordinatesButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(coordinateField, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(coorLabel))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(GPSONButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(GPSOFFButton, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(gpsField, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(gpslabel)
                                    .addComponent(imageLabel)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(imageComponentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(imageButton))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(100, 100, 100)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(sensorNameLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(sensorField, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(45, 45, 45)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(sensorList, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(sensorListLabel, javax.swing.GroupLayout.Alignment.LEADING)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(stateLabel)
                                                .addGap(18, 18, 18)
                                                .addComponent(sensorStateField, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(stateChangeON, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(stateChangeBottonOFF, javax.swing.GroupLayout.Alignment.LEADING))))
                                        .addContainerGap(195, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(quitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(198, 198, 198)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(119, 119, 119)
                                    .addComponent(logButton)
                                    .addContainerGap()))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(logLabel)
                                .addContainerGap()))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(25, 25, 25)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(currentValueButton)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusBar, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(324, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(loginLabel)
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ipLabel)
                            .addComponent(IPfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ENTER))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(userField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(userLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(passField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PASS)
                            .addComponent(passLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(gpslabel)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(GPSONButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(GPSOFFButton)
                                    .addComponent(logButton)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(gpsField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(sensorListLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sensorList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sensorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sensorNameLabel))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(stateLabel)
                            .addComponent(sensorStateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stateChangeBottonOFF))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stateChangeON)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imageLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(imageComponentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(logLabel)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(currentValueButton)))
                .addGap(33, 33, 33)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(imageButton)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(coordinatesButton)
                    .addComponent(coordinateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(coorLabel)
                    .addComponent(quitButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusBar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }// </editor-fold>

    /**
    * @param args the command line arguments
    */
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() instanceof JButton)
        {
                JButton buttonPressed=(JButton)e.getSource();
                if (buttonPressed==ENTER)
                {  			boolean connect=controller.connect(IPfield.getText());
                              if (connect){
                               IPfield.setEnabled(false);
                			   userField.setEnabled(true);
                               passField.setEnabled(true);
                               PASS.setEnabled(true);
                               stateChangeBottonOFF.setEnabled(true);
                               stateChangeON.setEnabled(true);
                               imageButton.setEnabled(true);
                               sensorList.setEnabled(true);
                               logButton.setEnabled(true);
                               GPSOFFButton.setEnabled(true);
                               GPSONButton.setEnabled(true);
                               coordinatesButton.setEnabled(true);
                               statusBar.setText("Conected");
                               sensorField.setEnabled(true);
                               sensorStateField.setEnabled(true);
                               gpsField.setEnabled(true);
                               currentValueButton.setEnabled(true);
                               quitButton.setEnabled(true);
                              }
                  else statusBar.setText("Conection Refused. Introduce the IP again.");
                        }
                
                
                else if (buttonPressed==PASS){
                        String user = userField.getText();       
                        String pass = new String(passField.getPassword());
                        try {
							controller.login(user, pass);
							
							
                        } catch (ServerException e1) {
							statusBar.setText(e1.getMessage());
			            }
                        List<String> list = null;
                		try {
                			list = controller.getListSensors();
                			logged = true;
                			for (int i=0; i<list.size(); i++){
                				StringTokenizer sTok= new StringTokenizer(list.get(i),":");
                				String item=sTok.nextToken();
                				sensorList.addItem(item);
                			}
                			gpsField.setText(controller.getGPSState());
                		} catch (ServerException e1) {
                			statusBar.setText(e1.getMessage());} 
                		
                		
					}
        
                
                
                
                 else if (buttonPressed==stateChangeBottonOFF){
                	 sensorStateField.setText("OFF");
						
                	 try {
                		controller.setSensorOFF(sensorList.getSelectedItem().toString());
                		} catch (ServerException e2) {
                			statusBar.setText(e2.getMessage());
                				
            			}
                     }
                
                
                
                 else if (buttonPressed==stateChangeON){
                	sensorStateField.setText("ON");
						
                	 try {
                		controller.setSensorON(sensorList.getSelectedItem().toString());
	                	} catch (ServerException e2) {
	                		statusBar.setText(e2.getMessage());
	                    	}
                     }
                
                
                
                
                 else if (buttonPressed==logButton){
                	List<String> list = null;
                	try {
             			list = controller.getHistoryLog(sensorList.getSelectedItem().toString());
             			((DefaultTableModel) modeloTabla).setRowCount(0);
    					for (int i=0; i<list.size(); i++)
                      		{StringTokenizer sTok= new StringTokenizer(list.get(i),"; ");
               				String date=sTok.nextToken();
               				String hour=sTok.nextToken();
               				String coordenates=sTok.nextToken();
               				String value=sTok.nextToken();
               				Object [] row = new Object[4];
                            row[0] = date;
                            row[1] =hour;
                            row[2] = coordenates;
                            row[3] = value;
                             ((DefaultTableModel) modeloTabla).addRow( row );
                     	}
             			statusBar.setText("Historical log of the selected sensor: "+ sensorField.getText());
                 } catch (ServerException e1) {
             			System.out.println(e1.getMessage());
             			statusBar.setText(e1.getMessage());
                 		}
                 }
                 
                 
                 else if (buttonPressed==currentValueButton){
                 	try {
                 		if (sensorStateField.getText().equals("ON"))
                 		{	((DefaultTableModel) modeloTabla).setRowCount(0);
                 		
                 		String line=controller.getCurrentValue(sensorList.getSelectedItem().toString());
                 		StringTokenizer sTok= new StringTokenizer(line,";");
           				String date=sTok.nextToken();
           				String hour=sTok.nextToken();
           				String coordenates=sTok.nextToken();
           				String value=sTok.nextToken();
           				Object [] row = new Object[4];
                        row[0] = date;
                        row[1] =hour;
                        row[2] = coordenates;
                        row[3] =value;
                        ((DefaultTableModel) modeloTabla).addRow( row );
                        statusBar.setText("Current value of the selected sensor: "+ sensorField.getText());}
                  } catch (ServerException e1) {
              			statusBar.setText(e1.getMessage());
                  		}
                  }
                
                
                 else if (buttonPressed==quitButton){ 
	             	if(logged){
	             		controller.quit();
	             		System.out.println("Estaba logeado");
	             	}
	                 System.exit(0);
	             }
                 else if (buttonPressed==imageButton)
                 { try {
                		state=1;
                		controller.getPicture();
             			
             		} catch (ServerException e1) {
             			imageComponentLabel.setIcon(new javax.swing.ImageIcon("recievedData\\Photo.jpg")); // NOI18N
             			statusBar.setText("Here you have the picture");
             			
             		}
                 }
                 
                 
                 else if (buttonPressed==coordinatesButton)
		                 {try {
		                	 if (state==1)
		                	 { coordinateField.setText(controller.getLocation());
		                	 statusBar.setText("The vehicle is there");}
		                	 else statusBar.setText("Get a picture first!");
		               } catch (ServerException e2) {
		             			System.out.println(e2.getMessage());
		             			statusBar.setText(e2.getMessage());
		             		}
		                 }
                
                
                
                 else if (buttonPressed==GPSOFFButton)
                	{gpsField.setText("OFF");
		    	 try {
                    		controller.setGPSOFF();
    	        }catch (ServerException e2) {
                	 statusBar.setText(e2.getMessage());}
                         } 
                
                
                 else if (buttonPressed==GPSONButton)
             	{gpsField.setText("ON");
                try {
                	controller.setGPSON();
 	            } catch (ServerException e2) {
 	            	statusBar.setText(e2.getMessage());
 	             	}
                      } 
                	 
                 }
        
        
        
                
        else if (e.getSource() instanceof JComboBox )
		 {      List<String> list = null;
		        ((DefaultTableModel) modeloTabla).setRowCount(0);
		        for (int i=0;i<4;i++)
		           {Object [] row = new Object[4] ;
		           ((DefaultTableModel) modeloTabla).addRow( row );
		           }
			
		 try {
		   			list = controller.getListSensors();
		   			int i=sensorList.getSelectedIndex();
		   			StringTokenizer sTok= new StringTokenizer(list.get(i),": ");
		   			String code=sTok.nextToken();
		   			String name=sTok.nextToken();
		   			String state=sTok.nextToken();
		   			sensorField.setText(name);
		   			sensorStateField.setText(state);
		   		 	
		   		} catch (ServerException e1) {
		   			System.out.println(e1.getMessage());
		   			statusBar.setText(e1.getMessage());}
		   		 	
        }
                        
                        
    }
                
}