package client;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame {
	private SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss", Locale.US);

	/**
	 * Ignore this
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The message that user wishes to send
	 */
//	private String MESSAGE;
	
	/**
	 * The username of the person sending a message
	 */
//	private String USERNAME;
	
	/**
	 * The pane that stores all of the widgets
	 */
	private JPanel contentPane;
	
	private ClientThread client;
	
//	private JList<String> userList;
//	private DefaultListModel<String> userListModel;
	
	private JTextArea messagesDisplayArea;
	private JTextField composeField;
	private JButton sendBtn;

	/**
	 * Creates and runs the frame
	 */
	public GUI(ClientThread clientThread) {
		client = clientThread;
		
		promptUsername();
		
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
		
//		userList = new JList<>();
//		userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		userList.setLayoutOrientation(JList.VERTICAL);
//		userList.setBounds(668, 421, -163, -400);
//		userList.setSelectedIndex(1);
//		
//		userListModel = new DefaultListModel<>();
//		userList.setModel(userListModel);
//		
//		contentPane.add(userList);
		
		messagesDisplayArea = new JTextArea();
		messagesDisplayArea.setEditable(false);
		messagesDisplayArea.setBounds(0, 0, 472, 393);
		messagesDisplayArea.setText(null);
//		contentPane.add(messagesDisplayArea);
		
		JScrollPane scroll = new JScrollPane(messagesDisplayArea);
		scroll.add(messagesDisplayArea);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(10, 10, 472, 393);
		contentPane.add(scroll);
		
		composeField = new JTextField();
		composeField.setBounds(10, 409, 400, 22);
		composeField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent key) {
				if(key.getKeyCode() == 10)
					sendMessage();
			}
		});
		contentPane.add(composeField);
		
		sendBtn = new JButton("Send");
		sendBtn.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		sendBtn.setBounds(419, 408, 62, 23);
		sendBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent mouse) {
				sendMessage();
			}
		});
		contentPane.add(sendBtn);
		
		addWindowListener(new WindowAdapter() {
			@Override
		    public void windowClosing(WindowEvent windowEvent) {
				client.disconnect();
		    }
		});
	}
	
	public void promptUsername() {
		String username = JOptionPane.showInputDialog("Pick a username");
		if(username == null)
			username = "";
			
		try {
			client.join(username);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void sendMessage() {
		try {
			client.postMessage(composeField.getText());
			composeField.setText("");
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	// Responses to server commands
	public void newPost(String sender, String message, long timestamp) {
		messagesDisplayArea.append(String.format("%s %s: %s\n", DATE_FORMAT.format(new Date(timestamp)), sender, URLDecoder.decode(message)));
	}
	
//	public void newPM(String sender, String message, long timestamp) {
//		messagesDisplayArea.append(String.format("[%s %s]: %s\n", DATE_FORMAT.format(new Date(timestamp)), sender, URLDecoder.decode(message)));
//	}
//	
//	public void userJoin(String username) {
//		userListModel.addElement(username);
//	}
//	
//	public void userLeave(String username) {
//		userListModel.removeElement(username);
//	}
	
	public void error(String message) {
		if(message.equals("USERNAME_TAKEN")) {
			promptUsername();
		}
	}
	
//	public void setUserList(String[] users) {
//		userListModel.clear();
//		for(String s : users)
//			userListModel.addElement(s);
//	}
	
//	/**
//	 * Prints the text in the user's window
//	 * @param textField The TextField to get the message from
//	 * @param textArea The TextArea to print the message on
//	 */
//	public void printText(JTextField textField, JTextArea textArea) {
//		MESSAGE = textField.getText();
//		textArea.setText(textArea.getText() + USERNAME + ": " + MESSAGE + "\n");
//		textField.setText(null);
//	}
}
