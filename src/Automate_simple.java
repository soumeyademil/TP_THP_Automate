import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class Automate_simple extends Automate {
	
	private HashMap<Integer, HashSet<Transition>> transitions = new HashMap<Integer, HashSet<Transition>>();

	public Automate_simple(Alphabet X,HashSet<Integer> etats, HashMap<Integer, HashSet<Transition>> transitions , int etatInit, HashSet<Integer> etatFin)
	{
		   super.setX(X);
		   this.transitions = transitions;
		   super.setEtatInit(etatInit);
		   super.setEtatFin(etatFin);
		   super.setEtats(etats);
	}

	private HashSet<Integer> etatSuivant(int etat) { // Retourne les etats successeur d'un etat donné
		HashSet<Integer> set = new HashSet<Integer>();
		Iterator<Transition> it = transitions.get(etat).iterator();
		while(it.hasNext()) {
			set.add(it.next().getEtatSuivant());
		}
		return set;
	}
	
	private boolean accessible (int etat) {
		if(etat == super.getEtatInit()) return true;
		else {
			Iterator<Transition> it;
			boolean res = false;
			for(Map.Entry entry : this.transitions.entrySet()) {
				it = ((HashSet<Transition>) entry.getValue()).iterator();
				boolean arret = false;
				while(it.hasNext() && !arret) {
					if(it.next().getEtatSuivant() == etat && (int)entry.getKey()!=etat) {
						arret = true;
						res = res || accessible((int)entry.getKey());
					}
				}
			}
		return res;
		}
	}
	
	private boolean coaccessible(int etat) {
		if(super.getEtatFin().contains(etat)) return true;
		else {
			boolean res = false;
			if(transitions.containsKey(etat)) {
				Iterator<Transition> it;
				int suiv;
					it = ((HashSet<Transition>) transitions.get(etat)).iterator();
					while(it.hasNext() && !res) {
						suiv = it.next().getEtatSuivant();
						if(suiv != etat) res = res || coaccessible(suiv);
					}				
			}
			
			return res;
		}
	}
	
	
	public Automate_simple reduire()
	{
		HashMap<Integer, HashSet<Transition>> nvTrans = new HashMap<Integer, HashSet<Transition>>();
		HashSet<Integer> nvEtats = new HashSet<Integer> ();
		HashSet<Integer> nvEtatFin = new HashSet<Integer>();	//	Nouveaux etats finaux
		 if(coaccessible(super.getEtatInit())) {
			 nvEtats.addAll(super.getEtats());
			int etat, suiv;
			Iterator<Transition> it;
			Transition t;
			nvTrans.putAll(transitions);	// On copie les transitions de l'automate d'origine dans une nouvelle variable nvTrans
			for(Map.Entry<Integer, HashSet<Transition>> entry : transitions.entrySet()) {
				etat = (int) entry.getKey(); 	// Pour chaque clé on vérifie si l'etat est accessible et coaccessible...
				if(!accessible(etat) || !coaccessible(etat)) {
					nvTrans.remove(etat);			// Si c'est pas le cas, on supprime toute l'etree correspondante dans nvTrans
					if(nvEtats.contains(etat)) nvEtats.remove(etat);	// Ainsi que de l'ensemble d'etats nvEtats
				}
				else {			// Sinon, on parcourt toutes les transitions et on enleve verifie l'accessibilté et la coacc de chaque etat
					it = ((HashSet<Transition>) transitions.get(etat)).iterator();
					while(it.hasNext()) {			
						t = it.next();
						suiv = t.getEtatSuivant();
						if(!accessible(suiv) || !coaccessible(suiv)) {
							((HashSet<Transition>)nvTrans.get(etat)).remove(t);
							if(nvEtats.contains(suiv)) nvEtats.remove(suiv);
						}
					}
				}
			}	
			 HashSet<Integer> etatFin = super.getEtatFin();
			 Iterator<Integer> itint = etatFin.iterator();	// On parcourrt l'ancien ensemble etatFin et on garde ceux qui sont dans nvEtats
			 int etFin;	
			 while(itint.hasNext()) {
				 etFin = itint.next();
				 if(nvEtats.contains(etFin)) nvEtatFin.add(etFin);
			 }

		}

		return new Automate_simple(super.getX(), nvEtats, nvTrans, super.getEtatInit(), nvEtatFin);
	}
/*public Automate_simple deterministe()
{

	
}
public Automate complément(Automate_simple A)
{
	return A;
	
}
public Automate_simple miroir(Automate_simple A)
{
	return A;
	
}
public boolean reconnaissance(Automate_simple A,mots mot)
{
	
} */
	public void ajouterTransition() {
		
	}

	public HashMap<Integer, HashSet<Transition>> getTransitions() {
			return transitions;
		}
	
	public void setTransitions(HashMap<Integer, HashSet<Transition>> transitions) {
			this.transitions = transitions;
		}
	
	public void afficher() {
		
		System.out.println("Alphabet: "+ super.getX());
		System.out.println("Etats: "+super.getEtats());
		System.out.println("Etat initial: "+ super.getEtatInit());
		System.out.println("Etats finaux: "+ super.getEtatFin());
		System.out.println("Transitions: ");
		Iterator<Transition> it ;
		Transition t;
		for(Map.Entry entry : this.transitions.entrySet()) {
			it = ((HashSet<Transition>) entry.getValue()).iterator();
			while(it.hasNext()) {
				t = it.next();
				System.out.println(entry.getKey() + " ---" + t.getLettre() + "---> " + t.getEtatSuivant());
			}

		}
	}

}
