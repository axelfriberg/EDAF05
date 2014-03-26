import java.util.*;
import java.io.*;

public class Parser{
	private Scanner scanner;
	private int n;
	private LinkedList<Man> men;
	private LinkedList<Woman> women;

	public Parser(String path){
		men = new LinkedList<>();
		women = new LinkedList<>();
		try{
			scanner = new Scanner(new File(path));
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	}

	public void parse(){
			while (scanner.hasNextLine()) {
				if (scanner.next().equals("#")) {
					scanner.nextLine();
				}
				else if(scanner.next().equals("n=3")){
					System.out.println("kollar efter n");
					scanner.skip("=");
					try{
					    n = Integer.parseInt(scanner.next().charAt(0)); 				//Fix this
					}
					catch(NumberFormatException ex){
					    System.out.println("Its not a valid Integer");
					}
				} else {
					String info;
					String[] infoSplit;
					String[] infoSplit2;
						for (int i = 0; i < 2*n; i++) {
							if(i%2 != 0){
								info = scanner.nextLine();
								infoSplit = info.split("\\s+");
								Man m = new Man(infoSplit[1], Integer.parseInt(infoSplit[0]));
								men.add(m);
							} else {
								info = scanner.nextLine();
								infoSplit = info.split("\\s+"); 
								Woman w = new Woman(infoSplit[1]);
								women.add(Integer.parseInt(infoSplit[0]),w);
							}

						}
						scanner.nextLine();
						for(int j = 0; j < 2*n; j++){
							info = scanner.nextLine();
							infoSplit = info.split(":");
							infoSplit2 = infoSplit[1].split("\\s+");
							if(j%2 != 0){
								for(int k = 0; k < infoSplit2.length; k++){
									men.get(j).addPref(Integer.parseInt(infoSplit2[k]),women.get(Integer.parseInt(infoSplit2[k])));
								}
							}
							else{
								int[] prefList = new int[infoSplit2.length];
								for(int l = 0; l < infoSplit2.length; l++){
									prefList[l] = Integer.parseInt(infoSplit2[l]);
								}
								women.get(j).setPrefList(prefList);
							}
						}
			}
						
			
		}
	}
	public LinkedList<Man> getMen(){
		return men;
	}

	public int getPairAmount(){
			return n;
	}
}