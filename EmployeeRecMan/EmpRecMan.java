import java.util.*;
public class EmpRecMan{
    public static void main(String args[]){
	ArrayList<Employee> list = new ArrayList<Employee>();
	Scanner in = new Scanner(System.in);
	int prompt;
	int ID;
	String name;
	int salary;
	do{
	    System.out.printf("1.ADD%n2.VIEW%n3.UPDATE%n4.DELETE%nSelect an option: ");
	    prompt = in.nextInt();
	    if(prompt == 1){
		System.out.print("Enter employee ID: ");
		ID = in.nextInt();
		System.out.print("Enter employee name: ");
		name = in.next();
		System.out.print("Enter employee salary: ");
		salary = in.nextInt();
		list.add(new Employee(ID,name,salary));
	    }
	    else if(prompt == 2){
		System.out.println(list);
	    }
	    else if(prompt == 3){
		boolean seen = false;
		System.out.print("Enter ID to update: ");
		ListIterator<Employee> li = list.listIterator();
		ID = in.nextInt();
		while(li.hasNext()){
		    Employee l = li.next();
		    if(l.getID() == ID){
			System.out.print("Enter new name: ");
			name = in.next();
			System.out.print("Enter new Salary: ");
			salary = in.nextInt();
			li.set(new Employee(ID,name,salary));
			seen = true;
		    }
		}
		if(!seen){
		    System.out.println("Record not found");
		}
		else{
		    System.out.println("Record updated successfully");
		}
	    }
	    else if(prompt == 4){
		boolean seen = false;
		System.out.print("Enter ID to delete: ");
		Iterator<Employee> it = list.iterator();
		ID = in.nextInt();
		while(it.hasNext()){
		    Employee l = it.next();
		    if(l.getID() == ID){
			it.remove();
			seen = true;
		    }
		}
		if(!seen){
		    System.out.println("Record not found");
		}
		else{
		    System.out.println("Record deleted succesfully");
		}
	    }
	}while(prompt != 0);
	
    }
}

public class Employee{
    private String name;
    private int ID;
    private int salary;

    Employee(int ID, String name, int salary){
	this.ID = ID;
	this.name = name;
	this.salary = salary;
    }

    public int getID(){
	return ID;
    }
    public int getSalary(){
	return salary;
    }
    public String getName(){
	return name;
    }
    public String toString(){
	return ID + " " + name + " " + salary;
    }
}
