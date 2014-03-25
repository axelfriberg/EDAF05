import java.util.*;

public class StableMarriages{
	public static void main(String[] args){
		HashMap<Integer, Person> map = new HashMap<>();

		ArrayList<Integer> prefRoss = new ArrayList<>();
		ArrayList<Integer> prefMonica = new ArrayList<>();
		ArrayList<Integer> prefChandler = new ArrayList<>();
		ArrayList<Integer> prefPhoebe = new ArrayList<>();
		ArrayList<Integer> prefJoey = new ArrayList<>();
		ArrayList<Integer> prefRachel = new ArrayList<>();
		prefRoss.add(6);
		prefRoss.add(4);
		prefRoss.add(2);
		prefMonica.add(3);
		prefMonica.add(5);
		prefMonica.add(1);
		prefChandler.add(2);
		prefChandler.add(6);
		prefChandler.add(4);
		prefPhoebe.add(5);
		prefPhoebe.add(1);
		prefPhoebe.add(3);
		prefJoey.add(6);
		prefJoey.add(4);
		prefJoey.add(2);
		prefRachel.add(1);
		prefRachel.add(5);
		prefRachel.add(3);
		
		Person ross = new Person("Ross", 1, prefRoss);
		Person monica =new Person("Monica", 2, prefMonica);
		Person chandler = new Person("Chandler", 3, prefChandler);
		Person phoebe = new Person("Phoebe", 4, prefPhoebe);
		Person joey = new Person("Joey", 5, prefJoey);
		Person rachel = new Person("Rachel", 6, prefRachel);

		map.put(1, ross);
		map.put(2, monica);
		map.put(3, chandler);
		map.put(4, phoebe);
		map.put(5, joey);
		map.put(6, rachel);

		Person m = map.get(1);

		int marriages = 0;

		while (marriages < 3){	
			Person w = map.get(m.getFirstPref());
			m.proposeTo(m.getFirstPref());

			if(w.getPartner()==null){
				m.engageTo(w);
				w.engageTo(m);
				marriages++;
			}
			else {
				if(w.prefers(m.getId())){
					w.getPartner().engageTo(null);
					w.engageTo(m);
					m.engageTo(w);
				}
			}
		
			for(int i = 1; i < map.size(); i++){
				i++;
				Person man = map.get(i);
				if(!man.commitmentIssues() && man.getPartner()==null){
					m = man;
				}
			}
		}

		System.out.println(map.get(1).toString());
		System.out.println(map.get(3).toString());
		System.out.println(map.get(5).toString());	
	}
}