package Question1;
import java.util.ArrayList;
import java.util.*;
/*
 * The Polynom class contain polynomial builder methods and all the methods that can be used on polynomial 
 * 
 * */

//Section A.1
public class Polynom {
	private ArrayList<Monom> polynomial = new ArrayList<>();


	//method that build the array list of the Polynomial
	public Polynom (double [] con, int [] pow)throws Exception{

		if(con.length!=pow.length) {
			throw new Exception("Out of Bounds Exc. different arrays length");
		}
		for(int i=0; i<pow.length;i++) {
			polynomial.add(i,new Monom(con[i],pow[i]));
		}

		sort(polynomial);
		
		for(int i=0; i<polynomial.size()-1;i++) {
			
			polynomial.set(i, new Monom(polynomial.get(i).getCon() + polynomial.get(i).getCon(), polynomial.get(i).getPow()));
            polynomial.remove(i);
		}
	}
//set polynom array list
	public Polynom(ArrayList<Monom> polynomial) {
		this.polynomial = new ArrayList<>(polynomial);
	}

	public ArrayList<Monom> getPolynomial()
	{
		return this.polynomial;
	}


	public void sort(ArrayList<Monom> polynomial) {
		for(int i=0; i<polynomial.size();i++) {
			int minx=i;
			for(int j=i+1; j<polynomial.size();j++) {
				if(polynomial.get(j).getPow()>polynomial.get(minx).getPow())
				{
					minx=j;
				}
			}
			if(minx!=i) {
				Monom temp=polynomial.get(i);
				polynomial.set(i, polynomial.get(minx));
				polynomial.set(minx, temp);
			}
		}
	}

	//Section A.2
	public Polynom plus(Polynom p) {
		Polynom newPolynom = new Polynom(this.polynomial);
		boolean monom ;
		for (int i = 0; i < p.polynomial.size(); i++) {
			monom = false;
			for(int j=0; j<p.polynomial.size();j++)
			{
				if(!monom) {
					int newPow = p.polynomial.get(i).getPow();
					int curPow = newPolynom.polynomial.get(j).getPow();
					if (newPow == curPow) {
						double newCon = p.polynomial.get(i).getCon();
						newPolynom.polynomial.set(j, new Monom(newCon + newPolynom.polynomial.get(j).getCon(), newPow));
						monom = true;
					}
				}
			}
			if (!monom) {
				newPolynom.polynomial.add(0, p.polynomial.get(i));
			}
		}
		newPolynom.sort(newPolynom.getPolynomial());
		return newPolynom;
	}
	
	//Section A.3- A minus method that accepts a polynom as a parameter and returns a polynom that is the difference between the polio
	//And the parameter. The difference is obtained by subtracting the coefficients of members of the same power.
	public Polynom minus(Polynom p) {
		Polynom newPolynom = new Polynom(this.polynomial);
		boolean monom ;
		for (int i = 0; i < p.polynomial.size(); i++) {
			monom = false;
			double newCon = p.polynomial.get(i).getCon();
			int newPow = p.polynomial.get(i).getPow();
			for(int j=0; j<p.polynomial.size();j++)
			{
				if(!monom) {

					int curPow = newPolynom.polynomial.get(j).getPow();

					if (newPow == curPow) {

						newPolynom.polynomial.set(j, new Monom(newPolynom.polynomial.get(j).getCon()-newCon, newPow));
						monom = true;
					}
				}
			}
			if (!monom) {
				newPolynom.polynomial.add(0, new Monom (-newCon,newPow));
			}
		}
		newPolynom.sort(newPolynom.getPolynomial());
		return newPolynom;
	}
	
	//Section A.4 - A method that cuts the polynomial and returns its derivative
	public Polynom cut() {
		Polynom newPolynom = new Polynom(this.polynomial);
		for (int i = 0; i < newPolynom.polynomial.size(); i++) {
			int newPow =  newPolynom.polynomial.get(i).getPow();
			if (newPow == 0) {
				newPolynom.polynomial.remove(i); //the power is 0 and derivative remove it from the result
			} else {
				double newCon =  newPolynom.polynomial.get(i).getCon();
				newPolynom.polynomial.set(i, new Monom( newCon * newPow , newPow - 1)); 
			}
		}
		return newPolynom;
	}

	//Section A.5- Returns a string in the usual way, when you can write the hold in a way
	//the next:
	//	8.0x ^ 3 - 3.0x ^ 2 - x + 7.0
	public String toString() {
		StringBuilder strPolynom = new StringBuilder("");

		strPolynom.append(this.polynomial.get(0).getCon()).append("X").append("^").append(this.polynomial.get(0).getPow());
		
		for (int i = 1; i < this.polynomial.size() - 1; i++) {
			strPolynom.append(polynomial.get(i).toString());
		}
		if (this.polynomial.get(this.polynomial.size() - 1).getPow() == 0) {
			if (this.polynomial.get(this.polynomial.size() - 1).getCon() < 0) {
				strPolynom.append(this.polynomial.get(this.polynomial.size() - 1).getCon());
			} else {
				strPolynom.append("+").append(this.polynomial.get(this.polynomial.size() - 1).getCon());
			}
		} else {
			strPolynom.append(polynomial.get(this.polynomial.size() - 1).toString());
		}
		return strPolynom.toString();
	}
	
	//Section A.6 - Examining whether a polynom obtained as a parameter is equal to a polynom
	//The method was applied to it. Polymers will be considered equal if their members (consisting of a coefficient and a force) are equal.
	public boolean equals(Object p) {
		if (p instanceof Polynom) {
			if (this.polynomial.size() != ((Polynom) p).polynomial.size())
			{
				System.out.println("not equals! The polynomials not in the same length");
				return false;
			}

			for (int i = 0; i < this.polynomial.size(); i++) {
				if ( this.polynomial.get(i).getPow() != ((Polynom) p).polynomial.get(i).getPow()
						||this.polynomial.get(i).getCon() != ((Polynom)p).polynomial.get(i).getCon())
				{
					System.out.println("not equals! The monomials not in the same");
					return false;
				}
			}
			return true;
		} else {
			System.out.println("error!, this object isn't Polynomial");
			return false;
		}
	}
}
