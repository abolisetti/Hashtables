
public class HashTest{
	public static void main(String[]args){
		HashTable<String> names = new HashTable<String>();
		names.add("Marissa");
		names.add("Jacob");
		names.add("Affan");
		names.add("Oguzhan");
		/*
		names.add("Banaenae");
		names.add("Spaghetti");
		names.add("poop");
		names.add("kek");
		names.add("poopoo");
		names.add("geygey");
		names.add("who dat");
		names.add("who dis");
		names.add("who dot");
		*/
		System.out.println(names);
		names.remove("who dot");
		names.setMaxLoad(.75);
		names.setLoad(0.5);
		
	}
}