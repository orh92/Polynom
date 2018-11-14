package myMath;
import static org.junit.Assert.*;
import org.junit.Test;
public class JunitTest {
	//test f(x)
	@Test
	public void testFx() {
		//polynom fx and monom fx
		//monom fx test:
		Polynom p1=new Polynom();
		Monom m1=new Monom(3,2);
		p1.add(m1);//p1 hold only monom..so fx p1=fx m1
		double result =p1.f(2);//3*2^2=12
		assertTrue("its 12",result==12);
		//another monom fx test
		double result2 =p1.f(-3);//3*(-3)^2=need to be 27
		assertTrue("its 27",result2==27);
		//polynom fx test
		Polynom p2=new Polynom();
		Monom m2=new Monom(-0.5,3);//-0.5*x^3
		Monom m3=new Monom(16,1);//16*x^1
		p2.add(m2);
		p2.add(m3);
		double results =p2.f(-2);
		//p2.f(-2) need to be -28
		assertTrue("its -28",results==-28);
	}

	@Test
	public void testAddMonom() {
		Monom m1=new Monom(2,2); 
		Monom m2=new Monom(-3,2);
		Monom m3=new Monom(-1,2);//monom3=-1*x^2
		Monom m4=new Monom(-1,3);//monom3=-1*x^3
		m1.add(m2);//m1+m2=-1*x^2
		assertTrue("check power,coefficient of (after m1 mult with m2)|m3 ",(m1.get_coefficient()==m3.get_coefficient())&&(m1.get_power()==m3.get_power()));
		assertFalse("check power,coefficient of m1,m4 (after m1 mult with m2)",(m1.get_coefficient()==m4.get_coefficient())&&(m1.get_power()==m4.get_power()));
	}

	@Test
	public void testAddMonomToPolynom() {
		Polynom p =new Polynom();
		Monom m1=new Monom(1,2);//1*x^2
		Monom m2=new Monom(1,2);//1*x^2
		Monom m3=new Monom(-3.4,7);//-3.4*x^7
		p.add(m1);
		p.add(m2);
		p.add(m3);
		assertTrue(p.toString().equals("-3.4*x^7+2.0*x^2"));
	}

	@Test
	public void testAddPolynomGettingP1() {
		Polynom p =new Polynom();
		Polynom p2 =new Polynom();
		Monom m1=new Monom(3,2);
		Monom m2=new Monom(6,3);
		Monom m3=new Monom(-6,3);
		Monom m4=new Monom(3,2);
		Monom m5=new Monom(-0.5,7);
		p.add(m1);
		p.add(m2);
		p.add(m5);
		p2.add(m3);
		p2.add(m4);
		p.add(p2);
		//3x^2+6x63-6x^3+3x^2-0.5x^7= -0.5x^7+6x^2
		assertTrue(p.toString().equals("-0.5*x^7+6.0*x^2"));
	}

	@Test
	public void testDerivativeMonom() {
		Monom m1=new Monom(-3,4);
		assertTrue("need to be -12.0*x^3",m1.derivative().toString().equals("-12.0*x^3"));
		Monom m2=new Monom(0.5,3);
		assertTrue("need to be 1.5*x^2",m2.derivative().toString().equals("1.5*x^2"));

	}

	@Test
	public void testDerivativePolynom() {
		Polynom p =new Polynom();
		Monom m1=new Monom(-1,5);
		Monom m2=new Monom(-6,1);
		Monom m3=new Monom(-2,3);
		p.add(m1);
		p.add(m2);
		p.add(m3);
		assertTrue("the derivative is:-5.0*x^4-6.0*x^2-6.0*x^0",p.derivative().toString().equals("-5.0*x^4-6.0*x^2-6.0*x^0"));
	}


	@Test
	public void TestRoot() {
		Polynom p1 = new Polynom();
		p1.add(new Monom(1,2)); //x^2
		p1.add(new Monom(-7,1)); //x
		p1.add(new Monom(12,0)); //-1
		//i do 1 test example here but i did maney tests with symbolab comparation and its work
		assertTrue("answers is 4,3 with 0.01",p1.root(-1, 4, 0.01)==2.994140625);
		//tested in symbolab(from google)
	}

	@Test
	//tested maney examples in Symbolab all work
	//but i show here 1example
	public void TestArea() {
		Polynom p1 = new Polynom();
		p1.add(new Monom(3,3)); //x^2
		p1.add(new Monom(2,2));//-7x	
		assertTrue((p1.area(0, 10, 0.01))==8166.661666878907);
		//tested in Symbolab(in google)
	}

	@Test
	public void TestMultiplyMonom() {
		//mult m1 with m2
		//the mult value will be saved in m1
		Monom m1=new Monom(12,1);
		Monom m2=new Monom(-4,1);
		m1.multiply(m2);
		assertTrue(m1.toString().equals("-48.0*x^2"));
		//mult m3 with m4
		//the mult value will be saved in m3
		Monom m3=new Monom(0.5,4);
		Monom m4=new Monom(0.7,3);
		m3.multiply(m4);
		assertTrue(m3.toString().equals("0.35*x^7"));
	}

	@Test
	public void TestMultiplyPolynom() {
		//mult polynom p1 with polynom p2 and save data in polynom p1
		Polynom p1=new Polynom ();
		Polynom p2=new Polynom ();
		p1.add(new Monom(-2,2)); 
		p1.add(new Monom(2,1));
		p2.add(new Monom(3,2));
		p2.add(new Monom(4,1));
		p1.multiply(p2);
		assertTrue("p1 after mult in p2 is:-6.0*x^4-2.0*x^3+8.0*x^2",p1.toString().equals("-6.0*x^4-2.0*x^3+8.0*x^2"));
	}
	@Test
	public void Testsubstract() {
		Polynom p1=new Polynom ();//p1 is null
		Polynom p2=new Polynom ();//p2 is null
		p1.substract(p2);
		//test if i substruct null polynom
		assertTrue("null p1 - null p2=is zero",p1.isZero());
		//creat p1 and p2 and substruct
		p1.add(new Monom(1,5)); 
		p1.add(new Monom(3,7));
		p2.add(new Monom(3,2));
		p2.add(new Monom(11,5));
		p2.add(new Monom(-14.7,7));
		p1.substract(p2);
		//p1 after substruct is= 17.7*x^7-10.0*x^5-3.0*x^2
		assertTrue("p1 after substruct p2 is:17.7*x^7-10.0*x^5-3.0*x^2",p1.toString().equals("17.7*x^7-10.0*x^5-3.0*x^2"));
	}

	@Test
	public void TestTostringPolynom() {
		//creat polynom and add few monoms and send it to string 
		Polynom p1=new Polynom ();
		p1.add(new Monom(11,5)); 
		p1.add(new Monom(3.2,7));
		p1.add(new Monom(3,2));
		p1.add(new Monom(11,5));
		p1.add(new Monom(-14.7,7));
		assertTrue("the string of p1 is -11.5*x^7+22.0*x^5+3.0*x^2",p1.toString().equals("-11.5*x^7+22.0*x^5+3.0*x^2"));
	}

	@Test
	public void TestMonomTostring() {
		Monom m1=new Monom(-18.772,7);
		assertTrue("the monom is:-18.772*x^7",m1.toString().equals("-18.772*x^7"));	
	}
	@Test
	public void TestisZeroPolynom() {
		Polynom p=new Polynom();
		assertTrue("p is zero",p.isZero());
		Polynom p1=new Polynom();
		p1.add(new Monom(2,4));//p1 is not zero
		assertFalse("p1 is not zero",p1.isZero());
	}
	@Test
	public void TestifPolynomsEqual() {
		Polynom p3=new Polynom();
		Polynom p4=new Polynom();
		p3.add(new Monom(3,3));
		p3.add(new Monom(3,2));
		p4.add(new Monom(3,3));
		p4.add(new Monom(3,2));
		assertTrue(p3.equals(p4));//p3 equals to p4
		p4.add(new Monom(4,1));//add p4 new monom and now they not equal
		assertFalse(p3.equals(p4));//p3 not equal to p4
	}
	@Test
	public void TestPolynomCopy() {
		Polynom p1=new Polynom();
		p1.add(new Monom(-2,7));
		p1.add(new Monom(2,2));
		p1.add(new Monom(-2.3,1));
		p1.add(new Monom(1,0));
		//check if copy of p1 in string equals to the main p1 polynom
		assertTrue("p1 copy is:-2.0*x^7+2.0*x^2-2.3*x^1+1.0*x^0",p1.copy().toString().equals("-2.0*x^7+2.0*x^2-2.3*x^1+1.0*x^0"));
	}
	@Test
	public void TestisZero() {
		Polynom p=new Polynom();
		assertTrue("now p is zero ",p.isZero());
		p.add(new Monom(2,2));
		assertFalse("now p is not zero",p.isZero());
	}
	@Test
	public void TestConstractorPolynomNull() {
		Polynom p1=new Polynom();//creat null polynom
		assertTrue("constructor dont do anything only creat polynom null",p1.isZero());
	}
	@Test
	public void TestconstructorGettingPolynom() {
		//creat polynom p and after creat polynom p2 like polynom p
		Polynom p=new Polynom();
		p.add(new Monom(2,6));
		p.add(new Monom(-2,5));
		p.add(new Monom(2,4));
		p.add(new Monom(-2,3));
		Polynom p2=new Polynom(p);
		assertTrue(p2.toString().equals("2.0*x^6-2.0*x^5+2.0*x^4-2.0*x^3"));
	}

	@Test
	public void testPolynomConstructorString() {
		//get string into polynom constructor and build polynom with this string
		String str="0.2x^4-1.5x^3+3.0x^2-x-5";
		Polynom p = new Polynom(str);
		Polynom p2 = new Polynom();
		p2.add(new Monom(0.2,4));
		p2.add(new Monom(-1.5,3));
		p2.add(new Monom(3,2));
		p2.add(new Monom(-1,1));
		p2.add(new Monom(-5,0));
		assertTrue("Should be equal, the polynom generated manualy and by string", p.toString().equals(p2.toString()));
	}

}