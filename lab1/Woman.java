import java.util.*;

public class Woman {
	private String name;
	private Man partner;

	public Woman(String name){
		this.name = name;
		partner = null;
	}

	public void engage(Man m){
		partner = m;
	}

	public String toString(){
		return partner.getName() + " -- " + name;
	}

	public Man getPartner(){
		return partner;
	}
}