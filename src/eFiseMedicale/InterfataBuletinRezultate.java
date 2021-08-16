package eFiseMedicale;
import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class InterfataBuletinRezultate extends JFrame {
private JButton b;
private JList l;
private JScrollPane jp;

    public boolean stadiuRecoltare(String numePrenume) throws Exception
    {
    	String adresa = "C:\\ProiectPOO\\Cereri"+numePrenume+"Cerere.txt";
    	String line,ultimaLinie="";
    	String rez="";
    	BufferedReader br = new BufferedReader(new FileReader(adresa));
    	while((line = br.readLine()) != null )
    	{
    		ultimaLinie = line;
    	}
    	String[] imparteLinie = ultimaLinie.split(" ");
    	rez = imparteLinie[0];
    	System.out.println(rez);
    	if( rez.equals("are"))
    		return false;
    	else return true;
    	
    }
  
	public InterfataBuletinRezultate() throws Exception
	{
		super("eFiseMedicale - Generator Buletine de Rezultate");
		
		Panel p1 = new Panel();
		Label label = new Label("BULETINE REZULTATE: Selectati pacientul - NU generati buletinul inainte de analizele asistentei!");
		p1.add(label);
		this.add(p1,BorderLayout.NORTH);
		
		Panel p2 = new Panel();
		ArrayList<String> numePacienti = new ArrayList<String>();
		ColectiePacienti p = new ColectiePacienti(); 
		for(Pacient i : p.getLista()) 
		{
			String nume = i.getNume();
			String prenume = i.getPrenume();
			numePacienti.add(nume + " " + prenume);
		}
		l = new JList<>(numePacienti.toArray());
		l.setVisibleRowCount(15);
		jp = new JScrollPane(l);
		p2.add(jp);
		this.add(p2,BorderLayout.CENTER);
		
		Panel p3 = new Panel();
		b = new JButton("Genereaza Buletin de Rezultate");
		p3.add(b);
		AscultatorButoane ab = new AscultatorButoane();
	    b.addActionListener(ab);
		this.add(p3,BorderLayout.SOUTH);
		
	}
	
	public void genereazaFisier(String numeFisier,String continut,boolean append) throws IOException
	{
		File file = new File(numeFisier);
		FileWriter fr = new FileWriter(file,append);
		fr.write(continut);
		fr.close();
	}
	
	public String extrageData(String numePrenume) throws Exception
	{
        String fisier = "C:\\ProiectPOO\\Cereri\\"+numePrenume+"Cerere.txt";
		
		File f = new File(fisier);
		BufferedReader br = new BufferedReader(new FileReader(fisier));
		if( f.exists() == false )
		{
			return null;
		}
		
		String line;
		String data = "";
		
		while((line = br.readLine()) != null)
		data=line;	
		br.close();
		return data;
		
	}
	
	public String extrageDoctor(String numePrenume) throws Exception
	{
    String fisier = "C:\\ProiectPOO\\Cereri\\"+numePrenume+"Cerere.txt";	
    File f = new File(fisier);
	BufferedReader br = new BufferedReader(new FileReader(fisier));
	if( f.exists() == false )
	{
		return null;
	}
	
	String primaLinie = br.readLine();
	String[] imparte = primaLinie.split("doctorului ");
	String doctor = imparte[1];
	
	return doctor;
	
	}
	
	private class AscultatorButoane implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			switch( e.getActionCommand() )
			{
			case("Genereaza Buletin de Rezultate"):
			{
			try {
			String np = l.getSelectedValue().toString();
			String fisier = "C:\\ProiectPOO\\BuletineRezultate\\"+np+"BR.txt";
			
			ColectiePacienti p = new ColectiePacienti();
			Pacient pac = p.cautaPacient(np);
			String infoPac = pac.getInformatii();
			
			String doctorSpecialist = extrageDoctor(np);
			String dataRecoltarii = extrageData(np);
			String continutFisier = infoPac+"\n"+doctorSpecialist+"\n"+dataRecoltarii;
			
			//System.out.println(continutFisier);
			
			
			genereazaFisier(fisier,continutFisier,false);
			System.out.println("Am generat fisierul");
			
			JOptionPane.showMessageDialog(null, "Buletinul de rezultate pentru "+np+" a fost realizat cu succes! :)");
            	
			
			
			
			    }catch(Exception e1) { JOptionPane.showMessageDialog(null, "Pacientul nu are inregistrata o cerere completa!\n(Care sa contina si recoltarea probelor)", ":( - eFiseMedicale", JOptionPane.ERROR_MESSAGE); };
			
			}
			}
		}
	}
}
