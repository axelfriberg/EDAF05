import java.util.*;
import java.io.*;

public class Parser{
    private Scanner scanner;
    private int n;
    private LinkedList<Man> men;
    private HashMap<Integer, Woman> women;

    public Parser(String path){
        men = new LinkedList<>();
        women = new HashMap<>();
        try{
        scanner = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void parse(){
    	String info;
        while (scanner.hasNextLine()) {
            info = scanner.nextLine();
            if (info.charAt(0) == '#') {

            } else if(info.charAt(0) == 'n'){
                try{
                    String[] pairs =(info.split("="));
                    n = Integer.parseInt(pairs[1]);
                } catch(NumberFormatException ex){
                        System.out.println("Its not a valid Integer");
                }
    
            } else {
                String[] infoSplit;
                String[] infoSplit2;
                for (int i = 1; i <= 2*n; i++) {
                        infoSplit = info.split("\\s+");
                    if(i%2 == 1){
                        Man m = new Man(infoSplit[1], i);
                        men.add(m);
                    } else {
                        Woman w = new Woman(infoSplit[1]);
                        women.put(i,w);
                    }
                    info = scanner.nextLine();
                }

               	int index = 0;
                for(int j = 1; j <= 2*n; j++){
                	info = scanner.nextLine();
                	infoSplit = info.split(": ");
                	infoSplit2 = infoSplit[1].split(" ");
                	if(j%2 == 1){
                		for (int k = 0; k < infoSplit2.length; k++) {                			
                			men.get(index).addPref(Integer.parseInt(infoSplit2[k]));                		
                		}
                		index++;	
                	} else {
                		int[] prefArray = new int[n];

                		for (int k = 0; k < infoSplit2.length; k++) {  
                			 prefArray[k] = Integer.parseInt(infoSplit2[k]);             			
                		}
                	    women.get(j).setPrefList(prefArray);
                	}
           		}
           	}
        }
    }
    public LinkedList<Man> getMen(){
        return men;
    }

    public HashMap<Integer, Woman> getWomen(){
    	return women;
    }

    public int getPairAmount(){
        return n;
    }
}