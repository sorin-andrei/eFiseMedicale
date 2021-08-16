package eFiseMedicale;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;


public class Aplicatie extends JFrame{
	private JComboBox c;
	private JButton select;
	
	public Aplicatie()
	{
		super("eFiseMedicale");
		
		JPanel p0 = new JPanel();
		p0.add(new JLabel("Bine ati venit!"));
		this.add(p0,BorderLayout.NORTH);
		
		JPanel p = new JPanel();
		
		p.add(new JLabel("\nLogare ca"));
        
		c = new JComboBox();
		c.addItem("Pacient");
		c.addItem("Medic");
		c.addItem("Asistenta");
		c.addItem("Chimist");
	    p.add(c);
	 
	    
	    select = new JButton ("Select");
	    p.add(select);
	    AscultatorButoane ab = new AscultatorButoane();
	    select.addActionListener(ab);
		this.add(p, BorderLayout.CENTER);
		

	}
	
	public class AscultatorButoane implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			String selectie = c.getSelectedItem().toString();
			
			switch( e.getActionCommand() )
			{
			case("Select"): 
			switch(selectie)
			{
			case("Medic"): {
			
			InterfataMedic f4 = new InterfataMedic();
			f4.setSize(400,300);
			f4.setVisible(true);
			
			//Inchidem fereastra 
			f4.addWindowListener(  new WindowAdapter()  
			{              
		    public void windowClosing(WindowEvent e)
		    { System.exit(0);}
			}
		    );
			
			break;
			}
			case("Asistenta"): 
			{
				try {
			
				JFrame f1 = new InterfataAsistenta();
				f1.setSize(600,400);
				f1.setVisible(true);
				
				//Inchidem fereastra 
				f1.addWindowListener(  new WindowAdapter()  
				{              
			    public void windowClosing(WindowEvent e)
			    { System.exit(0);}
				}
			    );
				
				}catch(Exception e1)
				{
					e1.printStackTrace();
				}
			
			}
				
				; break;
			case("Chimist"): 
				
			{
				JFrame f1 = new InterfataChimist();
				f1.setSize(400,150);
				f1.setVisible(true);
				
				//Inchidem fereastra 
				f1.addWindowListener(  new WindowAdapter()  
				{              
			    public void windowClosing(WindowEvent e)
			    { System.exit(0);}
				}
			    );
			}
				
				; break;
			case("Pacient"): {
			JFrame f1 = new InterfataPacient();
			f1.setSize(650,500);
			f1.setVisible(true);
			                          
			//Inchidem fereastra 
			f1.addWindowListener(  new WindowAdapter()  
			{              
		    public void windowClosing(WindowEvent e)
		    { System.exit(0);}
			}
		    );
			
		    } break;
			}
			
			{
	        break; }
	}
	
			
			
		}
	}
  
   
}
