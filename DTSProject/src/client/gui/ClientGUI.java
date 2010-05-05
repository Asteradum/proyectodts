package client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;
import java.util.Observer;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SwingUtilities;
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
    private javax.swing.JTextField gpsField;
    private javax.swing.JLabel gpslabel;
    private javax.swing.JTextArea image;
    private javax.swing.JButton imageButton;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLabel ipLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton logButton;
    private javax.swing.JLabel logLabel;
    private javax.swing.JList logList;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JPasswordField passField;
    private javax.swing.JLabel passLabel;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JTextField sensorField;
    private javax.swing.JComboBox sensorList;
    private javax.swing.JLabel sensorListLabel;
    private javax.swing.JLabel sensorNameLabel;
    private javax.swing.JTextField sensorStateField;
    private javax.swing.JButton stateChangeBottonOFF;
    private javax.swing.JButton stateChangeON;
    private javax.swing.JLabel stateLabel;
    private javax.swing.JLabel statusBar;
    private javax.swing.JTextField userField;
    private javax.swing.JLabel userLabel;
    private javax.swing.JButton currentValueButton;
    private javax.swing.JButton quitButton;
    private ClientController controller = null;
    private int state=0;  
    
    
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
    @SuppressWarnings("unchecked")
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
        jScrollPane2 = new javax.swing.JScrollPane();
        logList = new javax.swing.JList();
        stateChangeBottonOFF = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        image = new javax.swing.JTextArea();
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
        currentValueButton= new javax.swing.JButton();
        quitButton= new javax.swing.JButton();

        
        this.setContentPane(new Contenedor());
        
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
        setName("Enviromental Car Application"); // NOI18N

        userLabel.setText("User");

        userField.setEnabled(false);

        ENTER.setText("Enter");
        currentValueButton.setText("Current Value");

        passField.setEnabled(false);

        passLabel.setText("Password");

        PASS.setIcon(new javax.swing.ImageIcon("C:\\Users\\SoNia\\workspace\\DTSProject\\src\\images\\llave1.jpg")); // NOI18N
        PASS.setText("Login");
        PASS.setEnabled(false);
        
        quitButton.setText("Exit");
        quitButton.setEnabled(false);

        ipLabel.setText("IP");

        IPfield.setText(" ");

        statusBar.setText("Dts Project");

        sensorListLabel.setText("Sensors");

        loginLabel.setText("Login");

        logButton.setText("History Log");
        logButton.setEnabled(false);
        currentValueButton.setEnabled(false);

        jScrollPane2.setViewportView(logList);

        stateChangeBottonOFF.setText("Change State to OFF");
        stateChangeBottonOFF.setEnabled(false);
        image.setColumns(20);
        image.setEditable(false);
        image.setRows(5);
        jScrollPane1.setViewportView(image);

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

        sensorList.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        sensorList.setEnabled(false);

        sensorField.setEditable(false);
        gpsField.setEditable(false);

        sensorStateField.setEditable(false);

        sensorNameLabel.setText("Name");

        stateLabel.setText("State");

        stateChangeON.setText("Change State to ON");
        stateChangeON.setEnabled(false);

        GPSOFFButton.setText("GPS OFF");
        GPSOFFButton.setEnabled(false);

        gpsField.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(statusBar, javax.swing.GroupLayout.DEFAULT_SIZE, 737, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quitButton)
                        .addGap(37, 37, 37))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(86, 86, 86)
                                .addComponent(loginLabel))
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
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ENTER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(PASS)))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(49, 49, 49)
                                        .addComponent(imageLabel))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(imageButton))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(51, 51, 51)
                                                .addComponent(sensorList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(sensorNameLabel)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(sensorField, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(63, 63, 63)
                                                        .addComponent(logButton))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(stateLabel)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(sensorStateField, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                            .addComponent(stateChangeON, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(stateChangeBottonOFF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGap(62, 62, 62))))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(87, 87, 87)
                                        .addComponent(sensorListLabel)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(69, 69, 69)
                                        .addComponent(logLabel))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(coordinatesButton)
                                    .addComponent(GPSONButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(coordinateField, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(56, 56, 56)
                                        .addComponent(coorLabel))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(gpslabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(gpsField, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(GPSOFFButton)))
                                .addGap(169, 169, 169)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(687, Short.MAX_VALUE)
                .addComponent(currentValueButton)
                .addGap(63, 63, 63))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ipLabel)
                            .addComponent(IPfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ENTER)
                            .addComponent(sensorList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(imageLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(sensorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sensorNameLabel)
                                    .addComponent(logButton))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(stateLabel)
                                    .addComponent(sensorStateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(stateChangeBottonOFF))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(stateChangeON))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(loginLabel)
                            .addComponent(sensorListLabel)
                            .addComponent(logLabel))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addComponent(currentValueButton)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(imageButton))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(gpslabel)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(coordinateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(coorLabel))
                                        .addGap(4, 4, 4))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(gpsField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(GPSONButton)
                                            .addComponent(GPSOFFButton))
                                        .addGap(42, 42, 42)
                                        .addComponent(coordinatesButton)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addComponent(statusBar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quitButton)
                        .addContainerGap())))
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
					    System.out.println("helloooooo");
                        List<String> list = null;
                   		try {
                   			list = controller.getListSensors();
                   			for (int i=0; i<list.size(); i++){
                   				System.out.println(list.get(i));
                   				StringTokenizer sTok= new StringTokenizer(list.get(i),":");
                   				String item=sTok.nextToken();
                   				System.out.println(item);
                   				sensorList.addItem(item);}
                   		} catch (ServerException e1) {
                   			System.out.println(e1.getMessage());
                   			statusBar.setText("This action can not be completed. Please try again later");}
						}
                
                
                
                 else if (buttonPressed==stateChangeBottonOFF){
                	 sensorStateField.setText("OFF");
						
                	 try {
                		controller.setSensorOFF(sensorList.getSelectedItem().toString());
                		statusBar.setText("This action can not be completed. Please try again later");
            			} catch (ServerException e2) {
            			}
                     }
                
                
                
                 else if (buttonPressed==stateChangeON){
                	 sensorStateField.setText("ON");
						
                	 try {
                		controller.setSensorON(sensorList.getSelectedItem().toString());
	                	} catch (ServerException e2) {
	                		statusBar.setText("State has changed correctly");
	                    	}
                     }
                
                
                
                
                 else if (buttonPressed==logButton){
                	List<String> list1 = null;
                	try {
             			list1 = controller.getHistoryLog(sensorList.getSelectedItem().toString());
             			Vector<String> vector=new Vector<String> (list1.size());
                      	for (int i=0; i<list1.size(); i++)
                      		vector.add(list1.get(i).toString());
                      	logList.setListData(vector);
             			statusBar.setText("Historical log of the selected sensor: "+ sensorField.getText());
                 } catch (ServerException e1) {
             			System.out.println(e1.getMessage());
             			statusBar.setText("This action can not be completed. Please try again later");
                 		}
                 }
                 
                 
                 else if (buttonPressed==currentValueButton){
                 	List<String> list1 = null;
                 	try {
              			list1 = controller.getHistoryLog(sensorList.getSelectedItem().toString());
              			Vector<String> vector=new Vector<String> (list1.size());
                       	for (int i=0; i<list1.size(); i++)
                       		vector.add(list1.get(i).toString());
                       	vector.add (controller.getCurrentValue(sensorList.getSelectedItem().toString()));
              			logList.setListData(vector);
              			statusBar.setText("Historical log of the selected sensor: "+ sensorField.getText());
                  } catch (ServerException e1) {
              			System.out.println(e1.getMessage());
              			statusBar.setText("The Sensor has to be active");
                  		}
                  }
                
                
                
                 else if (buttonPressed==quitButton)
                	 
                 {
                	 controller.quit();
                 }
                
                 else if (buttonPressed==imageButton)
                	 try {
             			controller.getPicture();
             			statusBar.setText("Here you have the picture");
                 		state=1;
             		} catch (ServerException e1) {
             			System.out.println(e1.getMessage());
             			statusBar.setText("This action can not be completed. Please try again later");
             		}
                 
                 
                 
                 else if (buttonPressed==coordinatesButton)
		                 {try {
		                	 if (state==1)
		                	 { coordinateField.setText(controller.getLocation());
		                	 statusBar.setText("The vehicle is there");}
		                	 else statusBar.setText("Get a picture first!");
		               } catch (ServerException e2) {
		             			System.out.println(e2.getMessage());
		             			statusBar.setText("This action can not be completed. Please try again later");
		             		}
		                 }
                
                
                
                 else if (buttonPressed==GPSOFFButton)
                	{
                	 gpsField.setText("OFF");
		    	 try {
                    		controller.setGPSOFF();
    	        }catch (ServerException e2) {
                	 statusBar.setText("State has changed correctly");}
                         } 
                
                
                 else if (buttonPressed==GPSONButton)
             	{gpsField.setText("ON");
                try {
                	controller.setGPSON();
 	            } catch (ServerException e2) {
 	            	statusBar.setText("State has changed correctly");
 	             	}
                      } 
                	 
                 }
        
        
        
                
        else if (e.getSource() instanceof JComboBox )
		 {      List<String> list = null;
		   		try {
		   			logList.removeAll();
		   		    Vector<String> vector=new Vector<String> (1);
		   		    logList.setListData(vector);
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
		   			statusBar.setText("This action can not be completed. Please try again later");}
		   		 	
        }
                        
                        
    }
                
}