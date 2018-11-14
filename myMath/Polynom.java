package myMath;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

//import org.omg.CORBA.Current;

import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */

public class Polynom implements Polynom_able{
	private ArrayList<Monom> polynom=new ArrayList<Monom>();
	/**
	 *  default constructor
	 */
	public Polynom(){

	}
	/**
	 *  this function get polynom and construct like him
	 *  p1 is the polynom that i construct like him
	 * @param p1
	 */
	public  Polynom(Polynom p1) {
		polynom=((Polynom)p1.copy()).polynom;
	}
	/**
	 * Computes log2 of "bits" via logic operations as its the fastest way, works on integers only and have error margin.
	 * @param bits [the number to calculate]
	 * @return log2(bits)
	 *took this idea from https://stackoverflow.com/questions/3305059/how-do-you-calculate-log-base-2-in-java-for-integers
	 */
	private static int binlog( int bits ) // returns 0 for bits=0
	{
		int log = 0;
		if( ( bits & 0xffff0000 ) != 0 ) { bits >>>= 16; log = 16; }
		if( bits >= 256 ) { bits >>>= 8; log += 8; }
		if( bits >= 16  ) { bits >>>= 4; log += 4; }
		if( bits >= 4   ) { bits >>>= 2; log += 2; }
		return log + ( bits >>> 1 );
	}

	/**
	 * string=string that hold the polynom info
	 * monom has the information about coefficient and poewr
	 * counter=0 at start to ensure the first iteration(to know if to add - )
	 *return me the string with the polynom
	 */
	public String toString() {
		Iterator<Monom> currentPlace=this.iteretor();
		if(this.isZero()) {return "0";}
		String string= "";
		boolean flag=false;
		Monom monom;
		int counter=0;
		while(currentPlace.hasNext()) {
			monom=currentPlace.next();
			if(monom.get_coefficient()<0||counter==0) {
				string=string+monom.get_coefficient()+"*x^"+monom.get_power();
				flag=true;
			}		
			if(flag==false&&counter!=0) {
				string=string+"+"+monom.get_coefficient()+"*x^"+monom.get_power();
			}
			counter++;
			flag=false;
		}
		return string;
	} 
	
	/**
	 * 
	 * @param st= the string that the function get
	 * monom1 hold the monom that i creat
	 * temp is the temporary string that i use to creat monom
	 * temp2 is the temporary string that i use to creat monom(after for ends)
	 * counter holds the index of the sign(x/-)
	 * k hold the index of x(if exist)
	 */
	public  Polynom(String st){
		try {
			st=st.replaceAll(" ","");
			st=st.replaceAll(Pattern.quote("*"),"");
			st=st.replaceAll(Pattern.quote("^"),"");
			int counter=0;
			Monom monom1;
			double a;
			int b;
			int k;
			String temp;
			for (int i=0; i<st.length(); i++) {
				//if i is zero so dont check the first sign
				if ((st.charAt(i)== '+'|| st.charAt(i)=='-')&&i!=0) {
					temp=st.substring(counter, i);//take the monom in any shape
					if (temp.contains("x")) {
						k=temp.indexOf('x');
						if(temp.charAt(0)=='+') {
						 if(k==1) {
								a=1;
								if(k==temp.length()-1) {b=1;}
								else {b=Integer.parseInt(temp.substring(k+1));}
							}
							else {
								a=Double.parseDouble(temp.substring(0,k));
							if(k==temp.length()-1) {b=1;}
							else {b=Integer.parseInt(temp.substring(k+1));}
							}
									
						}
						else if(temp.charAt(0)=='-') {
						
						 if(k==1) {a=-1;
							if(k==temp.length()-1) {b=1;}
							else {b=Integer.parseInt(temp.substring(k+1));}
							}
							
							else {a=Double.parseDouble(temp.substring(0,k));
							if(k==temp.length()-1) {b=1;}
							else {b=Integer.parseInt(temp.substring(k+1));}
							}
						}
						else {
							if(k==0) {a=1;
							if(k==temp.length()-1) {b=1;}
							else {b=Integer.parseInt(temp.substring(k+1));}
							}
							else {a=Double.parseDouble(temp.substring(0,k));
							if(k==temp.length()-1) {b=1;}
							else {b=Integer.parseInt(temp.substring(k+1));}
							}
						}
					
						monom1 = new Monom(a,b);
						this.add(monom1);
					}

					//not contain x
					else{
						a=Double.parseDouble(temp);
						b=0;
						monom1 = new Monom(a,b);
						this.add(monom1);
					}
					counter=i;		
				}	

			}

			//for end and need to creat last monom
			String temp2=st.substring(counter);
			if (temp2.contains("x")) {
				k=temp2.indexOf('x');
				if(k==0) {
					a=1;
					if (k==temp2.length()-1) {
						b=1;	
					}
					else {
						b=Integer.parseInt(temp2.substring(k+1));
					}
				}
				//k is not 0
				else if(k==1) {
					if(temp2.charAt(0)=='+') {
						a=1;
						if (k==temp2.length()-1) {
							b=1;	
						}
						else {
							b=Integer.parseInt(temp2.substring(k+1));
						}
					}
					else if(temp2.charAt(0)=='-') {
						a=-1;
					if (k==temp2.length()-1) {
						b=1;	
					}
					else {
						b=Integer.parseInt(temp2.substring(k+1));
					}
					}
					else {
						a=Double.parseDouble(temp2.substring(0,k));
						if (k==temp2.length()-1) {
							b=1;	
						}
						else {
							b=Integer.parseInt(temp2.substring(k+1));
						}
					}
				}
				//k>1
				else {
					a=Double.parseDouble(temp2.substring(0,k));
					if (k==temp2.length()-1) {
						b=1;	
					}
					else {
						b=Integer.parseInt(temp2.substring(k+1));
					}
				}

			
				monom1 = new Monom(a,b);
				this.add(monom1);

			}
			//not contain x
			else {
				a=Double.parseDouble(temp2);
				b=0;
				monom1 = new Monom(a,b);	
				this.add(monom1);
			}
		}
		catch (Exception e) {
			throw new RuntimeException("Invaild input");
		}
	}
	
	/**
	 * gives the value of the polynom at specific "x"
	 * value hold the value of the function after the calculation
	 * monom hold the pointer of the polynom
	 * and f(x) calculate value of each monom and return the total calculation
	 */
	@Override
	public double f(double x) {
		double value=0;
		Iterator<Monom> currentPlace= iteretor();
		Monom monom;
		while(currentPlace.hasNext()) {
			monom=currentPlace.next();

			value=value+monom.f(x);
		}
		return value;
	}

	/**
	 * take the polynom(p1) and add him to the main polynom4
	 * currentPlace1=pointer of p1 polynom
	 * currentPlace2=pointer of the main polynom
	 * monom1 hold the pointer of p1 polynom
	 * monom2 hold the pointer of the main polynom
	 * flag hold the answer if found same power and done my mission each iteration
	 * if dont find same power so add him
	 * return the sorted polynom
	 * 
	 */
	@Override
	public void add(Polynom_able p1) {
		Iterator<Monom> currentPlace1= p1.iteretor();
		Iterator<Monom> currentPlace2= iteretor();
		boolean flag=false;
		Monom monom1;
		while(currentPlace1.hasNext()) {
			monom1	= currentPlace1.next();
			while(currentPlace2.hasNext()) {
				Monom monom2	= currentPlace2.next();
				if(monom2.get_power()==monom1.get_power()) {
					monom2.set_coefficient(monom1.get_coefficient()+monom2.get_coefficient());
					if(monom2.get_coefficient()==0) {
						currentPlace2.remove();
					}
					flag=true;
					break;
				}
			}
			if(flag==false) {
				polynom.add(new Monom(monom1.get_coefficient(),monom1.get_power()));
				currentPlace2= iteretor();
			}
			flag=false;
		}
		polynom.sort(new Monom_Comperator());
	}

	@Override
	/**
	 * take the new monom and add him to the polynom(in array list)
	 * current=pointer of the main polynom
	 * monom hold the monom every iteration
	 * return the sorted polynom after add
	 */
	public void add(Monom m1) {
		Iterator<Monom> current= iteretor();
		Monom monom;
		while(current.hasNext()) {
			monom = current.next();
			if(	m1.get_power()==monom.get_power()) {
				monom.add(new Monom(m1.get_coefficient(),m1.get_power()));
				if (monom.get_coefficient()==0) {
					current.remove();
				}
				return;
			}		
		}
		if(m1.get_coefficient()!=0) {
			polynom.add(new Monom(m1.get_coefficient(),m1.get_power()));
		}
		polynom.sort(new Monom_Comperator());
		return;
	}
	/**
	 * take the new polynom(p1) and the main polynom
	 * and substruct p1 from the main polynom
	 * check each monom in p1 with all the monoms of main polynom
	 * if cant find similar monom(monom with same _power) so add this monom to main polynom with "-"sign
	 * currentPlace1=pointer of polynom p1
	 * currentPlace2=pointer of main polynom
	 * flag hold true if found same power , and hold false if still didnt found same power
	 * monom1 hold the pointer of p1(allways get nexted if its allowed)
	 * monom2 hold the pointer of main polynom(allways get nexted if it allowed)
	 */
	@Override
	public void substract(Polynom_able p1) {
		Iterator<Monom> currentPlace1= p1.iteretor();
		Iterator<Monom> currentPlace2= iteretor();
		Monom monom1,monom2;
		boolean found=false;
		while(currentPlace1.hasNext()) {
			monom1	= currentPlace1.next();
			found = false;
			while(currentPlace2.hasNext()) {
				monom2	= currentPlace2.next();
				if(monom2.get_power()==monom1.get_power()) {
					found = true;
					if(monom2.get_coefficient()-monom1.get_coefficient()==0) {
						currentPlace2.remove();
						break;
					}
					else {
						monom2.set_coefficient(monom2.get_coefficient()-monom1.get_coefficient());
						break;}
				}
			}
			if(!found) {
				monom1.set_coefficient(-monom1.get_coefficient());
				polynom.add(new Monom(monom1.get_coefficient(),monom1.get_power()));}
			currentPlace2= iteretor();
		}
		polynom.sort(new Monom_Comperator());
	}
	/**
	 * 
	 * @return the Monom's container as arraylist<Monom>
	 */
	public ArrayList<Monom> getList() {
		return polynom;
	}
	/**
	 * take the new polynom(p1) and take each monom at this polynom
	 * and multiply him with all the monoms of the main polynom
	 * currentPlace1=pointer of the p1 polynom
	 * currentPlace2=pointer of the main polynom
	 * afterMult(have new  polynom that hold the monoms after multiply) to help me save the old polynoms as they are
	 * 
	 * sort the polynom
	 */
	@Override
	public void multiply(Polynom_able p1) {
		Iterator<Monom> currentPlace1= p1.iteretor();
		Iterator<Monom> currentPlace2= iteretor();
		Polynom afterMult=new Polynom();
		Monom monom1,monom2, monom3;
		if(currentPlace1.hasNext()&&!currentPlace2.hasNext()) {
			System.out.println("im zero");
		}
		if(currentPlace2.hasNext()&&!currentPlace1.hasNext()) {System.out.println("p1 is zero");}
		if(!currentPlace2.hasNext()&&!currentPlace1.hasNext()) {System.out.println("both are zero");}
		if(currentPlace2.hasNext()&&currentPlace1.hasNext()) {
			while(currentPlace1.hasNext()){
				monom1=currentPlace1.next();
				while(currentPlace2.hasNext()) {
					monom2=currentPlace2.next();
					monom3=new Monom(monom1.get_coefficient()*monom2.get_coefficient(),monom1.get_power()+monom2.get_power());
					afterMult.add(monom3);
				}
				currentPlace2=iteretor();
			}
			polynom=afterMult.getList();
			polynom.sort(new Monom_Comperator());	
		}
	}
	/**
	 * check if the new polynom(p1) is same polynom as the main polynom
	 * if yes return true ,if not return false
	 * currentPlace1 if the pointer of p1 monoms
	 * currentPlace2 is the pointer of the main monoms
	 * flag will hold the answer true/false if polynoms are equals
	 * i assume that p1 is sorted polynom
	 * monom1 is the monom of p1, monom2 is the monom of polynom2(main)
	 */
	@Override
	public boolean equals(Polynom_able p1) {
		Iterator<Monom> currentPlace1= p1.iteretor();
		Iterator<Monom> currentPlace2= iteretor();
		Monom monom1,monom2;
		if(this.isZero()) {
			if(p1.isZero()) {
				return true;
			}
			else {
				return false;
			}	
		}
		if(p1.isZero()) {
			if(this.isZero()) {
				return true;
			}
			else {
				return false;
			}	
		}
		while(currentPlace1.hasNext()&&currentPlace2.hasNext()) {
			monom1=currentPlace1.next();
			monom2=currentPlace2.next();
			if((currentPlace1.hasNext()&&!currentPlace2.hasNext())||(currentPlace2.hasNext()&&!currentPlace1.hasNext())) {return false;}
			if((monom1.get_coefficient()!=monom2.get_coefficient())||(monom1.get_power()!=monom2.get_power())) {
				return false;
			}
		}	
		return true;
	}

	/**
	 * check if all the monoms at the polynom are 0
	 * "(all polynoms are zero untill i see diffrent coefficient at any monom)"
	 * flag hold the answer if is zero or not
	 * monom used to check the coefficient element in the polynom
	 */
	@Override
	public boolean isZero() {
		Iterator<Monom> currentPlace= iteretor();
		if(currentPlace==null) {return true;}
		boolean flag=true;
		Monom monom;
		while(currentPlace.hasNext()) {
			monom=currentPlace.next();
			if(monom.get_coefficient()!=0) {
				flag=false;}
		}
		return flag;
	}
	/**
	 * give me one of the root, of the polynom
	 * when you get x0,x1/ left=x0 right=x1
	 * mid is the (left+right)/2 allways updated
	 * check if x=is negative or positive..if needed so swap x1<->x0
	 * left hold x0, right hold x1, temp used to swap left and right if needed
	 */
	@Override
	public double root(double x0, double x1, double eps) {
		double left=x0;
		double right=x1;
		if(left>0) {
			double temp=left;
			left=right;
			right=temp;
		}
		double mid=(left+right)/2;
		double n=(binlog((int)((right-left)/eps))+1);
		for(int i=0;i<n;i++) {
			mid=(left+right)/2;
			if(f(mid)==0) {
				return mid;
			}
			if(f(mid)<0) {
				if(f(left)<0) {
					left=mid;
				}
				if(f(left)>0)  {right=mid;}
			}
			if(f(mid)>0) {
				if(f(left)>0) {
					left=mid;
				}
				if(f(left)<0) {
					right=mid;
				}
			}
		}
		return mid;
	}
	/**
	 * take deep copy of the polynom
	 * return the deep copyed polynom
	 * theCopy will be the returned copy of the polynom
	 */
	@Override
	public Polynom_able copy() {
		Iterator<Monom> currentPlace= iteretor();
		Polynom theCopy = new Polynom();
		Monom monom;
		if(!currentPlace.hasNext()) {
			return theCopy;
		}
		while(currentPlace.hasNext()) {
			monom=currentPlace.next();
			theCopy.add(new Monom(monom.get_coefficient(),monom.get_power()));
		}
		return theCopy;
	}
	/**
	 * derivativePoly will hold the derivative of the polynom
	 * monom hold each monom that the func make derivative
	 * return me the derivative of the polynom
	 */
	@Override
	public Polynom_able derivative() {
		Iterator<Monom> currentPlace= iteretor();
		Polynom derivativePoly=new Polynom();
		Monom monom;
		while(currentPlace.hasNext()) {
			monom=currentPlace.next();
			derivativePoly.add(monom.derivative());
		}
		return derivativePoly;
	}
	/**
	 * a hold x0,b hold x1 
	 * sum=will hold the area value of the integral
	 * n hold the number of intervals
	 * to find the n (number of intervals) i learned in youtube at this link: https://www.youtube.com/watch?v=MlP_W-obuNg
	 * deltaX =(x1(end)- x(start) )/number of intervals (now deltax is the size of each interval)
	 * return the area value of the integral(ryman)
	 */
	@Override 
	public double area(double x0, double x1, double eps) {
		double a=x0;
		double b=x1;
		if((b-a)<0) {
			b=x0;
			a=x1;
		}
		double sum=0;
		double n=((b-a)*(f(b)-f(a)))/eps;
		double deltaX=(b-a)/n;
		for(int i=0;i<n;i++) {
		
			sum=sum+f(a)*deltaX;
			a=a+deltaX;	
			
		}
		return sum;
	}
	/**
	 * 
	 * @param x0=hold the left x
	 * @param x1 =hold the right x (if x0 > x1 swap from comftable using)
	 * @param eps=diffrence between main answer to my answer + -
	 * @return the area of the function under the X 
	 */
	public double areaUnderX(double x0, double x1, double eps) {
		double a=x0;
		double b=x1;
		if((b-a)<0) {
			b=x0;
			a=x1;
		}
		double sum=0;
		double n=((b-a)*(f(b)-f(a)))/eps;
		double deltaX=(b-a)/n;
		for(int i=0;i<n;i++) {
			if(f(a)<0) {
			sum=sum+f(a)*deltaX;
			}
			a=a+deltaX;			
		}
		if(sum<0) {System.out.println("sum under x is - so sum*(-1)");
		sum=sum*(-1);}
		return sum;
	}
	/*
	 * 
	 * @see myMath.Polynom_able#iteretor()
	 */
	@Override
	public Iterator<Monom> iteretor() {
		return polynom.iterator();

	}

}
