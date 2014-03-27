import java.util.*;

public class Woman {
	private String name;
	private Man partner;
	private int[] pref;

	public Woman(String name){
		this.name = name;
		partner = null;
		pref = null;
	}

	public void engage(Man m){
		partner = m;
	}

	public String toString(){
		return name;
	}

	public Man getPartner(){
		return partner;
	}

	public void setPrefList(int[] list){
		int[] invPref = new int[list.length*2];
		for(int i=0; i<list.length; i++){
			invPref[list[i]] = i;
		}
		pref = invPref;
	}

	public boolean prefers(int i){
		if(pref[partner.getId()] < pref[i]){
			return false;
		}
		return true;
	}
}