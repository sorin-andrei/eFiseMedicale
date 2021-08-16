package eFiseMedicale;

public class Pacient {
	
	private String nume,
	prenume,dataNasterii,CNP
	,varsta,sex,adresa;
	private String doctorSpecialist,dataRecoltarii;
	
	public Pacient(String n, String p, String dN, String c, String v, String s, String a)
	{
	nume = n;	
	prenume = p;
	dataNasterii = dN;	
	CNP = c;
	varsta = v;
	sex = s;
	adresa = a;
	}
	
	public String getInformatii()
	{
		return nume + "\n" + prenume + "\n" + dataNasterii + "\n" + CNP + "\n" + varsta + "\n" + sex + "\n" + adresa+"\n";
	}
	
	public String getNume()
	{
		return nume;
	}
	public String getPrenume()
	{
		return prenume;
	}
	public String getDataNasterii()
	{
		return dataNasterii;
	}
	public String getCNP()
	{
		return CNP;
	}
	public String getVarsta()
	{
		return varsta;
	}
	public String getAdresa()
	{
		return adresa;
	}
	
	public void setDoctorSpecialist(String ds)
	{
		this.doctorSpecialist = ds;
	}
	public String getDoctorSpecialist()
	{
		return doctorSpecialist;
	}
	public void setDataRecoltarii(String dr)
	{
		this.dataRecoltarii = dr;
		
		
		
	}
	public String getDataRecoltarii()
	{
		return dataRecoltarii;
	}

}
