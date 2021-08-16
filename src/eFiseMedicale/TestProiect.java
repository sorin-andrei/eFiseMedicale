package eFiseMedicale;
import java.io.*;
import java.nio.file.*;
import java.awt.event.*;
import java.awt.*;
public class TestProiect  {
    public static void main(String[] args) throws Exception
    {
    	
    	
    	File fl1 = new File("C:\\ProiectPOO");
    	File fl2 = new File("C:\\ProiectPOO\\Cereri");
    	File fl3 = new File("C:\\ProiectPOO\\BuletineRezultate");
    	File fl4 = new File("C:\\ProiectPOO\\FiseMedicale");
    	
    	if(fl1.exists()==false)
    		fl1.mkdir();
    	if(fl2.exists()==false)
    		fl2.mkdir();
    	if(fl3.exists()==false)
    		fl3.mkdir();
    	if(fl4.exists()==false)
    		fl4.mkdir();
    	
        ColectiePacienti c = new ColectiePacienti();
    	
        
        
        
    	Frame f1 = new Aplicatie();
		f1.setSize(300,200);
		f1.setVisible(true);
		
		//Inchidem fereastra 
		f1.addWindowListener(  new WindowAdapter()  
		{              
	    public void windowClosing(WindowEvent e)
	    { System.exit(0);}
		}
	    ); 
    
    
    }
}
