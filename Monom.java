package Question1;
/*
 * Class for the monomials of the polynomial, that the elements in the array list
 * */
public class Monom {
	private double con; // the monomial coefficient
	private int pow;	//the monomial power
	
	public Monom(double con, int pow){
		this.con=con;
		this.pow=pow;
	}
	
	public double getCon() {
		return this.con;
	}
	public int getPow() {
		return this.pow;
	}
	
	// this will over ride the toString function from Polynom.class
    public String toString() {
        String strPoly = "";
        if (this.con > 0) {
        	strPoly = strPoly + '+' + this.con+ "x";
        } else if (this.con < 0) {
        	strPoly = strPoly + this.con + "x";
        }
        if (this.pow > 1) {
        	strPoly = strPoly+ "^" + this.pow;
        }
        return strPoly;
    }
}
