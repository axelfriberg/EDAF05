import java.util.*;

public class Man {
	private ArrayList<Woman> pref;
	private String name;
	private int id;
	private String partner;

	public Man(String name, int id){
		partner = "Forever Alone";
		this.name = name;
		this.id = id;
		pref = new ArrayList<Woman>();
	}

	public void addPref(int index, Woman w){
		pref.add(index, w);
	}

	public int getId(){
		return id;
	}

	public String toString(){
		return name + " -- " + partner;
	}

	public Woman topPick(){
		if(!pref.isEmpty()){
		partner = pref.get(0).toString();
		 return pref.remove(0);	
		}
		return null;
	}

	public boolean proposedToAll(){
 		return pref.isEmpty();
	}
}
