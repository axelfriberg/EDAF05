import java.util.*;

public class StableMarriages{
	public static void main(String[] args){
		long time = System.currentTimeMillis();
		Parser parser = new Parser(args[0]);
		parser.parse();
		LinkedList<Man> freeMen = parser.getMen();
		LinkedList<Man> engagedMen = new LinkedList<>();
		HashMap<Integer, Woman> women = parser.getWomen();
		int n = parser.getPairAmount();
		ManChooser manChooser = new ManChooser(freeMen);
		Man m = manChooser.chooseMan();

		while(!freeMen.isEmpty() && m != null){
			int woman = m.topPick();
			Woman w = women.get(woman);

			if(w.getPartner() == null){
				w.engage(m);
				m.engage(w.toString());
				engagedMen.add(m);
				freeMen.remove(m);	
			} 
			else {
				if(w.prefers(m.getId())){
					freeMen.add(w.getPartner());
					w.getPartner().engage("Forever Alone");
					engagedMen.remove(w.getPartner());
					w.engage(m);
					m.engage(w.toString());
					engagedMen.add(m);
					freeMen.remove(m);
				} 
			}
			manChooser = new ManChooser(freeMen);
			m = manChooser.chooseMan();
		}
		
		for(Man man : engagedMen){
			System.out.println(man.toString());
		}
		time = System.currentTimeMillis() - time;
		System.out.println(time);
	}
}

