package eFiseMedicale;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.*;


public class InterfataFisaMedicala extends JFrame{
	
	private JTextField nume,prenume,dataNasterii,CNP,varsta,sex,adresa;
	private JButton Save,Exit;
	private JRadioButton m,f;
	private ButtonGroup grup;
	
	public void genereazaFisier(String numeFisier,String continut,boolean append) throws IOException
	{
		File file = new File(numeFisier);
		FileWriter fr = new FileWriter(file,append);
		fr.write(continut);
		fr.close();
	}
	
	public InterfataFisaMedicala()
	{
		super("Generator Fisa Medicala");
		
        JPanel p1 = new JPanel();
		
        p1.add(new JLabel("Informatii pacient") );
		this.add(p1 , BorderLayout.NORTH);
		
		JPanel p2 = new JPanel(new GridLayout (10,10,3,3) );
		
		int dimensiune = 7;
		nume = new JTextField(dimensiune);
		p2.add(new JLabel("Nume"));
	    p2.add(nume);
	    
	    prenume = new JTextField(dimensiune);
		p2.add(new JLabel("Prenume"));
	    p2.add(prenume);
		
	    dataNasterii = new JTextField(dimensiune);
		p2.add(new JLabel("Data nasterii"));
	    p2.add(dataNasterii);
	
	    CNP = new JTextField(dimensiune);
		p2.add(new JLabel("CNP"));
	    p2.add(CNP);
	    
	    varsta = new JTextField(dimensiune);
		p2.add(new JLabel("Varsta"));
	    p2.add(varsta);
	    
	    p2.add(new JLabel("Sex"));
	    m = new JRadioButton("Masculin",true);
	    f = new JRadioButton("Feminin",false);
	    p2.add(new Label(""));
	    p2.add(m);
	    p2.add(f);
	    
	    grup = new ButtonGroup();
	    grup.add(m);
	    grup.add(f);
		
	    adresa = new JTextField(dimensiune);
		p2.add(new JLabel("Adresa"));
	    p2.add(adresa);
	    this.add(p2,BorderLayout.CENTER);
	    
	    JPanel p3 = new JPanel();
	    
	    Save = new JButton("Save");
	    p3.add(Save);
	    
	    Exit = new JButton("Exit");
	    p3.add(Exit);
	    
	    AscultatorButoane ab = new AscultatorButoane();
	    Save.addActionListener(ab);
	    Exit.addActionListener(ab);
	    
		this.add(p3 , BorderLayout.SOUTH);
		

	}
	
	private class AscultatorButoane implements ActionListener{
	String n,p,dN,C,v,s,a,dR,dS;
	public void actionPerformed(ActionEvent e) 
	{
		String continutFisier;
		
		n = nume.getText();
		p = prenume.getText();
		dN = dataNasterii.getText();
		C = CNP.getText();
		v = varsta.getText();
		a = adresa.getText();
		
		if (m.isSelected() == true )
		     s = "Masculin";
		else s = "Feminin";
		
		continutFisier = "Nume: "+n + "\n"+"Prenume: " + p + "\n"+"Data nasterii: " + dN + "\n" + "CNP: "+ C + "\n" +"Varsta: "+ v + "\n" +"Sex:"+ s + "\n" + "Adresa: "+a  + "\n"
	    +"\n-----------------------------------------------------------\nIstoric medical: \n";
		
		switch( e.getActionCommand() )
		{
		case("Save"): try {
			
			
			//Pentru a aduaga la FiseMedicale
			String numeFisier = n+" "+p+".txt";
			String adresa = "C:\\ProiectPOO\\FiseMedicale\\";
			String fisierFM = adresa+numeFisier;
			genereazaFisier(fisierFM,continutFisier,false);
			
			//Pentru a adauga la Pacienti.txt
			String continutPacientNou = n+":"+p+":"+dN+":"+C+":"+v+":"+s+":"+a+"\n";
			String fisierPacientiTxt = "C:\\ProiectPOO\\Pacienti.txt";
			genereazaFisier(fisierPacientiTxt,continutPacientNou,true);
			
			JOptionPane.showMessageDialog(null, "Fisa medicala a fost realizata cu succes!");
	     
	 
		
		} catch (IOException e1) {
 break; }
		case("Exit"):  ; break;
		
		}
		}
    }
}
