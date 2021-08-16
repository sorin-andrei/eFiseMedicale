package eFiseMedicale;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class InterfataCereri extends JFrame {
	private JTextField doctorSpecialist;
	private JComboBox c; //tipulAnalizei
	private JButton trimiteCererea;
	private JList l;
	private JScrollPane jp;

	public void genereazaCerere(String s , String infP) throws IOException
    {
    	String adresa = "C:\\ProiectPOO\\Cereri\\";
    	String numeFisier = adresa + infP + "Cerere.txt";
    	
    	String continut =s+"\r";
    	File file = new File(numeFisier);
    	FileWriter fr = new FileWriter(file,false);
    	fr.write(continut);
    	
		System.out.println("S-a printat cererea avand urmatorul continut: \n"+s);
    	fr.close();
     }

	public InterfataCereri() throws Exception
	{
		super("Generator Cerere");
		
		JPanel p1 = new JPanel();
		p1.add(new JLabel("Inregistreaza cerere") );
		
		ArrayList<String> numePacienti = new ArrayList<String>();
		ColectiePacienti p = new ColectiePacienti(); 
		for(Pacient i : p.getLista()) 
		{
			String nume = i.getNume();
			String prenume = i.getPrenume();
			numePacienti.add(nume + " " + prenume);
		}
		
		l = new JList<>(numePacienti.toArray());
		l.setVisibleRowCount(6);
		jp = new JScrollPane(l);
		p1.add(jp);
	    
		
		this.add(p1 , BorderLayout.NORTH);
		
		JPanel p2 = new JPanel( new GridLayout (6,6,10,10)  );
		int dimensiune = 6;
		doctorSpecialist = new JTextField(dimensiune);
		p2.add(new JLabel("Doctorul specialist"));
	    p2.add(doctorSpecialist);
	    
	    p2.add(new JLabel("Tipul analizei"));
		c = new JComboBox();
		c.addItem("ACTH (Hormon adrenocorticotrop)");
		c.addItem("ADH (Vasopresina)");
		c.addItem("Insulina");
		c.addItem("Progesteron");
		c.addItem("SPN (Spaghettisteron)");
		c.addItem("Testare Aldostreon (Seric / Urinar)");
		c.addItem("Testare Cortisol (Seric / Salivar / Urinar)");
		c.addItem("Determinare HGH Seriata Dupa Glucoza");
		c.addItem("DHEA Dehidro-Epiandrosteron in Urina");
		c.addItem("VSH (Folicolostimulant)");
		c.addItem("TSH Tiroidostimulant");
		p2.add(c);
		
		this.add(p2,BorderLayout.CENTER);
		
		 JPanel p3 = new JPanel();
		    
		    trimiteCererea = new JButton("Trimite cererea");
		    p3.add(trimiteCererea);
		    AscultatorButoane ab = new AscultatorButoane();
		    trimiteCererea.addActionListener(ab);
		 this.add(p3 , BorderLayout.SOUTH);
		    		
	}
	
	private class AscultatorButoane implements ActionListener 
	{
		
		public void actionPerformed(ActionEvent e)
		{
			 String nP,pP,dS,tA;
		        switch( e.getActionCommand() )
				{
				case("Trimite cererea"): try {
					dS = doctorSpecialist.getText();
					tA = c.getSelectedItem().toString();
				    nP  = l.getSelectedValue().toString();
					String continutFisier =nP+ ", pacient al doctorului "+dS+"\nare nevoie de analize de tip "+tA;
					
					genereazaCerere(continutFisier,nP);
					JOptionPane.showMessageDialog(null, "Cererea a fost trimisa cu succes!");
				     
					
					} catch (Exception e1) {
		 break; }
		}
		
	}
	
	
	

}
	
}
