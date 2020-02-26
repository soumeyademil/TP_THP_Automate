
public class instruction {
	private Etats départ;
	private Etats arrivé;
	private mots mot;
	// constructeur 
	public instruction(Etats départ, Etats arrivé,mots mot)
	{
		this.départ=départ;
		this.arrivé=arrivé;
		this.mot=mot;
	}
	//Setteur 
	public void setdépart(Etats départ)
	{
		this.départ=départ;
	}
	public void setarrivé(Etats arrivé)
	{
		this.arrivé=arrivé;
	}
	public void setmot(mots mot)
	{
		this.mot=mot;
	}
	// getteur 
	public Etats  getdépart( )
	{
		return départ;
	}
	public Etats getarrivé()
	{
		return arrivé;
	}
	public  mots getmot( )
	{
		return mot;
	}
}
