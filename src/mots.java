
public class mots {
	private alphabet X;// alphabet pour construire les mots
	private int taille;
	
	//constructeur
	public mots (alphabet X ,int taille)
	{
		this.taille=taille;
		this.X=X;
	}
	//getteur
	public int gettaille()
	{
		return taille;
	}
	public alphabet getX()
	{
		return X;
	}
	//setteur
	public void setalphabet(alphabet X)
	{
		this.X=X;
	}
	public void settaille(int taille)
	{
		this.taille=taille;
	}

}
