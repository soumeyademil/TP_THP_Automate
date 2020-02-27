
public class Transition {
    private char lettre;
    private int etatSuivant;
    
    
    public Transition() {
    	
    }   
    public Transition(char v, int x) {
        lettre = v;
        etatSuivant = x;
    }
	public char getLettre() {
		return lettre;
	}
	public void setLettre(char lettre) {
		this.lettre = lettre;
	}
	public int getEtatSuivant() {
		return etatSuivant;
	}
	public void setEtatSuivant(int etatSuivant) {
		this.etatSuivant = etatSuivant;
	}
    
    
}
