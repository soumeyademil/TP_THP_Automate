import java.util.ArrayList;
public class Automate {
   private alphabet X;
   private ArrayList<Etats> S = new ArrayList<Etats>();
   private Etats So;// c'est un "o" pas 0
   private ArrayList<Etats> Sf = new ArrayList<Etats>();
   private ArrayList<instruction> I = new ArrayList<instruction>();
   // constructeur 
   public Automate(alphabet X,ArrayList<Etats> S,Etats So, ArrayList<Etats> Sf,ArrayList<instruction> I )
   {
	   this.X=X;
	   this.I=I;
	   this.Sf=Sf;
	   this.So=So;
	   this.S=S;
	   
   }
   
}
