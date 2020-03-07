import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class Automate_simple extends Automate {
	
	private HashMap<Integer, HashSet<Transition>> transitions = new HashMap<Integer, HashSet<Transition>>();
	private boolean deterministe;

	public Automate_simple(Alphabet X,HashSet<Integer> etats, HashMap<Integer, HashSet<Transition>> transitions , int etatInit, HashSet<Integer> etatFin)
	{
		   super.setX(X);
		   this.transitions = transitions;
		   super.setEtatInit(etatInit);
		   super.setEtatFin(etatFin);
		   super.setEtats(etats);
		   this.deterministe = false;
	}
	
	public Automate_simple reduire()
	{
		HashMap<Integer, HashSet<Transition>> nvTrans = new HashMap<Integer, HashSet<Transition>>();
		HashSet<Integer> nvEtats = new HashSet<Integer> ();
		HashSet<Integer> nvEtatFin = new HashSet<Integer>();	//	Nouveaux etats finaux
		ArrayList<Integer> access = new ArrayList<Integer>();
		ArrayList<Integer> coAccess = new ArrayList<Integer>();
		HashSet<Transition> transit = new HashSet<Transition>();
		Iterator<Transition> it;
		Transition transSuiv = new Transition();
		access.add(super.getEtatInit());
		int i = 0;
		while(i < access.size()) {
			if(transitions.containsKey(access.get(i))) {
				transit = transitions.get(access.get(i));
				it = transit.iterator();
				while(it.hasNext()) {
					transSuiv = it.next();
					if(!access.contains(transSuiv.getEtatSuivant())) {
						access.add(transSuiv.getEtatSuivant());
					}
				}
			}
			i++;
		}
		//On remplit coAccess avec les etats finaux...
		Iterator<Integer> itInt = super.getEtatFin().iterator();
		int etat;
		while(itInt.hasNext()) {
			etat = itInt.next();
			coAccess.add(etat);
		}
		Transition t = new Transition();
		int j = 0;
		boolean appartient;
		while(j < coAccess.size()) {
			for(Map.Entry entry : this.transitions.entrySet()) {
				it = ((HashSet<Transition>) entry.getValue()).iterator();
				appartient = false;
				while(it.hasNext() && !appartient) {
					t = it.next();
					if(t.getEtatSuivant() == coAccess.get(j)) {
						if(!coAccess.contains((Integer)entry.getKey())) coAccess.add((Integer)entry.getKey());
						appartient = true;
					}
				}
			}
			j++;
		}
		int etatActu;
		Transition tr = new Transition();
		Iterator<Transition> itt;
		HashSet<Transition> trans;
		for(Map.Entry entry : this.transitions.entrySet()) {
			etatActu = (Integer)entry.getKey();
			if(coAccess.contains(etatActu) && access.contains(etatActu)) {
				nvEtats.add(etatActu);
				trans = new HashSet<Transition>();
				it = ((HashSet<Transition>)entry.getValue()).iterator();
				while(it.hasNext()) {
					tr = it.next();
					if(coAccess.contains(tr.getEtatSuivant()) && access.contains(tr.getEtatSuivant())) {
						nvEtats.add(tr.getEtatSuivant());
						trans.add(new Transition(tr.getLettre(), tr.getEtatSuivant()));
					}
				}
				if(!trans.isEmpty()) nvTrans.put(etatActu, trans);
			}
		}
		
		itInt = super.getEtatFin().iterator();
		int etFin;
		while(itInt.hasNext()) {
			etFin = itInt.next();
			if(access.contains(etFin)) nvEtatFin.add(etFin);
		}
		

		return new Automate_simple(super.getX(), nvEtats, nvTrans, super.getEtatInit(), nvEtatFin);
	}
public Automate_simple deterministe() {
	
		
			this.deterministe = true;		 
		   return null;
}
public Automate complément(Automate_simple A)
{
	return A;
	
}

public Automate_simple miroir() {	
	HashMap<Integer, HashSet<Transition>> nvTrans = new HashMap<Integer, HashSet<Transition>>();
	int etatOrigin, etatTrans;
	Iterator<Transition> it;
	String lettre;
	Transition tr;
	for(Map.Entry<Integer, HashSet<Transition>> entry : transitions.entrySet()) {
		etatOrigin = entry.getKey();
		it = entry.getValue().iterator();
		while(it.hasNext()) {
			tr = it.next();
			lettre = tr.getLettre();
			etatTrans = tr.getEtatSuivant();
			if(nvTrans.containsKey(etatTrans)) nvTrans.get(etatTrans).add(new Transition(lettre, etatOrigin));
			else {
				nvTrans.put(etatTrans, new HashSet<Transition> ());
				nvTrans.get(etatTrans).add(new Transition(lettre, etatOrigin));
			}
		}
		
	}
	//Modification etat initial et etats finaux......
	HashSet<Integer> nvEtatFin = new HashSet<Integer>();
	nvEtatFin.add(super.getEtatInit());			// L'ensemble des nouveaux etats finaux contient l'ancien etat initial
	int nvEtatInit = 0;							// Nouvel etat initial
	HashSet<Transition> transEtatInit = new HashSet<Transition>();	// Transition du nouvel etat initial
	HashSet<Integer> EtatFin = super.getEtatFin();
	Iterator<Integer> itInt = EtatFin.iterator();
	int etatActu;
	while(itInt.hasNext()) {
		etatActu = itInt.next();		// On parcourt le Set des anciens etats finaux pour copier leurs transitions dans le nouvel etat init 
		if(nvTrans.containsKey(etatActu)) transEtatInit.addAll(nvTrans.get(etatActu));
		nvEtatInit = Integer.parseInt(((Integer)nvEtatInit).toString() + ((Integer)etatActu).toString());		
	}
	nvTrans.put(nvEtatInit, transEtatInit);		// On ajoute les transitions du nouvel etat 
	HashSet<Integer> nvEtats = new HashSet<Integer>();	//	On ajoute aussi nv ETAT init au Set d'etats
	nvEtats.addAll(super.getEtats());
	nvEtats.add(nvEtatInit);
	
	return new Automate_simple(super.getX(), nvEtats, nvTrans, nvEtatInit, nvEtatFin);		// Et enfin on retourne le miroir
	
}

public boolean reconnaissance(String mot) {
	if(!deterministe) deterministe();
	int etatActu = super.getEtatInit(), i = 0, length = mot.length();
	Transition transEtat = new Transition();
	Iterator<Transition> it;
	boolean changerEt;
	boolean bloque = false; // On le met a faux si on ne trouve plus de transitions au milieu du mot
	while(i < length && !bloque) {	// On consomme le mot tant qu'il existe un chemin non bloque
		it = transitions.get(etatActu).iterator();
		changerEt = false;
		while(it.hasNext() && !changerEt) {	// On parcourt toutes les transitions de l'etat actuel
			transEtat = it.next();
			if(transEtat.getLettre().equals(((Character)mot.charAt(i)).toString())) { // S'il existe une transition avec ce caractere
				etatActu = transEtat.getEtatSuivant();						// Alors se deplacer a l'etat suivant
				changerEt = true;			// On a changé d'etat...
				System.out.println(((Character)mot.charAt(i)).toString());
			}	
		}
		if(!changerEt) bloque = true;		// Si on a pas change d'etat alors chemin bloque
		i++;		
	}
	if(!bloque && super.getEtatFin().contains(etatActu)) return true;	// Si l'etat obtenu a la fin de la lecture du mot est un etat FINAL
	else return false;				// Sinon ...
} 

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
