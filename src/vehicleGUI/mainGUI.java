package vehicleGUI;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.security.AccessControlContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.jface.viewers.TableViewer;

public class mainGUI {

	protected Shell shlMainGUI;
	private Text text_1;
	private Text text_2;
	private Text txtLocalhost;
	private Text text_port;
	private Text text_user;
	private Text txtTest;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());

	protected MysqlAcces acces = new MysqlAcces();
	private Text textLog;
	
	protected Operation operation;
	protected DatabaseTable[] tables = new DatabaseTable[3];
	protected int id; // id de la table courante
	private Text text_3;
	private Table table;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			mainGUI window = new mainGUI();
			window.open();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlMainGUI.open();
		shlMainGUI.layout();
		while (!shlMainGUI.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlMainGUI = new Shell();
		shlMainGUI.setSize(534, 316);
		shlMainGUI.setText("Projet de test d'une application de gestion de concessionnaire");
		
		operation = Operation.CREATION; // l'opération par défaut est la création
		
		id = 0;
		
		tables[0] = new DatabaseTable("Marque"); // MARQUE
		tables[0].setFieldValue(0, "Nom de la marque");
		tables[0].setFieldValue(1, "Pays d'origine");
		
		tables[1] = new DatabaseTable("Modele"); // MODELE
		tables[1].setFieldValue(0, "Nom de la marque"); // l'utilisateur aura ici le choix de choisir à partir d'une liste
		tables[1].setFieldValue(1, "Nom du modéle");
		
		tables[2] = new DatabaseTable("Vehicule"); // VEHICULE
		tables[2].setFieldValue(0, "Nom de la marque");		// l'utilisateur aura ici le choix de choisir à partir d'une liste
		tables[2].setFieldValue(1, "Nom du modele"); 		// l'utilisateur aura ici le choix de choisir à partir d'une liste
		tables[2].setFieldValue(2, "Prix du vehicule"); 	// l'utilisateur aura ici le choix de choisir à partir d'une liste
		
		Button btnApplyAction = new Button(shlMainGUI, SWT.NONE);
		
		btnApplyAction.setBounds(259, 222, 75, 25);
		btnApplyAction.setText("Cr\u00E9er");
		
		text_1 = new Text(shlMainGUI, SWT.BORDER);
		text_1.setBounds(121, 87, 135, 21);
		
		Label field_1 = new Label(shlMainGUI, SWT.NONE);
		field_1.setBounds(8, 90, 107, 15);
		field_1.setText("Nom de la marque");
		
		text_2 = new Text(shlMainGUI, SWT.BORDER);
		text_2.setBounds(121, 114, 135, 21);
		
		Label field_2 = new Label(shlMainGUI, SWT.NONE);
		field_2.setText("Pays d'origine");
		field_2.setBounds(8, 117, 107, 15);
		
		Label lblLogo = new Label(shlMainGUI, SWT.NONE);
		lblLogo.setBounds(282, 147, 55, 15);
		lblLogo.setText("Image");
		
		Menu menu = new Menu(shlMainGUI, SWT.BAR);
		shlMainGUI.setMenuBar(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.setText("?");
		
		txtLocalhost = new Text(shlMainGUI, SWT.BORDER);
		txtLocalhost.setText("localhost");
		txtLocalhost.setBounds(121, 0, 135, 21);
		
		Label lblIpDuServeur = new Label(shlMainGUI, SWT.NONE);
		lblIpDuServeur.setText("H\u00F4te du seveur BDD");
		lblIpDuServeur.setBounds(8, 3, 107, 15);
		
		text_port = new Text(shlMainGUI, SWT.BORDER);
		text_port.setText("3306");
		text_port.setBounds(338, 0, 60, 21);
		
		Label lblPort = new Label(shlMainGUI, SWT.NONE);
		lblPort.setText("Port");
		lblPort.setBounds(259, 3, 78, 15);
		
		text_user = new Text(shlMainGUI, SWT.BORDER);
		text_user.setText("root");
		text_user.setBounds(121, 27, 135, 21);
		
		Label lblNomDutilisateur = new Label(shlMainGUI, SWT.NONE);
		lblNomDutilisateur.setText("Nom d'utilisateur");
		lblNomDutilisateur.setBounds(8, 30, 107, 15);
		
		Label lblMotDePasse = new Label(shlMainGUI, SWT.NONE);
		lblMotDePasse.setText("Mot de passe");
		lblMotDePasse.setBounds(262, 30, 75, 15);
		
		txtTest = new Text(shlMainGUI, SWT.BORDER | SWT.PASSWORD);
		txtTest.setText("test");
		txtTest.setBounds(338, 27, 141, 21);
		
		Button btnParcourir = formToolkit.createButton(shlMainGUI, "Parcourir", SWT.NONE);
		btnParcourir.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnParcourir.setBounds(262, 164, 75, 25);
		
		Label lblEtatDeconnect = new Label(shlMainGUI, SWT.NONE);
		lblEtatDeconnect.setText("Etat : deconnect\u00E9");
		lblEtatDeconnect.setBounds(131, 54, 107, 15);
		formToolkit.adapt(lblEtatDeconnect, true, true);
		
		Button btnConnexion = new Button(shlMainGUI, SWT.NONE);
		btnConnexion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				if(acces.connection == null)
				{
					acces.configure(txtLocalhost.getText(), text_port.getText(), text_user.getText(), txtTest.getText());
					
					acces.connect();
					
					btnConnexion.setText("Deconnexion");
					
					if(acces.connection != null) lblEtatDeconnect.setText("Etat : connect\u00E9");
				}
				
				else
				{
					try {
						if(!acces.connection.isClosed())
						{
							acces.connection.close();
							
							lblEtatDeconnect.setText("Etat : deconnect\u00E9");
							
							btnConnexion.setText("Connexion");
							
							acces.connection = null;
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnConnexion.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnConnexion.setText("Connexion");
		btnConnexion.setBounds(404, 0, 75, 25);
		formToolkit.adapt(btnConnexion, true, true);
		
		Label label = new Label(shlMainGUI, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(8, 75, 500, 2);
		formToolkit.adapt(label, true, true);
		
		List listTable = new List(shlMainGUI, SWT.BORDER);
		listTable.setItems(new String[] {"Marque", "Modele", "Vehicule"});
		listTable.setBounds(352, 114, 75, 68);
		formToolkit.adapt(listTable, true, true);
		
		Button btnRechargerTable = new Button(shlMainGUI, SWT.NONE);
		
		btnRechargerTable.setText("Recharger");
		btnRechargerTable.setBounds(352, 184, 75, 25);
		formToolkit.adapt(btnRechargerTable, true, true);
		
		List listOperation = new List(shlMainGUI, SWT.BORDER);
		listOperation.setItems(new String[] {"Creation", "Recherche", "Liste"});
		listOperation.setBounds(433, 114, 75, 68);
		formToolkit.adapt(listOperation, true, true);
		
		Button btnRechargerOperation = new Button(shlMainGUI, SWT.NONE);
		
		btnRechargerOperation.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
			}
		});
		btnRechargerOperation.setText("Recharger");
		btnRechargerOperation.setBounds(433, 184, 75, 25);
		formToolkit.adapt(btnRechargerOperation, true, true);
		
		Label lblActions = new Label(shlMainGUI, SWT.NONE);
		lblActions.setText("Actions");
		lblActions.setBounds(414, 90, 47, 18);
		formToolkit.adapt(lblActions, true, true);
		
		Label label_1 = new Label(shlMainGUI, SWT.SEPARATOR | SWT.VERTICAL);
		label_1.setBounds(344, 75, 2, 174);
		formToolkit.adapt(label_1, true, true);
		
		textLog = new Text(shlMainGUI, SWT.BORDER);
		textLog.setToolTipText("Les logs sont affich\u00E9s ici");
		textLog.setEnabled(false);
		textLog.setBounds(10, 224, 246, 21);
		formToolkit.adapt(textLog, true, true);
		
		textLog.setText("LOGS");
		
		text_3 = new Text(shlMainGUI, SWT.BORDER);
		text_3.setBounds(121, 141, 135, 21);
		formToolkit.adapt(text_3, true, true);
		if(operation == Operation.CREATION || operation == Operation.SEARCHING) text_3.setVisible(false);
		
		Label field_3 = new Label(shlMainGUI, SWT.NONE);
		field_3.setBounds(10, 147, 105, 15);
		formToolkit.adapt(field_3, true, true);
		field_3.setText("New Label");
		if(operation == Operation.CREATION || operation == Operation.SEARCHING) field_3.setVisible(false);
		
		Label labelSelection = new Label(shlMainGUI, SWT.NONE);
		labelSelection.setBounds(365, 227, 143, 15);
		formToolkit.adapt(labelSelection, true, true);
		labelSelection.setText("Selectionne : " + tables[id].name);
		
		Combo combo_1 = new Combo(shlMainGUI, SWT.NONE);
		
		combo_1.setBounds(121, 85, 135, 23);
		formToolkit.adapt(combo_1);
		formToolkit.paintBordersFor(combo_1);
		combo_1.setVisible(false);
		
		Combo combo_2 = new Combo(shlMainGUI, SWT.NONE);
		combo_2.setBounds(121, 114, 135, 23);
		formToolkit.adapt(combo_2);
		formToolkit.paintBordersFor(combo_2);
		combo_2.setVisible(false);
		
		table = new Table(shlMainGUI, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(8, 87, 326, 160);
		formToolkit.adapt(table);
		formToolkit.paintBordersFor(table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		
	    table.setVisible(false);
	    
		combo_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				combo_2.removeAll();
				
				Statement statement;
				
				try {
					statement = acces.connection.createStatement();
					
					ResultSet resultat = statement.executeQuery( "SELECT id, modele_name FROM Modele;" );

					/* Récupération des données du résultat de la requête de lecture */
					while ( resultat.next() ) {
						int idMarque = resultat.getInt( "id" );
					    String nameModele = resultat.getString( "modele_name" );

					    // On ne laisserait à l'utilsateur voir que les modeles liés à la marque selectionnée
					    if(idMarque == combo_1.getSelectionIndex() + 1) combo_2.add(nameModele); 
					   
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
				
			}
		});
		
		btnRechargerOperation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if(acces.connection == null) 
				{
					textLog.setText("Vous n'etes pas connecte");
					return;
				}
				
				id = listOperation.getSelectionIndex();
				
				if(id == 0)
				{
					btnApplyAction.setText("Creer");
					
					operation = operation.CREATION;
					
					field_1.setVisible(true);
					field_2.setVisible(true);
					//field_3.setVisible(true);
					
					text_1.setVisible(true);
					text_2.setVisible(true);
					//text_3.setVisible(false);
					
					combo_1.setVisible(false);
					combo_2.setVisible(false);
					
					btnApplyAction.setVisible(true);
					btnParcourir.setVisible(true);
					
					textLog.setVisible(true);
					
					lblLogo.setVisible(true);
					
					table.setVisible(false);
				}
				
				else if(id == 1)
				{
					btnApplyAction.setText("Rechercher");
					
					operation = operation.SEARCHING;
					
					field_1.setVisible(false);
					field_2.setVisible(false);
					field_3.setVisible(false);
					
					text_1.setVisible(false);
					text_2.setVisible(false);
					text_3.setVisible(false);
					
					combo_1.setVisible(false);
					combo_2.setVisible(false);
					
					btnApplyAction.setVisible(false);
					btnParcourir.setVisible(false);
					
					textLog.setVisible(false);
					
					lblLogo.setVisible(false);
					
					table.setVisible(false);
				}
				
				
				else if(id == 2)
				{
					btnApplyAction.setText("Modifier");
					
					operation = operation.MODIFICATION;
					
					field_1.setVisible(false);
					field_2.setVisible(false);
					field_3.setVisible(false);
					
					text_1.setVisible(false);
					text_2.setVisible(false);
					text_3.setVisible(false);
					
					combo_1.setVisible(false);
					combo_2.setVisible(false);
					
					btnApplyAction.setVisible(false);
					btnParcourir.setVisible(false);
					
					textLog.setVisible(false);
					
					lblLogo.setVisible(false);
					
					table.removeAll();
					 
					String[] titles = { "ID", "Marque", "Modele", "Prix", "Etat" };

				    for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
				      TableColumn column = new TableColumn(table, SWT.NULL);
				      column.setText(titles[loopIndex]);
				    }
				    
				    int statut;
					
					Statement statement;
					try {
						statement = acces.connection.createStatement();
						
						int marque_id = combo_1.getSelectionIndex();
						int modele_id = -1;
						
						ResultSet resultat = statement.executeQuery
						( "SELECT DISTINCT Vehicule.vehicule_id, Vehicule.prix, Modele.modele_id, Modele.id, Marque.name, Modele.modele_name FROM Vehicule, Modele, Marque  WHERE Vehicule.modele_id=Modele.modele_id AND Modele.id=Marque.id;");
					
						/* Récupération des données du résultat de la requête de lecture */
						while ( resultat.next() ) {
							int idVehicle 		= resultat.getInt( "Vehicule.vehicule_id" 			);
							int prixVehicle 		= resultat.getInt( "Vehicule.prix" 			);
							String nameMarque 	= resultat.getString( "Marque.name"	);
						    String nameModele 	= resultat.getString( "Modele.modele_name"	);
						    
						    TableItem item = new TableItem(table, SWT.NULL);
						      item.setText("ID " + idVehicle);
						      item.setText(0, "ID " + idVehicle);
						      item.setText(1, nameMarque);
						      item.setText(2, nameModele);
						      item.setText(3, "" + prixVehicle);
						      item.setText(4, "En vente");
						      
						    for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
							      table.getColumn(loopIndex).pack();
							    }
						   
						}    
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}					
										   				   
					table.setVisible(true);
					
				}
			}
		});
		
		btnApplyAction.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseDown(MouseEvent e) {

				if(acces.connection == null )
				{
					textLog.setText("Vous n'etes pas connecte");
					return;
				}
				
				if(operation == Operation.CREATION)
				{
					if(id == 0) // MARQUE
					{
						if(text_1.getText().isEmpty()) 
						{
							textLog.setText("Le champ 1 est vide");
							return;
						}
						
						if(text_2.getText().isEmpty()) 
						{
							textLog.setText("Le champ 2 est vide");
							return;
						}
						
						try {
							Statement statement;
							
							int statut;
							
							statement = acces.connection.createStatement();					
							
							statut = statement.executeUpdate( "INSERT INTO " + tables[id].name + " (name, nation) VALUES ('" + text_1.getText() + "', '" + text_2.getText() + "');" );
							
							textLog.setText("Etat de la création : " + statut);
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					else if(id == 1) // MODELE
					{
						if(combo_1.getSelectionIndex() < 0) 
						{
							textLog.setText("Le champ 1 est vide");
							return;
						}
						
						if(text_2.getText().isEmpty()) 
						{
							textLog.setText("Le champ 2 est vide");
							return;
						}
						
						try {
							Statement statement;
							
							int statut;
							
							statement = acces.connection.createStatement();					
							
							int marque_id = combo_1.getSelectionIndex() + 1;
				
							statut = statement.executeUpdate( "INSERT INTO " + tables[id].name + " (id, modele_name) VALUES ('" +  marque_id + "', '" + text_2.getText() + "');" );
							
							textLog.setText("Etat de la création : " + statut);
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					else if(id == 2) // VEHICULE
					{
						if(combo_1.getSelectionIndex() < 0) 
						{
							textLog.setText("Le champ 1 est vide");
							return;
						}
						
						if(combo_2.getSelectionIndex() < 0) 
						{
							textLog.setText("Le champ 2 est vide");
							return;
						}
						
						if(text_3.getText().isEmpty()) 
						{
							textLog.setText("Le champ 3 est vide");
							return;
						}
						
						try {
							Statement statement;
							
							int statut;
							
							statement = acces.connection.createStatement();					
							
							int marque_id = combo_1.getSelectionIndex() + 1;
							int modele_id = -1;
							
							ResultSet resultat = statement.executeQuery( "SELECT id, modele_id, modele_name FROM Modele;" );
							
							/* Récupération des données du résultat de la requête de lecture */
							while ( resultat.next() ) {
								int idMarque 		= resultat.getInt( "id" 			);
								int idModele 		= resultat.getInt( "modele_id" 		); 
							    String nameModele 	= resultat.getString( "modele_name"	);

							    // Ceci va permettre de recuperer l'id absolue du modele
							    if(		nameModele.compareTo( combo_2.getItem( combo_2.getSelectionIndex() )) == 0 &&
							    		idMarque == combo_1.getSelectionIndex() + 1) 
							    {
							    	modele_id = idModele;
							    	break;
							    }
							}
							
							statut = statement.executeUpdate( "INSERT INTO " + tables[id].name + " (modele_id, prix) VALUES ('" +  modele_id + "', '" + Integer.parseInt(text_3.getText()) + "');" );
							
							textLog.setText("Etat de la création : " + statut + " (vehicule)");
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				
			}
		});
		
		btnRechargerTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				if(acces.connection == null) 
				{
					textLog.setText("Vous n'etes pas connecte");
					return;
				}
				
				id = listTable.getSelectionIndex();
				
				if(operation == Operation.MODIFICATION) return;
				
				if(id == 0)
				{
					field_1.setText( tables[id].fieldsNames[0] );
					field_2.setText( tables[id].fieldsNames[1] );
					
					field_3.setVisible(false);
					
					combo_1.setVisible(false);
					combo_2.setVisible(false);
					
					combo_1.removeAll();
					combo_2.removeAll();
					
					text_1.setText("");
					text_2.setText("");
					text_3.setText("");
					
					text_1.setVisible(true);
					text_2.setVisible(true);
					text_3.setVisible(false);
					
				}
				
				else if(id == 1)
				{
					field_1.setText( tables[id].fieldsNames[0] );
					field_2.setText( tables[id].fieldsNames[1] );
					
					field_3.setVisible(false);
					
					combo_1.setVisible(true);
					combo_2.setVisible(false);
					
					combo_1.removeAll();
					combo_2.removeAll();
					
					Statement statement;
					
					try {
						statement = acces.connection.createStatement();
						
						/* Exécution d'une requête de lecture */
						ResultSet resultat = statement.executeQuery( "SELECT name FROM Marque;" );

						/* Récupération des données du résultat de la requête de lecture */
						while ( resultat.next() ) {
						    //int idModele = resultat.getInt( "id" );
						    String nameModele = resultat.getString( "name" );

						    combo_1.add(nameModele);
						    /* Traiter ici les valeurs récupérées. */
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}		
					
					text_1.setVisible(false);
					text_2.setVisible(true);
					text_3.setVisible(false);
				}
				
				else if(id == 2)
				{
					field_1.setText( tables[id].fieldsNames[0] );
					field_2.setText( tables[id].fieldsNames[1] );
					field_3.setText( tables[id].fieldsNames[2] );
					
					field_3.setVisible(true);
					
					combo_1.setVisible(true);
					combo_2.setVisible(true);
					
					combo_1.removeAll();
					combo_2.removeAll();
					
					Statement statement;
					
					try {
						statement = acces.connection.createStatement();
						
						/* Exécution d'une requête de lecture */
						ResultSet resultat = statement.executeQuery( "SELECT name FROM Marque;" );

						/* Récupération des données du résultat de la requête de lecture */
						while ( resultat.next() ) {
						    //int idMarque = resultat.getInt( "id" );
						    String nameMarque = resultat.getString( "name" );

						    combo_1.add(nameMarque);
						    /* Traiter ici les valeurs récupérées. */
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}		
					
					text_1.setVisible(false);
					text_2.setVisible(false);
					text_3.setVisible(true);
				}
				
				labelSelection.setText("Selection : " + tables[id].name);
			}
		});
	}
}
