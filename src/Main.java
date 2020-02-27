import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int i,j;
		char car;
		HashMap<Integer, HashSet<Transition>> transitions = new HashMap<Integer, HashSet<Transition>>();
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
		while(!instruction.equals("OK")) { // Pareil on s'arrete a OK
			c = instruction.charAt(2);
			if(alpha.contains(c.toString())) { // On verifie si la lettre en entree appartient a l'alphabet
				i = instruction.charAt(0) - 48;  // On soustrait le code ASCII de 0 pour tomber sur le bon entier
				j = instruction.charAt(4) - 48;	// Pour se placer a l'etat suivant on doit passer 2 caracteres et deux blancs de la chaine en entree
				car = instruction.charAt(2);
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
			}
			else System.out.println("Cette lettre n'appartient pas a l'alphabet. Veuillez reessayer ");
			sc = new Scanner(System.in);
			instruction = sc.nextLine();
		}
		
		System.out.println("Etat initial :");
		sc = new Scanner(System.in);
		int etInit = sc.nextInt();
		HashSet<Integer> etatFin = new HashSet<Integer>();
		System.out.println("Etats finaux :");
		sc = new Scanner(System.in);
		alp = sc.next();
		while(!alp.equals("OK")) {
			etatFin.add(Integer.parseInt(alp));
			sc = new Scanner(System.in);
			alp = sc.next();
		}
		
		Automate_simple A = new Automate_simple(X, transitions, etInit, etatFin);
		A.afficher();
	}
	

}
