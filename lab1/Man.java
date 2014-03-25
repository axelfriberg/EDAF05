import java.util.*;

public class Man {
	private ArrayList<Woman> pref;
	private String name;

	public Man(String name){
		this.name = name;
	}

	public addPref(int index, Woman w){
		pref.add(index, w);
	}

	public String getName(){
		return name;
	}

	public Woman topPick(){
		return pref.get(0);
	}
}