package myMath;

import java.util.Comparator;

public class Monom_Comperator implements Comparator<Monom> {

	@Override
	public int compare(Monom FirstMonom, Monom SecondMonom) {

		return   SecondMonom.get_power()-FirstMonom.get_power();
	}


}
