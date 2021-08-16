package eFiseMedicale;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfataVerificaBuletin extends JFrame
{
	private JTextArea ta;
	private JList l;
	private JScrollPane jp;
	private JButton b,b2;
	private JComboBox c;
	
	public InterfataVerificaBuletin() throws Exception
	{
		super("eFiseMedicale - Verificare buletin");
		
		JPanel p1 = new JPanel();
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
		

		b = new JButton("Afiseaza buletin");
		p1.add(b);
		
		this.add(p1, BorderLayout.NORTH);
		
		JPanel p2 = new JPanel();
		ta = new JTextArea(15,30);
		ta.setEditable(false);
		p2.add(ta);
		
		c = new JComboBox();
		c.addItem("Tratament cu iod radioactiv");
		c.addItem("Medicamente anti-tiroidiene");
		c.addItem("Interventie Chirurgicala Tiroidectomie");
		c.addItem("Tratament cu Levotiroxina");
		c.addItem("Tratament Alopat");
		c.addItem("Tratament cu Probiotice");
		c.addItem("Tratament cu Testosteron");
		c.addItem("Insulina injectabila");
		c.addItem("Tratament pentru hipertensiune arteriala");
		c.addItem("Suplimente de Cortizon si Aldosteron");
		c.addItem("Medicamentatie pentru Oftalmopatie Graves");
		//c.hide();
		p2.add(c);
		
		this.add(p2, BorderLayout.CENTER);
		
		JPanel p3 = new JPanel();
		b2 = new JButton("Adauga la istoric");
		p3.add(b2);
		this.add(p3, BorderLayout.SOUTH);
		
		b2.hide();
		c.hide();
		
		AscultatorButoane ab = new AscultatorButoane();
		b.addActionListener(ab);
		b2.addActionListener(ab);
	}
	
	public String extrageRezultat(String numePrenume) throws Exception
	{
		String fisier = "C:\\ProiectPOO\\BuletineRezultate\\"+numePrenume+"BR.txt";
		File f = new File(fisier);
		if(f.exists()==false) return null;
		BufferedReader br = new BufferedReader(new FileReader(fisier));
		String line;
		String ultimulRand=null;
		
		while((line = br.readLine()) != null  )
		ultimulRand = line;
		
		String[] token = ultimulRand.split("rezultat ");
		String rez = token[1];
		br.close();
		return rez;
	}
	
	public void completeazaFisier(String numeFisier,String continut,boolean append) throws IOException
	{
		File file = new File(numeFisier);
		FileWriter fr = new FileWriter(file,append);
		fr.write(continut);
		fr.close();
	}
	
	private class AscultatorButoane implements ActionListener
	{
	public void actionPerformed(ActionEvent e) 
	{
     	switch( e.getActionCommand() )
		{
		case("Afiseaza buletin"): 
		{
		try {
		String numePrenume = l.getSelectedValue().toString();
		String fisier = "C:\\ProiectPOO\\BuletineRezultate\\"+numePrenume+"BR.txt";
		String rez="";
		BufferedReader br = new BufferedReader(new FileReader(fisier));
		
		String line;
		while((line = br.readLine()) != null )
		{
			rez=rez+line+"\n";
		}
		ta.setText(rez);
		String rezultat = extrageRezultat(numePrenume);
		if(rezultat.equals("Negativ"))
		{
			b2.hide();
			c.hide();
			String fisaMedicala = "C:\\ProiectPOO\\FiseMedicale\\"+numePrenume+".txt";
			BufferedReader br2 = new BufferedReader(new FileReader(fisier));
			String continutFisier="";
			String line2;
			while((line2 = br2.readLine()) != null )
			continutFisier = line2;
			completeazaFisier(fisaMedicala,continutFisier,true);
			JOptionPane.showMessageDialog(null, "Rezultatul este NEGATIV: \nS-a trecut in istoricul medical al pacientului "+numePrenume);
		}
		else
		{
			b2.show();
			c.show();
			JOptionPane.showMessageDialog(null, "Rezultatul este POZITIV: \nSelectati tratamentul sugerat pentru pacientul "+numePrenume);
		}
		System.out.println(rez);
		
		}catch(Exception e1) { JOptionPane.showMessageDialog(null, "Chimistul nu a analizat probele!", ":( - eFiseMedicale", JOptionPane.ERROR_MESSAGE); c.hide(); b2.hide(); ta.setText("X_X"); };
        }; break;
		
		case("Adauga la istoric"):
		{
		   try {
		   String numePrenume = l.getSelectedValue().toString();
		   String fisaMedicala = "C:\\ProiectPOO\\FiseMedicale\\"+numePrenume+".txt";
		   String fisierBuletin = "C:\\ProiectPOO\\BuletineRezultate\\"+numePrenume+"BR.txt";
		   String continutFisier = null;
		   BufferedReader br = new BufferedReader(new FileReader(fisierBuletin));
		   String line;
		   while((line = br.readLine()) != null )
		   continutFisier = line;
		   
		   String rezultat = extrageRezultat(numePrenume);
		   if(rezultat.equals("Pozitiv"))
		   {
			   String tratament = c.getSelectedItem().toString();
			   String recomandare = "\nSe recomanda urmatorul tratament: "+tratament;
			   continutFisier = "\n"+continutFisier+recomandare+"\n";
		   }
		   
		   
		   completeazaFisier(fisaMedicala,continutFisier,true);
		   JOptionPane.showMessageDialog(null, "Sugestia tratamentului a fost adaugata la fisa medicala a pacientului "+numePrenume);
		   
		   }catch(Exception e1) { JOptionPane.showMessageDialog(null, "Fisa medicala a pacientului nu a fost gasita!", ":( - eFiseMedicale", JOptionPane.ERROR_MESSAGE); };
		}; break;
		
		}
		
	}
    }
	
	

}
