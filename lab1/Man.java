import java.util.*;

public class Man {
	private ArrayList<Integer> pref;
	private String name;
	private int id;
	private String partner;

	public Man(String name, int id){
		partner = "Forever Alone";
		this.name = name;
		this.id = id;
		pref = new ArrayList<Integer>();
	}

	public void addPref(int w){
		pref.add(w);
	}

	public int getId(){
		return id;
	}

	public String toString(){
		return name + " -- " + partner;
	}

	public int topPick(){
		if(!pref.isEmpty()){
		partner = pref.get(0).toString();
		 return pref.remove(0);	
		}
		return -1;
	}

	public boolean proposedToAll(){
 		return pref.isEmpty();
	}

	public void engage(String w){
		partner = w;
	}
}
