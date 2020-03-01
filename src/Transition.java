
public class Transition {
    private String lettre;
    private int etatSuivant;
    
    
    public Transition() {
    	
    }   
    public Transition(String v, int x) {
        lettre = v;
        etatSuivant = x;
    }
	public String getLettre() {
		return lettre;
	}
	public void setLettre(String lettre) {
		this.lettre = lettre;
	}
	public int getEtatSuivant() {
		return etatSuivant;
	}
	public void setEtatSuivant(int etatSuivant) {
		this.etatSuivant = etatSuivant;
	}
    
    
}
