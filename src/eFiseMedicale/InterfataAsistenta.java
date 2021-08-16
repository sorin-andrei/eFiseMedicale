package eFiseMedicale;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class InterfataAsistenta extends JFrame {

	private JButton b1,b2,b3;
	private JTextArea t=new JTextArea(10,50);
	private JList l;
	private JScrollPane jp,jp2;
	
	public void adaugaLaCerere(String s) throws IOException
	{
	  
	   String nP = l.getSelectedValue().toString();
	   String fisier;
	   fisier = "C:\\ProiectPOO\\Cereri\\"+nP+"Cerere.txt";
	   
	   File file = new File(fisier);
   	FileWriter fr = new FileWriter(file,true);
   	fr.write(s);
   	
   	fr.close();
		
		
		
	}
	
	public void printeazaCerere() {

		String nP = l.getSelectedValue().toString();
		  String fisier = "C:\\ProiectPOO\\Cereri\\"+nP+"Cerere.txt";

		  PrinterJob pj = PrinterJob.getPrinterJob();
		  pj.setJobName(" Printeaza cerere ");

		  pj.setPrintable (new Printable() {    
		    public int print(Graphics pg, PageFormat pf, int pageNum) {
		      if (pageNum > 0) return Printable.NO_SUCH_PAGE;
             
		     Graphics2D g2 = (Graphics2D) pg;
             g2.drawString(fisier,(int) pf.getImageableX(), (int) pf.getImageableY());
		     return Printable.PAGE_EXISTS;
		    }
		  });

		  if (pj.printDialog() == false) return;

		  try {
		    pj.print();
		  } catch (PrinterException ex) {
		    
		  }
		}
	

	public int afiseazaContinutCerere() throws IOException
	{
		String fisier;
		String nP = l.getSelectedValue().toString();
		String rez = "";
		fisier = "C:\\ProiectPOO\\Cereri\\"+nP+"Cerere.txt";
				
		
		File temp = new File(fisier);
		boolean ok = temp.exists();
		
		if (ok==false)
		{
		t.setText("Nu s-a gasit cererea");	
		JOptionPane.showMessageDialog(null, "Nu s-a gasit cererea :(", "Eroare", JOptionPane.ERROR_MESSAGE);
		return 0;
		}
		
		BufferedReader br = new BufferedReader(new FileReader(fisier));
		String line;
		
		
		while ((line = br.readLine()) != null) { 
		       rez = rez+line+"\n";
		   }
		t.setText(rez);
		   
		   br.close(); 
		   
		   return 1;
	}
	
	public InterfataAsistenta() throws Exception {
		super("eFiseMedicale");
		
		JPanel p1=new JPanel();
		JLabel l1 = new JLabel("Selectati pacientul"); 
		p1.add(l1);

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
		
		
		
		 b1=new JButton ("Printeaza cererea");
		 p1.add(b1);
			this.add(p1 , BorderLayout.NORTH);
		JPanel p2=new JPanel();
		
		jp2 = new JScrollPane(t);
		p2.add(jp2);
		 t.setEditable(false);
		 this.add(p2 , BorderLayout.CENTER);
		JPanel p3=new JPanel();
		 b2=new JButton ("Inregistreaza probe de sange");
		 p3.add(b2);
		 this.add(p3 , BorderLayout.SOUTH);
		 b3=new JButton("Afiseaza cererea");
		 p1.add(b3);
		 
		 AscultatorButoane ab = new AscultatorButoane();
		    b1.addActionListener(ab);
		    b2.addActionListener(ab);
		    b3.addActionListener(ab);
		 
	}
	
	private class AscultatorButoane implements ActionListener{
		public void actionPerformed(ActionEvent e) 
		{
			switch( e.getActionCommand() )
			{
			case("Printeaza cererea"):  {
				
				printeazaCerere();

			} break;
			case("Afiseaza cererea"): {
				try {
					afiseazaContinutCerere();
				}catch (IOException e2) {
					e2.printStackTrace();
				}
			}break;
			
			case("Inregistreaza probe de sange"): {
				
			
				String nP = l.getSelectedValue().toString();
				
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
				LocalDateTime now = LocalDateTime.now();
				String proba = "S-a recoltat proba pacientului "+nP+ " la data de "+"\n"+dtf.format(now)+"\n";
				
				try {
					if( afiseazaContinutCerere() == 0 )
					JOptionPane.showMessageDialog(null, "Nu s-a recoltat proba");
					
					else 
					
					JOptionPane.showMessageDialog(null,proba); 
				    adaugaLaCerere(proba);

				} catch (HeadlessException e1) {
					
					e1.printStackTrace();
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}

			}break;
		}
	}
}
	
}