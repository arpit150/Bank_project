import java.util.Scanner;
import java.sql.*;

public class Bank{
	
    Scanner sc= new Scanner(System.in);
	int ac,puskal=0;
	
	 void accno() throws Exception{
		System.out.print("\t\tEnter Account Number: ");
		ac=sc.nextInt();
		Connection con= new Connectionis().connect();
		Statement st= con.createStatement();
		ResultSet rs=st.executeQuery("select * from customer");
		while(rs.next()) {
			int a= rs.getInt(1);
			if(ac==a)
			{
				puskal=1;
				break;
			}
	 	}
		if(puskal==1) {}
		else
		{
			System.out.println("\taccount not register");
			Mainclass.main(null);
		}
	}


	
      void press() throws Exception {
		   System.out.print("\n\n\t\t\tpress ");
		   Thread.sleep(100);
		   System.out.print("any ");
		   Thread.sleep(100); System.out.print("key ");Thread.sleep(100);System.out.print("to ");Thread.sleep(100);System.out.print("Continue ");Thread.sleep(100);System.out.print("\t\t\t\t\t\t\t    Copyright: ");System.out.print("PUSKAL");
	       Thread.sleep(100);System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t    © All right Reserved:");Thread.sleep(100); System.out.print("Texas");Thread.sleep(100); System.out.print(" Imaginology");
		   char ch=sc.next().charAt(0);
		}
	
	
	
	void menu1() throws Exception{
		  int ch2;
		    do {
		    	System.out.println("\n\n\n\t\t\t\t\t\t~~~~~~~~~~~~~~~~~ \n\t\t\t\t\t\t|      Bank\t|");
		    	System.out.println("\t\t\t\t\t^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		    	System.out.println("\t\t\t\t\t:\t1.create account\t:\n\t\t\t\t\t:\t2.Account Detail\t:\n\t\t\t\t\t:\t3.Deposit\t\t:\n\t\t\t\t\t:\t4.Go to Home\t\t:");
		    	System.out.println("\t\t\t\t\t^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		    	System.out.print("\t  Press your choice: ");
		    	ch2=sc.nextInt();
		    	System.out.println("\n\n");
			   switch(ch2) {
			      case 1:
			        	createAcc();
			            break;
			    case 2:
				       executeAcc();
				       break;
			   case 3:
				     deposit();
				     break;
		       }
		}while(ch2<4);
	}
	
	
	
	
	  void createAcc() throws Exception {
    	    Scanner sc= new Scanner(System.in);
    	    System.out.println("\n\t- - - - - - - - - - - -\n\t     Fill the form\n\t- - - - - - - - - - - - ");
    	    System.out.print("\t   Name:    ");
    	    String nm=sc.nextLine();
    	    System.out.print("\t   Gender:  ");
    	    String g=sc.nextLine();
    	    System.out.print("\t   Address: ");
    	    String ad=sc.nextLine();
    	    System.out.print("\t   Phone:   ");
    	    String ph=sc.nextLine();
    	    System.out.println("\n\t   Enter 4 digit secure pin");
    	    System.out.print("\t!!!!!!!!! Be Aware!! please dont share this pin to anyone:    ");
    	    int pi=sc.nextInt();
    	    sc.nextLine();
    	    Connection con=new Connectionis().connect();
    	    PreparedStatement ps= con.prepareStatement("insert into customer (pin,Name,gender,Address,Ph_number)values(?,?,?,?,?)");
    	    ps.setInt(1, pi);
    	    ps.setString(2, nm);
    	    ps.setString(3, g);
    	    ps.setString(4, ad);
    	    ps.setString(5, ph);
            ps.executeUpdate();
    	    System.out.println("\n\t    Account register successfully");
    	    System.out.print("\t. . . . . . . . . . . . . . . . . . ."+"\n\t     your account number: ");
    	    Statement st=con.createStatement();
    	    ResultSet rs=st.executeQuery("select * from customer where pin="+pi+" && Ph_number="+ph+" && name='"+nm+"' ");
    	    rs.next();
    	    System.out.println(rs.getString(1)+"\n\t. . . . . . . . . . . . . . . . . . . ");
    	    press();
     }
     
	  
	  
	  void executeAcc() throws Exception{
            accno();
    	    Connection con= new Connectionis().connect();
    	    Statement s=con.createStatement();
    	    ResultSet rs=s.executeQuery("select * from customer where Account_number= "+ac+"");
    	 	rs.next();
    	 	String str= "\n\t     - - - - - - - - - - - - - - - \n\t\tName:    "+ rs.getString(3)+"\n\t\tGender:  "+rs.getString(4)+"\n\t\tAddress: "+rs.getString(5)+"\n\t\tPhone:   "+rs.getString(6)+"\n\t\tBalance: "+rs.getInt(7)+"\n\t     - - - - - - - - - - - - - - -\n\n\n";
    	 	System.out.println(str);
    	 	press();
    	 }
     
     
     
      void deposit() throws Exception {
    	   accno();
    	   Connection con=new Connectionis().connect();
    	   Scanner sc= new Scanner(System.in);
    	   System.out.print("\n\t\tHow much money you want to DEPOSIT:  ");
    	   int dp=sc.nextInt();
    	   Statement st=con.createStatement();
    	   ResultSet rs=st.executeQuery("select * from customer where Account_number="+ac+"");
    	   rs.next();
    	   int bal= rs.getInt(7)+ dp;
    	   st.executeUpdate("update customer set Balance="+bal+" where Account_number="+ac+"");
    	   System.out.println("\t\t. . . . . . . . . . . . . . . . . . . "+"\n\t\t       Deposited Money: Rs "+ dp+"\n\t\t       Total Balance: Rs "+ bal+"\n\t\t. . . . . . . . . . . . . . . . . . . ");
    	   String op="Deposit";
    	   Atm a= new Atm();
		   a.statement1(op,dp,bal,ac);
    	   press();
         }
 }