import java.util.ArrayList;
import java.util.HashSet;

public abstract class  Automate {
	
   private Alphabet X;
   private int etatInit;
   private HashSet<Integer> etatFin = new HashSet<Integer>();
   
   
	public Alphabet getX() {
		return X;
	}
	public void setX(Alphabet x) {
		X = x;
	}
	public int getEtatInit() {
		return etatInit;
	}
	public void setEtatInit(int etatInit) {
		this.etatInit = etatInit;
	}
	public HashSet<Integer> getEtatFin() {
		return etatFin;
	}
	public void setEtatFin(HashSet<Integer> etatFin) {
		this.etatFin = etatFin;
	}

    
   
   
}
