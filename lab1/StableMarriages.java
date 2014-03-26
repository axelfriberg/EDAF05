import java.util.*;

public class StableMarriages{
	public static void main(String[] args){
		Parser parser = new Parser("sm-friends.in");
		parser.parse();
		LinkedList<Man> freeMen = parser.getMen();
		LinkedList<Man> engagedMen = new LinkedList<>();
		int n = parser.getPairAmount();
		ManChooser manChooser = new ManChooser(freeMen);
		Man m = manChooser.chooseMan();

		for(Man man : freeMen){
			System.out.println(man.toString());
		}


		while(!freeMen.isEmpty() && m != null){
			Woman w = m.topPick();

			if(w.getPartner() == null){
				w.engage(m);
				engagedMen.add(m);
				freeMen.remove(m);	
			} 
			else {
				if(w.prefers(m.getId())){
					freeMen.add(w.getPartner());
					engagedMen.remove(w.getPartner());
					w.engage(m);
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
	}
}

