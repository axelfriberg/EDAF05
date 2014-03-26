import java.util.*;

public class StableMarriages{
	public static void main(String[] args){
		LinkedList<Man> freeMen = new LinkedList<>();
		int n = 3;
		Man ross = new Man("Ross",1);
		Woman monica = new Woman("Monica");
		Man chandler = new Man("Chandler", 3);
		Woman phoebe = new Woman("Phoebe");
		Man joey = new Man("Joey", 5);
		Woman rachel = new Woman("Rachel");
		
		ross.addPref(0, rachel);
		ross.addPref(1, phoebe);
		ross.addPref(2, monica);
		chandler.addPref(0, monica);
		chandler.addPref(1, rachel);
		chandler.addPref(2, phoebe);
		joey.addPref(0, rachel);
		joey.addPref(1, phoebe);
		joey.addPref(2, monica);
		freeMen.add(ross);
		freeMen.add(chandler);
		freeMen.add(joey);

		int[] monicaPref = new int[2*n];
		monicaPref[0] = 3;
		monicaPref[1] = 5;
		monicaPref[2] = 1;
		int[] phoebePref = new int[2*n];
		phoebePref[0] = 5;
		phoebePref[1] = 1;
		phoebePref[2] = 3;
		int[] rachelPref = new int[2*n];
		rachelPref[0] = 1;
		rachelPref[1] = 5;
		rachelPref[2] = 3;

		rachel.setPrefList(rachelPref);
		monica.setPrefList(monicaPref);
		phoebe.setPrefList(phoebePref);
		ManChooser manChooser = new ManChooser(freeMen);
		Man m = manChooser.chooseMan();


		while(!freeMen.isEmpty() && m != null){
			Woman w = m.topPick();

			if(w.getPartner() == null){
				w.engage(m);
				freeMen.remove(m);	
			} 
			else {
				if(w.prefers(m.getId())){
					freeMen.add(w.getPartner());
					w.engage(m);
					freeMen.remove(m);
				} 
			}
			manChooser = new ManChooser(freeMen);
			m = manChooser.chooseMan();
		}
		System.out.println(ross.toString());
		System.out.println(chandler.toString());
		System.out.println(joey.toString());
	}
}

