package test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;  
import javax.swing.JFrame;  
import javax.swing.*;
 
public class GUI extends JFrame {
	
	/**
	 * 
	 */
	private CM cm = new CM();
	private static final long serialVersionUID = 1L;
	private JTextArea jta = new JTextArea("[汉字]");
			JTextArea jta2 = new JTextArea("[拼音]");
			JButton okButton = new JButton("转化");
			//boolean read = false;
		
	class ButtonListener implements ActionListener {  
		@Override  
		public void actionPerformed(ActionEvent arg0) {  
			//System.out.println(((JButton) arg0.getSource()).getText());  
			String hanzi = jta.getText(); 
			//CM cm = new CM();
			
			
			String pinyin = "";
			try {
				pinyin = cm.check(hanzi);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			jta2.setText(pinyin);  
		        }  
		    } 
	
	private ButtonListener bl = new ButtonListener();  	
	
	public GUI() throws IOException {
	    super("汉字拼音转化小助手");
	    setSize(700,500);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    
		cm.ReadFile();
	  
	    //文本框
	    //JTextArea jta = new JTextArea("[汉字]");
	    jta.setLineWrap(true);
	    jta.setWrapStyleWord(true);
	    JScrollPane sp1 = new JScrollPane(jta);
	    sp1.setMinimumSize(new Dimension(200,200));
	    sp1.setPreferredSize(new Dimension(200,200));
	    
	    //JTextArea jta2 = new JTextArea("[拼音]\n");
	    jta2.setLineWrap(true);
	    jta2.setWrapStyleWord(true);
	    
	    JScrollPane sp2 = new JScrollPane(jta2);
	    sp2.setMinimumSize(new Dimension(200,200));
	    sp2.setPreferredSize(new Dimension(200,200));
	    
	    JPanel rightPane = new JPanel(new BorderLayout());
	    rightPane.add(new JScrollPane(jta2), BorderLayout.CENTER);
	 
	    JSplitPane jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, sp1, rightPane);
	 
	    getContentPane().add(jsp, BorderLayout.CENTER);
	 
	    JPanel bPane = new JPanel();
	    //JButton okButton = new JButton("转化");
	    okButton.addActionListener(bl);
	    bPane.add(okButton);
	 
	    getContentPane().add(bPane, BorderLayout.SOUTH);
	   
	    setVisible(true);
	    //bPane.repaint();
	  }
 
	public static void main(String args[]) throws IOException {
		new GUI();
	}
}
