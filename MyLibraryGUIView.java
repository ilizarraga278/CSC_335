//GUI view for library project

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MyLibraryGUIView{
	private Library model;
	
	

	public MyLibraryGUIView() {
		model = new Library();
        setUp();
	}
	
	private void setUp() {
		JFrame jframe = new JFrame("My Library");
		//setting size of frame
        jframe.setSize(600,400);
        jframe.setLayout(new BorderLayout());
        
        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3,2));
        
        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField();
        
        JLabel authorLabel = new JLabel("Author:");
        JTextField authorField = new JTextField();
        
        JButton addBookButton = new JButton("Add Book");
        JButton searchButton = new JButton("Search Books");
        
        inputPanel.add(titleLabel);
        inputPanel.add(titleField);
        
        inputPanel.add(authorLabel);
        inputPanel.add(authorField);
        
        input
        
        
        
        
        //setting up the status label
        statusLabel = new JLabel("",JLabel.CENTER);
        statusLabel.setSize(350,100);
        this.add(statusLabel);
        
        //adding the panel
        panel = new JPanel();
        this.add(panel);
        
        //adding a window listener for closing the app
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
	}
}
