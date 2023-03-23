import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;

public class Database {
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	
	public Database() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/social","root","rahulraj09092002");
			stmt=con.createStatement();
		} catch(Exception e) {
			System.out.print(e.toString());
		}
	}
	public void insert() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter student name : ");
		String name=sc.nextLine();
		System.out.println("Enter date of birth(YY-MM-DD) : ");
		String dob=sc.next();
		System.out.println("Enter department : ");
		String dept=sc.next();
		System.out.println("Enter SSLC mark : ");
		double sslc=sc.nextDouble();
		System.out.println("Enter HSC mark : ");
		double hsc=sc.nextDouble();
		System.out.println("Enter placement status(YES/NO) : ");
		String status=sc.next();
		System.out.println("Enter mobile number : ");
		String number=sc.next();
		System.out.println("Enter password : ");
		String pass=sc.next();
		System.out.println("Enter address : ");
		sc.nextLine();
		String address=sc.nextLine();
		System.out.println("Enter expected company : ");
		String company=sc.next();
		System.out.println("Enter expected salary : ");
		int salary=sc.nextInt();
		try {
			rs=stmt.executeQuery("select roll_no from student;");
			int r_no=0;
			while(rs.next()) {
				r_no=rs.getInt(1);
			}
			int student_id=r_no+1;
			String sql = "insert into student "+"values ("+student_id+",\""+name+"\",\""+dob+"\",\""+dept+"\","+sslc+","+hsc+",\""+status+"\",\""+number+"\",\""+pass+"\",\""+address+"\",\""+company+"\","+salary+");";
			stmt.executeUpdate(sql);
			System.out.println("Student created successfully");
		} catch(Exception e) {
			System.out.println("Enter proper values");
			return; 
		}
	}
	public void delete() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter student roll number :");
		int roll_no=sc.nextInt();
		try {
			String sql="delete from student where roll_no="+roll_no+";";
			stmt.executeUpdate(sql);
			System.out.println("Student details deleted successfully");
		} catch(Exception e) {
			System.out.println("Error occurred! Retry");
			return;
		}
	}
	
	public String retrieve_password_admin(int id) {
		try {
			rs=stmt.executeQuery("select password from admin where admin_id="+id+";");
			while(rs.next()) {
				String x=rs.getString("password");
				return x;
			}
		} catch(Exception e) {
			return null;
		}
		
		return null;
	}
	public void mostly_expected_company() {
		HashMap<String,Integer> map=new HashMap<>();
		try {
			rs=stmt.executeQuery("select expected_company from student;");
			while(rs.next()) {
				String company=rs.getString(1);
				if(!map.containsKey(company)) {
					map.put(company,1);
				} else {
					int val=map.get(company);
					map.put(company, val+1);
				}
			}
			int max=0;
			for(String i : map.keySet()) {
				int val=map.get(i);
				if(val>max) {
					max=val;
				}
			}
			for(String i : map.keySet()) {
				if(map.get(i)==max) {
					System.out.println(i);
				}
			}
		} catch(Exception e) {
			return;
		}
	}
	
	public String retrieve_password_student(int roll_no) {
		try {
			rs=stmt.executeQuery("select password from student where roll_no=\""+roll_no+"\";");
			while(rs.next()) {
				String x=rs.getString("password");
				return x;
			}
		} catch(Exception e) {
			return null;
		}
		
		return null;
	}
	
	public void update(int roll_no,String column,String value) {
		try {
			String sql="update student set "+column+"=\""+value+ "\"where roll_no=\""+roll_no+"\";";
			stmt.executeUpdate(sql);
			System.out.println("Successfully updated");
		} catch(Exception e) {
			return;
		}
	}
	
	public void view_details(int roll_no) {
		try {
			rs=stmt.executeQuery("select * from student where roll_no=\""+roll_no+"\";");
			while(rs.next()) {
				for(int i=1;i<=12;i++) {
					System.out.println(rs.getString(i));
				}
			}
		} catch(Exception e) {
			return;
		}
	}
	

}
