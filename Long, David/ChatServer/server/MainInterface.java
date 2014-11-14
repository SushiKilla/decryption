package server;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.PrintWriter;
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


public class MainInterface extends JFrame {

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

	/**
	 * Creates and runs the frame
	 */
	public MainInterface(ArrayList names, final PrintWriter out) {
		USERNAME = (String) names.get(0);
		
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
		
		JList list = new JList(names.toArray());
		list.setBounds(668, 421, -163, -400);
	    list.setSelectedIndex(1);
		contentPane.add(list);
		
		final JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(10, 10, 472, 393);
		textArea.setText(null);
		contentPane.add(textArea);
		
		final JTextField textField = new JTextField();
		textField.setBounds(10, 409, 400, 22);
		textField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent key) {
					if(key.getKeyCode() == 10) {
						printText(textField, textArea);
						messageToServer(MESSAGE, USERNAME, out);
					}
				}
			});
		contentPane.add(textField);
		
		JButton btnSend = new JButton("Send");
		btnSend.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnSend.setBounds(419, 408, 62, 23);
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent mouse) {
				printText(textField, textArea);
				messageToServer(MESSAGE, USERNAME, out);
				}
			});
		contentPane.add(btnSend);	
	}
	
	public void messageToServer(String message, String user, PrintWriter out){
		System.out.println(message);
		out.print(user + ": " + message + "\n");
		out.println();
		out.close();
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
