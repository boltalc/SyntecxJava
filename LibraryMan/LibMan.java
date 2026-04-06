import java.util.*;

class Book{
    int id;
    String title;
    String author;

    Book(int id, String title, String author){
	this.id = id;
	this.title = title;
	this.author = author;
    }

    public String toString(){
	return id + " - " + title + " by " + author;
    }
}

public class LibMan{
    static ArrayList<Book> books = new ArrayList<>();
    static Scanner in = new Scanner(System.in);

    public static void addBook(){
	System.out.print("Enter Book ID: ");
	int id = in.nextInt(); in.nextLine();
	System.out.print("Enter Title: ");
	String title = in.next();
	System.out.print("Enter Author: ");
	String author = in.next();
	books.add(new Book(id, title, author));
	System.out.printf("✓ Book Added Succesfully!%n");
    }

    public static void viewBooks(){
	if (books.isEmpty()){
	    System.out.printf("%nNo Books available%n");
	}
	else{
	    books.forEach(System.out::println);
	}
    }

    public static void deleteBook(){
	System.out.printf("Enter Book Id to remove: ");
	int id = in.nextInt();
	books.removeIf(b -> b.id == id);
	System.out.printf("✓ Book Deleted Successfully%n");
    }

    public static void searchBook(){
	System.out.print("Enter Title or Author: ");
	String key = in.next();
	books.stream().filter(b -> b.title.contains(key) || b.author.contains(key)).forEach(System.out::println);
    }

    public static void main(String[] args){
	int input;
	do{
	    System.out.printf("1. Add%n2. View%n3. Remove%n4. Search%n5. Exit%n");
	    input = in.nextInt();
	    if(input == 1){
		addBook();
	    }
	    else if(input == 2){
		viewBooks();
	    }
	    else if(input == 3){
		deleteBook();
	    }
	    else if(input == 4){
		searchBook();
	    }
	    else if(input == 5 || input == 0){
		System.exit(0);
	    }
	}while(true);
    }
}
