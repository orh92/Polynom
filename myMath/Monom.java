
package myMath;
/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{
	/**
	 * take monom and return string with his info
	 */
	public String toString() {
		return this._coefficient+"*x^"+this.get_power();
	}
	/**
	 * add new monom(m1)
	 * if they have same power so calculate what the new coefficient
	 * if diffrent power so cannot add because it will be polynom
	 * @param m1
	 */
	public void add(Monom m1) {
		if(this.get_power()==m1.get_power()) {
			this.set_coefficient(this.get_coefficient()+m1.get_coefficient());
		}	
		else throw new RuntimeException("Cannot combine 2 diffrent powers monoms");
	}
	/**
	 * multiply the new monom(m1) with the main monom
	 * the power of the main monom+power of the new monom = the new main monom's power
	 * the coefficient of the main monom*the coefficient of the new monom=the new main monom's coefficient
	 * @param m1=the monom that i multiply with him
	 */
	public void multiply(Monom m1) {
		this.set_coefficient(m1.get_coefficient()*this.get_coefficient());
		this.set_power(this.get_power()+m1.get_power());
	}
	/**
	 * monom1 hold the new monom after derivative
	 * olwpow hold the old power of the monom
	 * oldcof hold the old coefficient of the monom
	 * return the derivative of the main monom
	 * @return
	 */
	public Monom derivative() {
		double oldpow;
		double oldcof;
		Monom monom1 = new Monom(_coefficient,_power);
		oldpow=monom1.get_power();
		oldcof=monom1.get_coefficient();
		if(monom1.get_power()==0) {
			monom1.set_coefficient(0);
			return monom1;
		}
		monom1.set_coefficient(oldpow*oldcof);
		monom1.set_power(monom1.get_power()-1);
		return monom1;
	}
	/**
	 * creat monom
	 * @param a=coefficient
	 * @param b=power
	 */
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	/**
	 * deep copy of the main monom
	 * @param ot
	 */
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	/**
	 * get the coefficient of the main monom
	 * @return
	 */
	public double get_coefficient() {
		return _coefficient;
	}
	/**
	 * get the power of the main monom
	 * @return
	 */
	public int get_power() {
		return _power;
	}
	/**
	 * change the coefficient of the main monom
	 * @param a
	 */
	public void set_coefficient(double a){
		this._coefficient = a;
	}
	/**
	 * change the power of the main monom
	 * @param p
	 */
	public void set_power(int p) {
		this._power = p;
	}
	/**
	 * creat private coefficient and power of the main monom
	 */
	private double _coefficient; // 
	private int _power;
	/**
	 * return the function of the monom
	 * x is the value that the function get
	 * return the value of the function at specific "x" 
	 */
	@Override
	public double f(double x) {
		return (_coefficient)*(Math.pow(x, _power));
	} 
}
