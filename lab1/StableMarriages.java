import java.util.*;

public class StableMarriages{
	public static void main(String[] args){
		LinkedList freeMen = new LinkedList();
		int n = 3;
				Man ross = new Man("Ross");
		Woman monica = new Woman("Monica");
		Man chandler = new Man("Chandler");
		Woman phoebe = new Woman("Phoebe");
		Man joey = new Man("Joey");
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

		int[] monicaPref = new int[2*n];
		monicaPref = {3, 5, 1};
		int[] phoebePref = new int[2*n];
		phoebePref = {5, 1, 3};
		int[] rachelPref = new int[2*n];
		rachelPref = {1, 5, 3};

		rachelPref[1] = 0;
		rachelPref[5] = 1;

		while(!freeMen.isEmpty() && proposedToAll() != null){
			Man m = proposedToAll();
			Woman w = m.topPick();

			if(w.getPartner == null){
				w.engage(m);
				freeMen.remove(m);	
			} else {
				if(womenPref.indexOf()){

				} else {

				}

			}


		}
		
	}

	private Man proposedToAll(){
		for(Man m : freeMen){
			if(!m.proposedToAll()){
				return m;
			}		
		}	return null;
	}

	private int[] invert(int [] pref){
		int[] invPref = new int[pref.lenght];
		for(i=0; i<pref.lenght -1; i++){
			invPref[pref[i]] = i;
		}
	}
}


# Stable marriage instance based on NBC's show "Friends"
#
n=3
1 Ross
2 Monica
3 Chandler
4 Phoebe
5 Joey
6 Rachel

1: 6 4 2
2: 3 5 1
3: 2 6 4
4: 5 1 3
5: 6 4 2
6: 1 5 3
