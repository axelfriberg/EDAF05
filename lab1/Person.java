import java.util.*;

public class Person{
		private String name;
		private ArrayList<Integer> pref;
		private Person engagedTo;
		private int id;

		public Person (String name, int id, ArrayList<Integer> pref){
			this.name = name;
			this.pref = pref;
			this.id = id;
			engagedTo = null;
		}

		public String getName(){
			return name;
		}

		public int getId(){
			return id;
		}

		public boolean commitmentIssues(){
			return pref.isEmpty();
		}

		public int getFirstPref(){
			return pref.get(0);
		}
		public Person getPartner(){
			return engagedTo;
		}

		public void engageTo(Person p){
			engagedTo=p;
		}
		public void proposeTo(int id){
			pref.remove((Integer)id);
		}

		public boolean prefers(int id){
			if(pref.contains(id)){
				if(pref.indexOf(id) < pref.indexOf(engagedTo)){
					return true;
				}
			} 
				return false;
		}

		public String toString(){
			return name + " -- " + engagedTo.getName();
		}
	}