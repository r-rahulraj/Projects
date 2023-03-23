import java.util.*;

public class Main {
	public static int student_id=3;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		while(true) {
			System.out.println("1) Student Login\n2) Admin Login\n3) Exit");
			int option=sc.nextInt();
			switch(option) {
				case 1:
					student_login();
					break;
				case 2:
					admin_login();
					break;
				case 3:
					return;
				default:
					System.out.println("Enter correct input");
					break;
			}
		}
	}

	private static void admin_login() {
		Scanner sc=new Scanner(System.in);
		Database db=new Database();
		System.out.println("Enter admin id:");
		int id=sc.nextInt();
		System.out.println("Enter password:");
		String password=sc.next();
		String original_password=db.retrieve_password_admin(id);
		if(password.equals(original_password)) {
			admin_dashboard(id);
		} else {
			System.out.println("User doesn't exist or wrong password");
		}
	}

	private static void admin_dashboard(int id) {
		 Scanner sc=new Scanner(System.in);
		 Database db=new Database();
		 label:
	     while(true) {
			 System.out.println("1) Create Student\n2) Delete Student\n3) Mostly expected company\n4) Logout");
			 int option=sc.nextInt();
			 switch(option) {
			 	case 1:
			 		db.insert();
			 		break;
			 	case 2:
			 		db.delete();
			 		break;
			 	case 3:
			 		db.mostly_expected_company();
			 		break;
			 	case 4:
			 		System.out.println("Logout successfull");
			 		break label;
			 }
		 }
	}
	
	private static void student_login() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter roll number:");
		int roll_no=sc.nextInt();
		System.out.println("Enter password:");
		String password=sc.next();
		Database db=new Database();
		String original_password=db.retrieve_password_student(roll_no);
		if(password.equals(original_password)) {
			student_dashboard(roll_no);
		} else {
			System.out.println("User doesn't exist or wrong password");
		}
	}

	private static void student_dashboard(int roll_no) {
		Scanner sc=new Scanner(System.in);
		label:
		while(true) {
			System.out.println("1) View details\n2) Update details\n3) Logout");
			int option=sc.nextInt();
			switch(option) {
				case 1:
					Database db=new Database();
					db.view_details(roll_no);
					break;
				case 2:
					update_details(roll_no);
					break;
				case 3:
					break label;
			}
		}
	}

	private static void update_details(int roll_no) {
		Scanner sc=new Scanner(System.in);
		Database db=new Database();
		label:
		while(true) {
			System.out.println("1) Change mobile number\n2) Change address\n3) Change password\n4) Cancel");
			int option=sc.nextInt();
			switch(option) {
				case 1 :
					System.out.println("Enter new mobile number : ");
					String number=sc.next();
					db.update(roll_no,"mobile_no",number);
					break;
				case 2:
					System.out.println("Enter new address : ");
					sc.nextLine();
					String address=sc.nextLine();
					db.update(roll_no,"address",address);
					break;
				case 3:
					System.out.println("Enter new password : ");
					String pass=sc.next();
					db.update(roll_no,"password",pass);
					break;
				case 4:
					break label;
			}
		}
	}
	
}
