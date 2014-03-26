import java.util.*;

public class ManChooser{
	private LinkedList<Man> men;

	public ManChooser(LinkedList<Man> men){
		this.men = men;
	}

	public Man chooseMan(){
		for(Man man : men){
				if(!man.proposedToAll()){
					return man;
				}		
		}
		return null;
	}
}