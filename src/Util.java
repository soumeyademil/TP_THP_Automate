import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public abstract class Util {
	
	public static Automate creer() {
		int i,j;
		String car;
		HashMap<Integer, HashSet<Transition>> transitions = new HashMap<Integer, HashSet<Transition>>();
		HashSet<Integer> etats = new HashSet<Integer>();
		HashSet<String> alpha = new HashSet<String>();
		System.out.println("Alphabet :");
		Scanner sc = new Scanner(System.in);
		String alp = sc.nextLine();
		while(!alp.equals("OK")) { // On arrete lorsque on entre "OK"
			alpha.add(alp);
			sc = new Scanner(System.in);
			alp = sc.nextLine();
		}

		Alphabet X = new Alphabet(alpha);
		System.out.println("Les instructions: Par ex 0 a 1");
		HashSet<Transition> transition = new HashSet<Transition>();
		sc = new Scanner(System.in);
		String instruction = sc.nextLine();
		Character c;
		String[] str;
		while(!instruction.equals("OK")) { // Pareil on s'arrete a OK
			str = instruction.split(" ");
			if(alpha.contains(str[1])) { // On verifie si la lettre en entree appartient a l'alphabet
				i = Integer.parseInt(str[0]);  // On soustrait le code ASCII de 0 pour tomber sur le bon entier
				j = Integer.parseInt(str[2]);	// Pour se placer a l'etat suivant on doit passer 2 caracteres et deux blancs de la chaine en entree
				car = str[1];
				if(transitions.containsKey(i)) { // Si l'etat de depart existe deja alors ajouter une transition
					transition = transitions.get(i);
					transition.add(new Transition(car, j)); 
					transitions.replace(i, transition);
				}
				else {								//Sinon creer l'etat et lui ajouter la transition
					transition = new HashSet<Transition>();
					transition.add(new Transition(car, j));
					transitions.put(i, transition);
				}
				etats.add(i); 
				etats.add(j);
			}
			else System.out.println("Cette lettre n'appartient pas a l'alphabet. Veuillez reessayer ");
			sc = new Scanner(System.in);
			instruction = sc.nextLine();
		}
		
		System.out.println("Etat initial :");
		sc = new Scanner(System.in);
		int etInit = sc.nextInt();
		etats.add(etInit);
		HashSet<Integer> etatFin = new HashSet<Integer>();
		System.out.println("Etats finaux :");
		sc = new Scanner(System.in);
		alp = sc.next();
		while(!alp.equals("OK")) {
			etatFin.add(Integer.parseInt(alp));
			etats.add(Integer.parseInt(alp));
			sc = new Scanner(System.in);
			alp = sc.next();
		}
		
		return new Automate_simple(X, etats, transitions, etInit, etatFin);
		
	}

}
