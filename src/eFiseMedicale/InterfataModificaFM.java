package eFiseMedicale;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import java.util.*;

public class InterfataModificaFM extends JFrame{
	
	String globalText;

	private JTextArea continutFM;
	private JButton b1,b2;
	private JList l;
	private JScrollPane jp;

	public InterfataModificaFM() throws Exception{
		super("eFiseMedicale");
		
		//PANEL 1
		JPanel p1=new JPanel();
		
		p1.add(new JLabel("Selectati pacientul") );
		ArrayList<String> numePacienti = new ArrayList<String>();
		ColectiePacienti p = new ColectiePacienti(); //Constructorul face bufferul
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
		
		
		b1=new JButton("Afiseaza");
		p1.add(b1);
		
		
		
		this.add(p1 , BorderLayout.NORTH);
		
		//PANEL 2	
		
		JPanel p2=new JPanel();
		 continutFM = new JTextArea(20,50);
		 p2.add(continutFM);
		 	this.add(p2 , BorderLayout.CENTER);
		 	
		//PANEL 3
		JPanel p3=new JPanel();
			b2=new JButton("Modifica");
			 p3.add(b2);
			 this.add(p3, BorderLayout.SOUTH);
		 
			 AscultatorButoane ab = new AscultatorButoane();
			 b1.addActionListener(ab);
			 b2.addActionListener(ab);
		
	}
	
	public int afiseazaContinut() throws IOException
	{
		String fisier;
		String rez = "";
		String NP = l.getSelectedValue().toString();
		fisier = "C:\\ProiectPOO\\FiseMedicale\\"+NP+".txt";
		
		File temp = new File(fisier);
		boolean ok = temp.exists();
		
		if (ok==false)
		{
		continutFM.setText("X_X");	
		JOptionPane.showMessageDialog(null, "Fisa medicala nu a fost gasita!", ":( - eFiseMedicale", JOptionPane.ERROR_MESSAGE);
		return 0;
		}
		
		BufferedReader br = new BufferedReader(new FileReader(fisier));
		String line;
		
		
		while ((line = br.readLine()) != null) {
		       rez = rez+line+"\n";
		   }
		continutFM.setText(rez);
		globalText = rez;
		   
		   br.close(); 
		   
		   return 1;
	}
	
	public void aplicaModificari() throws IOException
	{
		String fisier;
		String NP = l.getSelectedValue().toString();
		fisier = "C:\\ProiectPOO\\FiseMedicale\\"+NP+".txt";
		
		String continut = continutFM.getText();
		
		File file = new File(fisier);
    	FileWriter fr = new FileWriter(file,false);
    	fr.write(continut);
    	
    	fr.close();
		
	}
	
	private class AscultatorButoane implements ActionListener{
	public void actionPerformed(ActionEvent e) 
	{
		
		String continutFisier;
		switch( e.getActionCommand() )
		{
		case("Afiseaza"):  {
			
			try {
				afiseazaContinut();
		     	} catch (Exception e1) {
		     		JOptionPane.showMessageDialog(null, "Fisa medicala nu a fost gasita!", ":( - eFiseMedicale", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
			
			break;
		} 
		
		case("Modifica"): {
			
			try {
				aplicaModificari();
				JOptionPane.showMessageDialog(null, "Fisa medicala a fost modificata cu succes!");
		     	} catch (IOException e1) {
		
				e1.printStackTrace();
			}
			
			break;
			
		}
		
		}
		
		}
		
	}
	
	
}
