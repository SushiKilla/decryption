package server;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;


public class GUI extends JFrame {

	/**
	 * Ignore this
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The message that user wishes to send
	 */
	private String MESSAGE;
	
	/**
	 * The username of the person sending a message
	 */
	private String USERNAME;
	
	/**
	 * The pane that stores all of the widgets
	 */
	private JPanel contentPane;
	
	private ClientThread client;
	
	private JList<String> userList;
	private JTextArea messagesDisplayArea;
	private JTextField composeField;
	private JButton sendBtn;

	/**
	 * Creates and runs the frame
	 */
	public GUI(ClientThread client) {
		this.client = client;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 480);
		setResizable(false);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {e.printStackTrace();}
		catch (InstantiationException e) {e.printStackTrace();}
		catch (IllegalAccessException e) {e.printStackTrace();}
		catch (UnsupportedLookAndFeelException e) {e.printStackTrace();}
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		userList = new JList<>();
		userList.setBounds(668, 421, -163, -400);
		userList.setSelectedIndex(1);
		contentPane.add(userList);
		
		messagesDisplayArea = new JTextArea();
		messagesDisplayArea.setEditable(false);
		messagesDisplayArea.setBounds(10, 10, 472, 393);
		messagesDisplayArea.setText(null);
		contentPane.add(messagesDisplayArea);
		
		composeField = new JTextField();
		composeField.setBounds(10, 409, 400, 22);
		composeField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent key) {if(key.getKeyCode() == 10) printText(composeField, messagesDisplayArea); }});
		contentPane.add(composeField);
		
		sendBtn = new JButton("Send");
		sendBtn.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		sendBtn.setBounds(419, 408, 62, 23);
		sendBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent mouse) {printText(composeField, messagesDisplayArea); }});
		contentPane.add(sendBtn);
	}
	
	/**
	 * Prints the text in the user's window
	 * @param textField The TextField to get the message from
	 * @param textArea The TextArea to print the message on
	 */
	public void printText(JTextField textField, JTextArea textArea) {
		MESSAGE = textField.getText();
		textArea.setText(textArea.getText() + USERNAME + ": " + MESSAGE + "\n");
		textField.setText(null);
	}
}
