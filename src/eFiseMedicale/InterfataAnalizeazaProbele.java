package eFiseMedicale;
import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;


public class InterfataAnalizeazaProbele extends JFrame{
	private JList l;
	private JScrollPane jp;
	private JRadioButton poz,neg;
	private ButtonGroup grup;
	private JButton b1;
	
	public InterfataAnalizeazaProbele()throws Exception{
	
		super("eFiseMedicale:-AnalizaProbe");
		
		JPanel p1 = new JPanel();
		Label label = new Label("Selectati pacientul");
		p1.add(label);
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
		this.add(p1,BorderLayout.NORTH);
		
		JPanel p2 = new JPanel();
		poz = new JRadioButton("Pozitiv",true);
	    neg = new JRadioButton("Negativ",false);
        JLabel tip = new JLabel("");
	    p2.add(tip);
	    //Sa se afiseze tipul analizei atunci cand apesi pe un element din lista
	  		l.addListSelectionListener(
	  			new ListSelectionListener()
	  			{
	  			@Override	
	  			public void valueChanged(ListSelectionEvent event)
	  			{
	  				String numePrenume = l.getSelectedValue().toString();
	  				try {
	  				String tipAnaliza = extrageTipulAnalizei(numePrenume);
	  				System.out.println(tipAnaliza);
	  				tip.setText(tipAnaliza);
	  				
	  				
	  				}catch(Exception e1) { tip.setText("Nu s-a gasit cererea :("); };
	  				
	  				
	  			}	
	  			}
	  		);
	  	/////////////////////////////////////////////////////////////////////////
	    p2.add(poz);
	    p2.add(neg);
		grup = new ButtonGroup();
	    grup.add(poz);
	    grup.add(neg);
		this.add(p2,BorderLayout.CENTER);
		
		Panel p3 = new Panel();
		p3.add(new Label(""));
	    b1=new JButton("Salveaza in buletinul de rezultate");
	    p3.add(b1);
	    this.add(p3,BorderLayout.SOUTH);
	    
	    AscultatorButoane ab = new AscultatorButoane();
	    b1.addActionListener(ab);
	}
	
	public String extrageTipulAnalizei(String numePrenume) throws Exception {
		 String fisier = "C:\\ProiectPOO\\Cereri\\"+numePrenume+"Cerere.txt";	
		    File f = new File(fisier);
			BufferedReader br = new BufferedReader(new FileReader(fisier));
			if( f.exists() == false )
			{
				br.close();
				return null;
			}
			String primaLinie;
			while((primaLinie = br.readLine()) != "S-a") {
			primaLinie = br.readLine();
			String[] imparte = primaLinie.split("tip ");
			String tA = imparte[1];

			return "Controlul asupra "+ tA +" are rezultat" ;
			}
		   
	    return ":";
	}
	
	public void genereazaFisier(String numeFisier,String continut,boolean append) throws IOException
	{
		File file = new File(numeFisier);
		FileWriter fr = new FileWriter(file,append);
		fr.write(continut);
		fr.close();
	}
	
	
	
	private class AscultatorButoane implements ActionListener{
		public void actionPerformed(ActionEvent e) 
		{
		try {
		
		String numePrenume = l.getSelectedValue().toString();
		File f = new File("C:\\ProiectPOO\\BuletineRezultate\\"+numePrenume+"BR.txt");
		if (f.exists()==false)
		{
			JOptionPane.showMessageDialog(null, "Buletinul nu exista!\nIntocmiti buletinul de rezultate inainte sa continuati!", "Eroare - eFiseMedicale", JOptionPane.ERROR_MESSAGE);
		}
		else {
		String rezultat;
		if(poz.isSelected()==true)
			rezultat = "Pozitiv";
		else rezultat = "Negativ";
		
		
		String tipulAnalizei = extrageTipulAnalizei(numePrenume);
		String continutFisier = "\n"+tipulAnalizei+" "+rezultat;
		
		String fisier = "C:\\ProiectPOO\\BuletineRezultate\\"+numePrenume+"BR.txt";
		genereazaFisier(fisier,continutFisier,true);
		JOptionPane.showMessageDialog(null, "Rezultatul a fost adaugat la buletinul de rezultate al pacientului "+numePrenume);
		}
		}catch(Exception e1) { JOptionPane.showMessageDialog(null, "Este nevoie de cerere!", "Eroare - eFiseMedicale", JOptionPane.ERROR_MESSAGE);}
		}
	}
	
}
