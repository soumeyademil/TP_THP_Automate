import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public abstract class Util {
	
	/**
	 * Cette methode permet de creer un automate simple
	 * @return	
	 */
	public static Automate_simple creer() {
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
	
	
	/**
	 * Cette methode permet de serialiser un automate
	 * @param A
	 */
	public static void sauvegarder(Automate A, String nom) {
		ObjectOutputStream oos;
		String nomFichier = nom + ".bin";
		try {
			oos = new ObjectOutputStream(
					new BufferedOutputStream(
							new FileOutputStream(
									new File(nomFichier))));
			oos.writeObject(A);
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

	}
	
	/**
	 * Cette methode permet de recuperer un automate deja sauvegarde, pour le modifier
	 * @param nom
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static Automate recuperer(String nom) throws ClassNotFoundException {
		ObjectInputStream ois;
		Automate_simple C = null;
		String nomFichier = nom + ".bin";
		try {
			ois = new ObjectInputStream(
					new BufferedInputStream(
							new FileInputStream(
									new File(nomFichier))));
			C = (Automate_simple) ois.readObject();
			C.afficher();
			ois.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return C;
	}
	
	
	
	
}

