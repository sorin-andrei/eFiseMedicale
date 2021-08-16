package eFiseMedicale;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class InterfataChimist extends JFrame {
	private JButton b1,b2;
	private JTextField numeP, prenumeP, dN, CNP, varsta, sex, adresa, dataRec, doctorS;
	private JList l;


	public void genereazaBuletinRezultate(String s , String infP) throws IOException {
		
		String adresa = "C:\\ProiectPOO\\BuletineRezultate\\";
    	String numeFisier = adresa + infP + "BR.txt";
    	
    	String continut =s+"\r";
    	File file = new File(numeFisier);
    	FileWriter fr = new FileWriter(file,true);
    	fr.write(continut);
    	
		System.out.println("S-a printat cererea avand urmatorul continut: \n"+s);
    		
    	fr.close();
    	
	}
	
	
	public InterfataChimist()
	{
		super("eFiseMedicale");
		JPanel p1 = new JPanel();
		p1.add(new JLabel("Bine ati venit!"));
		b1 = new JButton("Genereaza buletin de rezultate");
		p1.add(b1);
		this.add(p1,BorderLayout.NORTH);
		
		JPanel p2 = new JPanel();
		b2 = new JButton("Analizeaza probele");
		p2.add(b2);
		this.add(p2,BorderLayout.CENTER);
		
		AscultatorButoane ab = new AscultatorButoane();
	    b1.addActionListener(ab);
	    b2.addActionListener(ab);
	}		
	
	
   
	private class AscultatorButoane implements ActionListener{
	public void actionPerformed(ActionEvent e) 
	{	


		switch( e.getActionCommand() )
		{
			case("Analizeaza probele"):  {
				InterfataAnalizeazaProbele f2;
				try {
					f2 = new InterfataAnalizeazaProbele();
					
					f2.setSize(500,300);
					f2.setVisible(true);
					
					f2.addWindowListener(  new WindowAdapter()  {  
						
						public void windowClosing(WindowEvent e)
						{ System.exit(0);}}
				    );
					}catch (Exception e2) {
						
						e2.printStackTrace();
					}
			}break;
			case("Genereaza buletin de rezultate"):{
				InterfataBuletinRezultate f1;
			try {
				f1 = new InterfataBuletinRezultate();
			
			f1.setSize(600,400);
			f1.setVisible(true);
			
			f1.addWindowListener(  new WindowAdapter()  {  
				
				public void windowClosing(WindowEvent e)
				{ System.exit(0);}}
		    );
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			}break;
		}
			
	}
		

		
	}	
}