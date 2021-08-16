package eFiseMedicale;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

public class InterfataPacient extends JFrame  {
	
	private JTextField nume,prenume;
	private JButton afisare;
	private JTextArea ta;
	private JScrollPane jp;
	
	
	
	public int afiseazaContinut() throws IOException
	{
		String fisier;
		String n = nume.getText();
		String p = prenume.getText();
		fisier = "C:\\ProiectPOO\\FiseMedicale\\"+n+" "+p+".txt";
		
		File temp = new File(fisier);
		boolean ok = temp.exists();
		
		if(ok == false)
		{
			ta.setText("X_X");
			JOptionPane.showMessageDialog(null, "Nu sunteti inregistrat in sistem!", ":( - eFiseMedicale", JOptionPane.ERROR_MESSAGE);
			return 0;
			
		}
		
		BufferedReader br = new BufferedReader(new FileReader(fisier));
		String line;
		String rez="";
		   while ((line = br.readLine()) != null) {
		       rez=rez+line+"\n";
		   }
		   ta.setText(rez);
		   
		   br.close();
		   
		   return 1;
	}
	
	public InterfataPacient()
	{
		super("Pacient");
		

		JPanel p1 = new JPanel();
		p1.add(new JLabel("Introduceti datele dumneavoastra") );
		this.add(p1 , BorderLayout.NORTH);
		
		JPanel p2 = new JPanel(  );
		int dimensiune = 15;
		nume = new JTextField(dimensiune);
		p2.add(new JLabel("Nume"));
	    p2.add(nume);
	    
	    prenume = new JTextField(dimensiune);
		p2.add(new JLabel("Prenume"));
	    p2.add(prenume);
	    this.add(p2,BorderLayout.CENTER);
	    
	    ta = new JTextArea(20,30);
	    ta.setEditable(false);
	    jp = new JScrollPane(ta);
	    p2.add(jp);
	    
        JPanel p3 = new JPanel();
	    
	    afisare = new JButton("Afiseaza");
	    p3.add(afisare);
	    
	   
	    
	    AscultatorButoane ab = new AscultatorButoane();
	    afisare.addActionListener(ab);
	  
	    
		this.add(p3 , BorderLayout.SOUTH);
	}
	
	private class AscultatorButoane implements ActionListener{
	public void actionPerformed(ActionEvent e) 
	{
		
		String continutFisier;
		String n,p;
		n = nume.getText();
		p = prenume.getText();
		
		switch( e.getActionCommand() )
		{
		case("Afiseaza"):  {
			
			try {
				afiseazaContinut();
		     	} catch (IOException e1) {
		
				e1.printStackTrace();
			}
			
			break;
		} 
		
		}
		
		}
		
	
	
	
	
		
	}
	
	}
	
		
		
		
	
	
	
		
	
	
	
	
	
	


