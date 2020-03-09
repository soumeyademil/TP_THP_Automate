import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Automate_simple A = (Automate_simple) Util.creer();
		System.out.println("----------- A --------------");
		A.afficher();
		Automate_simple B = A.reduire();
		System.out.println("----------- B --------------");
		B.afficher();
		System.out.println("Un mot pour la reconnaissance : ");
		Scanner sc = new Scanner(System.in);
		boolean result = B.reconnaissance(sc.next());
		System.out.println(result);
		System.out.println("----------- Serialisation --------------");	
		System.out.println("Nom de l'automate: ");
		Scanner scNom = new Scanner(System.in);
		String nomAutomate = scNom.next();
		Util.sauvegarder(A, nomAutomate);	// On sauvegarde l'automate
		System.out.println("----------- Deserialisation --------------");
		System.out.println("Nom de l'automate: ");
		scNom = new Scanner(System.in);
		nomAutomate = scNom.next();
		try {
			Automate_simple C = (Automate_simple)Util.recuperer(nomAutomate);	// On recupere l'automate
																				// A la base ca retourne un Objet de type Automate
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

}
