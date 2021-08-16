package eFiseMedicale;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class InterfataMedic extends JFrame{
 private JButton b1,b2,b3,b4;
 private JTextArea t1=new JTextArea(50,75);;
 
  public InterfataMedic() {
	 super("eFiseMedicale - Medic");
	 
	Panel p1=new Panel();
		p1.add(new JLabel("Bine ati venit domnule doctor!\n") );
		this.add(p1 , BorderLayout.NORTH);
		
	Panel p2=new Panel();
		b1 = new JButton("Genereaza cerere");
			p2.add(b1);
		b2 = new JButton("Verifica buletin de rezultate");
			p2.add(b2);
		b3 = new JButton("Modifica fisa medicala");
	 		p2.add(b3);
	 	b4 = new JButton("Genereaza fisa medicala");
	 		p2.add(b4);
		this.add(p2, BorderLayout.CENTER);
		
	    AscultatorButoane ab = new AscultatorButoane();
	    b1.addActionListener(ab);
	    b2.addActionListener(ab);
	    b3.addActionListener(ab);
	    b4.addActionListener(ab);
 }
	private class AscultatorButoane implements ActionListener{
	public void actionPerformed(ActionEvent e) 
	{

		switch( e.getActionCommand() )
		{
		case("Genereaza cerere"):  {		
			JFrame f1;
			try {
				f1 = new InterfataCereri();
			
			f1.setSize(500,400);
			f1.setVisible(true);
			
			f1.addWindowListener(  new WindowAdapter()  {  
				
				public void windowClosing(WindowEvent e)
				{ System.exit(0);}}
		    );
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			}break;
		   
		    case("Modifica fisa medicala"):{
				JFrame f2;
				try {
					f2 = new InterfataModificaFM();
				
				f2.setSize(600,400);
				f2.setVisible(true);
				
				f2.addWindowListener(  new WindowAdapter()  {  
					
					public void windowClosing(WindowEvent e)
					{ System.exit(0);}}
				
			    );
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			    }break;
			    
			
			case("Genereaza fisa medicala"):{
				JFrame f3;
				try {
					f3= new InterfataFisaMedicala();
					
					f3.setSize(600,400);
					f3.setVisible(true);
					
					f3.addWindowListener( new WindowAdapter() {
						public void  windowClosing(WindowEvent e3)
						{System.exit(0);}
					});

				} catch (Exception e3) {
					e3.printStackTrace();				
				}
				
			}break;
		
			case("Verifica buletin de rezultate"):
			{
				JFrame f1;
				try {
					f1 = new InterfataVerificaBuletin();
				f1.setSize(800,400);
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
 
 
