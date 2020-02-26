import java.util.ArrayList;

public class Automate_simple extends Automate {
	  private alphabet X;
	   private ArrayList<Etats> S = new ArrayList<Etats>();
	   private Etats So;// c'est un "o" pas 0
	   private ArrayList<Etats> Sf = new ArrayList<Etats>();
	   private ArrayList<instruction> I = new ArrayList<instruction>();
	//constructeur
	public Automate_simple(alphabet X,ArrayList<Etats> S,Etats So, ArrayList<Etats> Sf,ArrayList<instruction> I)
	{
		   this.X=X;
		   this.I=I;
		   this.Sf=Sf;
		   this.So=So;
		   this.S=S;
	}
public Automate_simple reduction(Automate_simple A)
{  
	
	
}
public Automate_simple deterministe(Automate_simple A)
{
	
}
public Automate complément(Automate_simple A)
{
	
}
public Automate_simple miroir(Automate_simple A)
{
	
}
public boolean reconnaissance(Automate_simple A,mots mot)
{
	
}
//  Getteurs 
public alphabet getAlphabet()
{
	return X;
}
public ArrayList<Etats> getEtats()
{
	return S;
}
public Etats getEtatInit()
{
	return So;
}
public ArrayList<Etats> getEtatFinal()
{
	return Sf;
}
public ArrayList<instruction> getInstruction()
{
	return I;
}
// Setteur
public void setAlphabet(alphabet X)
{
	this.X=X;
}
public void setEtats(ArrayList<Etats> S)
{
	this.S=S;
}
public void setEtatInit(Etats So)
{
	this.So=So;
}
public void setEtatFinal(ArrayList<Etats> Sf)
{
	this.Sf=Sf;
}
public void setInstruction(ArrayList<instruction> I)
{
	this.I=I;
}





}
