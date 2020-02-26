
public class Etats {
	private boolean init;// si etat initiale ou pas
	private boolean fina;// si c un etat final
	private String nom;
	
    // constructeur
	public Etats (String nom,boolean init,boolean fina)
	{
		this.fina=fina;
		this.init=init;
		this.nom=nom;
	}
	// getteur et setteur
	public void setnom(String nom)
	{
		this.nom=nom;
	}
	public void setInit(boolean init)
	{
		this.init=init;
	}
	public void setFinal(boolean fina)
	{
		this.fina=fina;
	}
	public boolean getInit()
	{
		return init;
		
	}
	public boolean getFinal()
	{
		return fina;
	}
	public String getNom()
	{
		return nom;
	}
}

