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
