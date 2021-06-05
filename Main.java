package Question1;
import java.util.Scanner;

////Section B - main program that use the Polynom class,Monom class
public class Main {
	public static void main(String[] args) {
		int  method=100;
		Polynom p1 = getPolynomialInput();
		Polynom p2 = getPolynomialInput();
		System.out.println("First polynom p1:  (" + p1 + ")\n");
		System.out.println("Second polynom p2:  (" + p2 + ")\n");
		while(method!=1) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the number of the chosen method: 1- exit; 2-plus polynomials;3- minus polynomials; 4-cut polynomial; 5- check if equals");
			method= sc.nextInt();
			switch(method) {
			case 1:
				break;
			case 2:

				Polynom p3 = p1.plus(p2);
				System.out.println("(" + p1 + ") + (" + p2 + ") = " + p3 + "\n");
				break;
			case 3:
				Polynom p4 = p1.minus(p2);
				System.out.println("("+ p1 +") - ("+ p2 +") = " + p4+ "\n");
				break;
			case 4:
				Polynom p5 = p1.cut();
				System.out.println("Cut of " + p1 + " is: " + p5 + "\n");

				Polynom p6 = p2.cut();
				System.out.println("Cut of " + p2 + " is: " + p6);
				break;
			case 5:
				if (p1.equals(p2)) {
					System.out.println( p1+ " and " +p2 +" are equals\n");
				} else {
					System.out.println( p1+ "and" +p2 +" are  not equals\n");
				}
				break;
			}
		}
	}

	public static Polynom getPolynomialInput() {
		Scanner sc = new Scanner(System.in);
		System.out.println("please enter polynomial size:");
		int polySize = sc.nextInt();
		sc.nextLine(); 
		int[] pow = new int[polySize];
		System.out.println("next enter polynomial powres:");
		Scanner squareScanner = new Scanner(sc.nextLine());
		for (int i = 0; i <polySize; i++) {
			if (squareScanner.hasNextInt()) {
				pow[i] = squareScanner.nextInt();
			} else {
				System.out.println("not enough powres");
				System.exit(1);
			}
		}
		
		double[] con = new double[polySize];
		System.out.println("next enter polynomial coefficients:");
		Scanner numScanner = new Scanner(sc.nextLine());
		for (int i = 0; i < polySize; i++) {
			if (numScanner.hasNextInt()) {
				con[i] = numScanner.nextInt();
			} else {
				System.out.println("not enough coefficients");
				System.exit(1);
			}
		}
		
		try {
			return new Polynom(con, pow);
		} catch (Exception ex) {
			System.exit(1);
			return null;
		}
	}
}

