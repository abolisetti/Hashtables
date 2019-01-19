/*HashTable.java
 *Arvind Bolisetti
 *Make a hashtable with its basic functions and features
 *Make a really big list with lots of spots and each of the spots has a lnked list
 */
import java.util.*;
public class HashTable<T>{
	//Use type T so it can be anything
	private ArrayList<LinkedList<T>> vals;
	private int size; // # of elements
	private double maxLoad = 0.6;//starting load
	 
	public HashTable(){
		vals = blank(10);//when u first start u use 10 spots
		size = 0;
	}
	//function to make a an arrayList thats really big with n # of spots
	public ArrayList<LinkedList<T>> blank(int n){
		ArrayList<LinkedList<T>> temp = new ArrayList<LinkedList<T>>();
		for(int i = 0; i<n; i++){
			temp.add(null);
		}
		return temp;
	}
	//Add vals into thingy
	//get code, depending on size of hashtable, u sort into into its spot
	//if theres stuff alr there u make it into linked list
	//if load gets too big, then u resize
	//make sure to keep track of size
	public void add(T val){
		int code = Math.abs(val.hashCode());
		int spot = code%vals.size();
		if(vals.get(spot) == null){
			vals.set(spot, new LinkedList<T>());
		}
		vals.get(spot).add(val);
		size+=1;
		if(getLoad()>maxLoad){
			resize(vals.size()*10);
		}
		
	}
	//resize is makes a new list, a bigger a list
	//and iterates through al values and adds them into 
	//new list
	public void resize(int currSpots){
		ArrayList<LinkedList<T>> oldList = vals;
		vals = blank(currSpots);
		
		for(LinkedList<T> lst: oldList){
			if(lst!=null){
				for(T v:lst){
					add(v);
				}
			}
		}
	}

	//remove finds value, and delets it.
	//should be O(1) time, but i had a lot of 
	//checky things so that it desnt crash if its not there
	
	public void remove(T val){
		int spot = Math.abs(val.hashCode());
		spot = spot%vals.size();
		if(vals.get(spot)!=null){
			if(vals.get(spot).indexOf(val)!=-1){
				vals.get(spot).remove(val);
				if(vals.get(spot).size()==0){
					vals.set(spot, null);
				}
				size--;//make sure to make size smaller
			}
		}
	}
	//return current load
	public double getLoad(){
		return (double)size/(double)vals.size();
	}
	//u can set the max load, at start its 0.6
	//min max stuff r just to make sure its in bonnds
	public void setMaxLoad(double l){
		if(l>0.8){
			maxLoad = Math.min(0.8, l);
		}
		else if(l<0.1){
			maxLoad = Math.max(0.1, l);
		}
		else{
			maxLoad = l;
		}
	}
	//basically remove but instead of deleting,
	//just look if its there.
	//in hindsight i couldve used contains in
	//remove but i did remove first
	//make sure ur not searching in a null cuz thats gives nullpointer
	public boolean contains(T val){
		int spot = Math.abs(val.hashCode());
		spot = spot%vals.size();
		if(vals.get(spot)!=null){
			if(vals.get(spot).contains(val)){
				return true;
			}
		}
		return false;
		
		
	}
	//when u set load, u resize it to whatever is appropriate
	//make sur its in whatever rnage it neesds to b in
	//rearange load = sizee/spots for new spots
	public void setLoad(double l){
		l = Math.min(l, maxLoad);
		l = Math.max(l, 0.1);
		resize((int)(size/l));
	}
	//basically toString for an array
	public ArrayList<T> toArray(){
		ArrayList<T> ans = new ArrayList<T>();
		
		for(LinkedList<T> lst: vals){
			if(lst!=null){
				for(T v:lst){
					ans.add(v);
				}
			}
		}
		return ans;
		
	}
	
	@Override
	//overloading system method for toString so it could print
	public String toString(){
		String ans = "* ";
		for(LinkedList<T> lst: vals){
			if(lst!=null){
				for(T v:lst){
					ans+=v + ", ";
				}
			}
		}
		if(ans!="* "){
			ans = ans.substring(0, ans.length()-2);
		}
		ans+=" *";
		return ans;
	}
	
}


//#################################################################//
/*HashAssign1.java
 *Arvind Bolisetti
 *its like cheat codes for scrabble
 *u enter 8 letter word and it give out all permutations that
 *r real words
 */
import java.io.*;
import java.util.*;

public class HashAssign1{
	private static HashTable<String> dictionary = new HashTable<String>();
	//global hashtable so i can access it in recursion
	public static void main(String[]args){
		
		getDict();//makes dict
		permutations(userWord());//permuations runs the input
	}
	public static void permutations(String word){
		permutations("", word);
	}
	//recursive func to get all permuations of a string of letters
	public static void permutations(String picked, String left){
		if(left.equals("")){
			if(dictionary.contains(picked)){
				//remove for duplicates cuz some letters repeat
				//word is in dictionary, print it
				System.out.println(picked);
				dictionary.remove(picked);
			}			
		}
		else{
			//loop gets all possible combinations
			for(int i = 0;i < left.length();i++){
				char ch = left.charAt(i);
				String newLeft = left.substring(0,i) + left.substring(i + 1);
				permutations(picked + ch, newLeft);
			}
		}
	}
	//cleaner way of getting input from user
	public static String userWord(){
		System.out.println("etnre word: ");
		Scanner kb = new Scanner(System.in);
		String letters = kb.nextLine();

		kb.close();	
		return letters;
	}
	//making the dict file into a hashTable dictionary
	public static void getDict() {
		Scanner infile = null; 
		try {
			infile = new Scanner(new File("dictionary.txt")); 
		}
		catch (IOException ex) {
			System.out.println(ex); 
		}
		
		int words = 84219;//amount of words i was told was in the list
		
		for (int i = 0; i < words; i++) {
			String word = (infile.nextLine());
			dictionary.add(word);
		}
	}
}
