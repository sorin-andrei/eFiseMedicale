package eFiseMedicale;
import java.io.*;
import java.util.*;

public class ColectiePacienti {
	private ArrayList listaPac = new ArrayList<Pacient>();
	
	public void genereazaFisier(String numeFisier,String continut,boolean append) throws IOException
	{
		File file = new File(numeFisier);
		FileWriter fr = new FileWriter(file,append);
		fr.write(continut);
		fr.close();
	}
	
	public ColectiePacienti() throws Exception
	{
		String linie;
		
		String fisier = "C:\\ProiectPOO\\Pacienti.txt";
		File f = new File(fisier);
		if(f.exists()==false)
		{
			genereazaFisier(fisier,"",false);
		}
		
		BufferedReader br = new BufferedReader(new FileReader("C:\\ProiectPOO\\Pacienti.txt"));
		
		
		
		while((linie = br.readLine()) != null)
		{
			String[] informatie = linie.split(":");
			String nume = informatie[0];
			String prenume = informatie[1];
			String dataN = informatie[2];
			String cnp = informatie[3];
			String varsta = informatie[4];
			String sex = informatie[5];
			String adresa = informatie[6];
			Pacient pac = new Pacient(nume,prenume,dataN,cnp,varsta,sex,adresa);
			
		    listaPac.add(pac);
		}
		
	}
	
	public ArrayList<Pacient> getLista()
	{
		return listaPac;
	}
	
	public Pacient cautaPacient(String numePrenume)
	{
		String[] informatii = numePrenume.split(" ");
		String nume = informatii[0];
		String prenume = informatii[1];
    		
		for(Pacient i : this.getLista())
		{
		if( (i.getNume().equals(nume)) && (i.getPrenume().equals(prenume) ) )	
		{
		System.out.println("S-a gasit pacientul" + i.getNume());
		return i;
		}
		
		}
		return null;
	}
}
