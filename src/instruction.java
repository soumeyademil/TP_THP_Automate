
public class instruction {
	private Etats d�part;
	private Etats arriv�;
	private mots mot;
	// constructeur 
	public instruction(Etats d�part, Etats arriv�,mots mot)
	{
		this.d�part=d�part;
		this.arriv�=arriv�;
		this.mot=mot;
	}
	//Setteur 
	public void setd�part(Etats d�part)
	{
		this.d�part=d�part;
	}
	public void setarriv�(Etats arriv�)
	{
		this.arriv�=arriv�;
	}
	public void setmot(mots mot)
	{
		this.mot=mot;
	}
	// getteur 
	public Etats  getd�part( )
	{
		return d�part;
	}
	public Etats getarriv�()
	{
		return arriv�;
	}
	public  mots getmot( )
	{
		return mot;
	}
}
