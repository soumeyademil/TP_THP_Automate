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
		Scanner sc = new Scanner(System.in);
		boolean result = B.reconnaissance(sc.next());
		System.out.println(result);
		
	}
	

}
