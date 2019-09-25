import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EmployeePredicateExample {

	   private Integer id;
	   private Integer age;
	   private String gender;
	   private String firstName;
	   private String lastName;
	
	   
	
	@Override
	public String toString() {
		return "EmployeePredicateExample [id=" + id + ", age=" + age + "]";
	}
	public EmployeePredicateExample(Integer id, Integer age, String gender, String firstName, String lastName) {
		super();
		this.id = id;
		this.age = age;
		this.gender = gender;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	//All Employees who are male and age more than 21
	public static Predicate<EmployeePredicateExample> isAdultMale() {
	    return p -> p.getAge() > 21 && p.getGender().equalsIgnoreCase("M");
	}
	//All Employees who are female and age more than 18
	public static Predicate<EmployeePredicateExample> isAdultFemale() {
	    return p -> p.getAge() > 18 && p.getGender().equalsIgnoreCase("F");
	}
	//All Employees whose age is more than a given age
	public static Predicate<EmployeePredicateExample> isAgeMoreThan(Integer age) {
	    return p -> p.getAge() > age;
	}
	
	 public static List<EmployeePredicateExample> filterEmployees (List<EmployeePredicateExample> employees, Predicate<EmployeePredicateExample> predicate) 
	 {
	    return employees.stream().filter( predicate ).collect(Collectors.<EmployeePredicateExample>toList());
	 }
	 
	 public static void main(String[] args){
		 EmployeePredicateExample e1 = new EmployeePredicateExample(1,23,"M","Rick","Beethovan");
		 EmployeePredicateExample e2 = new EmployeePredicateExample(2,13,"F","Martina","Hengis");
		 EmployeePredicateExample e3 = new EmployeePredicateExample(3,43,"M","Ricky","Martin");
		 EmployeePredicateExample e4 = new EmployeePredicateExample(4,26,"M","Jon","Lowman");
		 EmployeePredicateExample e5 = new EmployeePredicateExample(5,19,"F","Cristine","Maria");
		 EmployeePredicateExample e6 = new EmployeePredicateExample(6,15,"M","David","Feezor");
		 EmployeePredicateExample e7 = new EmployeePredicateExample(7,68,"F","Melissa","Roy");
		 EmployeePredicateExample e8 = new EmployeePredicateExample(8,79,"M","Alex","Gussin");
		 EmployeePredicateExample e9 = new EmployeePredicateExample(9,15,"F","Neetu","Singh");
		 EmployeePredicateExample e10 = new EmployeePredicateExample(10,45,"M","Naveen","Jain");
	         
	        List<EmployeePredicateExample> employees = new ArrayList<EmployeePredicateExample>();
	        employees.addAll(Arrays.asList(new EmployeePredicateExample[]{e1,e2,e3,e4,e5,e6,e7,e8,e9,e10}));
	                
	        System.out.println(filterEmployees(employees, isAdultMale()));
	         
	        System.out.println(filterEmployees(employees, isAdultFemale()));
	         
	        System.out.println(filterEmployees(employees, isAgeMoreThan(35)));
	         
	        //Employees other than above collection of "isAgeMoreThan(35)" can be get using negate()
	        System.out.println(filterEmployees(employees, isAgeMoreThan(35).negate()));
	    }
}
