package server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class GUI extends JFrame {
	String MESSAGE, USERNAME;
	
	private ClientThread client;
	
	private JTextArea messagesArea;
	private JList<String> userList;
	private JTextField composeField;
	private JButton sendButton;
	
	JPanel contentPane;
	
	/**
	 * Creates and runs the frame
	 */
	public GUI(final ClientThread client) {
		this.client = client;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 480);
		setResizable(false);
//		setVisible(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		userList = new JList<>();
		userList.setBounds(668, 421, -163, -400);
		contentPane.add(userList);
		
		messagesArea = new JTextArea();
		messagesArea.setEditable(false);
		messagesArea.setBounds(10, 10, 472, 393);
		messagesArea.setText(null);
		contentPane.add(messagesArea);
		
		composeField = new JTextField();
		composeField.setBounds(10, 409, 400, 22);
//		composeField.addKeyListener(new KeyAdapter() {
//			public void keyPressed(KeyEvent key) {
//				if(key.getKeyCode() == 10)
//					printText(composeField, messagesArea); 
//			}

		
		contentPane.add(composeField);
		
		sendButton = new JButton("Send");
		sendButton.setBounds(419, 408, 62, 23);
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent mouse) {
				try{
				client.postMessage(composeField.getText());
				}
				catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		});
		
		contentPane.add(sendButton);
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
	
	public static void main(String[] args) {
		GUI s = new GUI(null);
		s.setVisible(true);
	}
}
