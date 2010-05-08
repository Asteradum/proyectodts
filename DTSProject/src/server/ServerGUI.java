package server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import server.userDAO.UserDAO;

import client.ClientController;

public class ServerGUI extends javax.swing.JFrame implements ActionListener {

   
	  // Variables declaration - do not modify
	 private javax.swing.JButton ExitButton;
	    private javax.swing.JButton NumberButton;
	    private javax.swing.JTextField clientNumberField;
	    private javax.swing.JComboBox conectedUsers;
	    private javax.swing.JLabel controlLabel;
	    private javax.swing.JButton deleteButton;
	    private javax.swing.JButton disconectButton;
	    private javax.swing.JLabel jLabel1;
	    private javax.swing.JLabel jLabel2;
	    private javax.swing.JLabel jLabel3;
	    private javax.swing.JLabel jLabel4;
	    private javax.swing.JLabel jLabel5;
	    private javax.swing.JScrollPane jScrollPane1;
	    private javax.swing.JScrollPane jScrollPane2;
	    private javax.swing.JTable Table;
	    private javax.swing.JTextArea jTextArea1;
	    private javax.swing.JTextField maxNumber;
	    private javax.swing.JButton modifyBotton;
	    private javax.swing.JTextField nameField;
	    private javax.swing.JButton newButton;
	    private javax.swing.JTextField nikField;
	    private javax.swing.JLabel numberConectLabel;
	    private javax.swing.JTextField passField;
	    private javax.swing.JLabel statusBar;
    TableModel model = null;
    ServerController controller=null;
    UserDAO dao= new UserDAO();
    // End of variables declaration
	
	
	
	/** Creates new form ServerGUI */
    public ServerGUI(ServerController serverController) {
        super();
        initComponents();
        controller = serverController;
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

    	controlLabel = new javax.swing.JLabel();
        clientNumberField = new javax.swing.JTextField();
        numberConectLabel = new javax.swing.JLabel();
        maxNumber = new javax.swing.JTextField();
        NumberButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        conectedUsers = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        disconectButton = new javax.swing.JButton();
        newButton = new javax.swing.JButton();
        modifyBotton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        nikField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        passField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        ExitButton = new javax.swing.JButton();
        statusBar = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        
       
        
        ExitButton.addActionListener(this);
        NumberButton.addActionListener(this);
        disconectButton.addActionListener(this);
        newButton.addActionListener(this);
        modifyBotton.addActionListener(this);
        deleteButton.addActionListener(this);
        conectedUsers.addActionListener(this);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        controlLabel.setText("Total number of connected clients:");

        numberConectLabel.setText("Set maximun number of conections:");

        NumberButton.setText("Add");
       

        jLabel1.setText("Connected Users");

        conectedUsers.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "", "", "" }));
       
        model=(new DefaultTableModel(
			        				new String [][] {
			    	                {null, null, null},
			    	                {null, null, null},
			    	                {null, null, null},
			    	                {null, null, null}
			    	            },
				    	            new String [] {
				    	                "Nick", "Name", "Password"
				    	            }
    	        ) 
        		);
        loadTable();
        Table.setModel(model);
     
        jScrollPane1.setViewportView(Table);

        jLabel2.setText("Total Users");

        disconectButton.setText("Disconect?");

        newButton.setText("New User");

        modifyBotton.setText("Modify User");

        deleteButton.setText("Delete User");

        jLabel3.setText("Nick");

        jLabel4.setText("Password");

        

        jLabel5.setText("Name");

        ExitButton.setText("Exit");

        statusBar.setText("Server GUI");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("If you want to modify a user,\n select it first in the table\n");
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                                .addComponent(conectedUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addComponent(numberConectLabel)
                            .addComponent(controlLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(maxNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(clientNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(NumberButton))
                            .addComponent(disconectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nikField, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                                    .addComponent(passField, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
                                .addGap(70, 70, 70))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(nameField, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGap(89, 89, 89)))
                .addGap(101, 101, 101)
                .addComponent(ExitButton)
                .addGap(53, 53, 53))
            .addComponent(statusBar, javax.swing.GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(modifyBotton)
                                .addComponent(newButton)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(deleteButton))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(deleteButton)))
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ExitButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(numberConectLabel)
                            .addComponent(maxNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NumberButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(clientNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(controlLabel))
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(conectedUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(disconectButton))
                        .addGap(141, 141, 141)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(nikField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(passField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(newButton)
                                .addGap(18, 18, 18)
                                .addComponent(modifyBotton)))
                        .addGap(19, 19, 19)))
                .addComponent(statusBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }// </editor-fold>

   

   



	private void loadTable() {
    	((DefaultTableModel) model).setRowCount(0);
		List<String> list= new ArrayList<String>();
    	try {
			dao.connect();
			list=dao.getUsers();
			dao.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i=0;i<list.size();i++)
		{ System.out.println(list.get(i));
			StringTokenizer sTok= new StringTokenizer(list.get(i),";");
				String nik=sTok.nextToken();
				String name=sTok.nextToken();
				String pass=sTok.nextToken();
				Object [] row = new Object[3];
            row[0] = nik;
            row[1] =name;
            row[2] = pass;
            ((DefaultTableModel) model).addRow( row );
		}
		
	}



	/**
    * @param args the command line arguments
    */
    

	@Override
public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() instanceof JButton)
        {
                JButton buttonPressed=(JButton)e.getSource();
                
                
                if (buttonPressed==NumberButton)
                {
                	String number=maxNumber.getText();
                	controller.setMaxNumberConnections(Integer.parseInt(number));
                	statusBar.setText("Max number fixed");
                }
                
                
                if (buttonPressed==modifyBotton)
                {   int row=Table.getSelectedRow();
                System.out.println(row);
            	
                   String SelectedName=(String)model.getValueAt(row, 0);
                   System.out.println(SelectedName);
               	
                	String name=nameField.getText();
                	String nik=nikField.getText();
                	String pass=passField.getText();
                	try {
								dao.connect();
							if (name!="")
		                		dao.changeUserName(SelectedName,name);
		                	if (nik!="")
		                		{System.out.println("ha entrado");
		                	
		                		dao.changeUserNick(SelectedName,nik);}
		                	if (pass!="")
		                		dao.changeUserPassWord(SelectedName, pass);
		                	statusBar.setText("Correctly change");
		               dao.disconnect(); 
                	} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                	loadTable();
                }
                
                
                if (buttonPressed==deleteButton)
                {
                	 int row=Table.getSelectedRow();
                     String SelectedName=(String)model.getValueAt(row, 0);
                     System.out.println(SelectedName);
                     try {
							dao.connect();
							dao.deleteUser(SelectedName);
							dao.disconnect();
                     } catch (SQLException e1) {
 						// TODO Auto-generated catch block
 						e1.printStackTrace();
 					}
                     loadTable();
				}
                if (buttonPressed==ExitButton)
                { System.exit(0);}
                
                if (buttonPressed==disconectButton)
                {
                	int numUser= conectedUsers.getSelectedIndex();
                	List<Server> userList =null;
            		userList=controller.getUserList();
            		controller.removeServerThread(userList.get(numUser).getId());
            	}
                
                
                if (buttonPressed==newButton)
                {
                	String name=nameField.getText();
                	System.out.println(name);
                	String nik=nikField.getText();
                	System.out.println(nik);
                	
                	String pass=passField.getText();
                	System.out.println(pass);
                	
                	try {
						dao.connect();
						dao.insertUser(name, nik, pass);
	                	dao.disconnect();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					loadTable();
                 }
                if (buttonPressed==disconectButton)
                {	int num=conectedUsers.getSelectedIndex();
                	String line=conectedUsers.getSelectedItem().toString();
                	conectedUsers.remove(num);
                	StringTokenizer sTok= new StringTokenizer(line,";");
    				sTok.nextToken();String item=sTok.nextToken();
    				System.out.println(Long.valueOf(item));
    				controller.removeServerThread(Long.valueOf(item));
                }
                	
                	
                	}
        else if (e.getSource() instanceof JComboBox )
		 {}
            
		
	}



	public void Change( List<String >list) {
		conectedUsers.removeAllItems();
		List<Server> userList =null;
		userList=controller.getUserList();
		String number=String.valueOf(userList.size());
		clientNumberField.setText(number);
		for (int i=0; i<list.size(); i++){
	   				String item=list.get(i);
	   				conectedUsers.addItem(item);
	   				}
		
		
	}



	

  




	}
	


