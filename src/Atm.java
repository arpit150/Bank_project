import java.sql.*;
import java.util.Scanner;

public class Atm {
	  
	   Scanner sc= new Scanner(System.in);
       Bank bk= new Bank();
	
	   void menu2() throws Exception {
	     Connection con= new Connectionis().connect();
         bk.accno();
    	 System.out.print("\t\tEnter 4 digit pin: ");
    	 int pn=sc.nextInt();
    	 Statement s=con.createStatement();
    	 ResultSet rs=s.executeQuery("select * from customer where Account_number="+bk.ac+"");
    	 	rs.next();
    	 	int t2=rs.getInt(2);
    	 	
    	    if( pn!=t2 )
    	 	   {
    	         System.out.println("\n\tSorry! Pin number is Incorrect");
    	         System.out.println("\tWARNING!!! Enetering wrong password for repectively 3 times block your account\t\t © PUSkAL");
    	 	   }
    	 	
    	 	else
    	 	   {		
	             int ch3;
	             Scanner sn= new Scanner(System.in);
		           do {
		            	System.out.println("\n\n\n\t\t\t\t\t\t~~~~~~~~~~~~~~~~~ \n\t\t\t\t\t\t|      ATM\t|");
			            System.out.println("\t\t\t\t\t+++++++++++++++++++++++++++++++++");
			            System.out.println("\t\t\t\t\t:\t1.Balance Enquiry\t:\n\t\t\t\t\t:\t2.Withdraw\t\t:\n\t\t\t\t\t:\t3.Statement \t\t:\n\t\t\t\t\t:\t4.Pin change \t\t:\n\t\t\t\t\t:\t5.Go to Home\t\t:");
		            	System.out.println("\t\t\t\t\t+++++++++++++++++++++++++++++++++");
			            System.out.print("\t--->> Press your choice <<---- ");
			            ch3=sn.nextInt();
			           switch(ch3) {
			                 case 1:
				                   balance(bk.ac);
			                        break;
			                 case 2:
				                   withdraw(bk.ac);
				                   break;
			                case 3:
			                     dis_statement1();
			    	             break;
		                	case 4:
				                pinChange(bk.ac);
				                break;
			                    }
			            }while(ch3<5);           
		          }
            }

	   
	    
	   
	    void balance (int ac) throws Exception {
	          Connection con= new Connectionis().connect();
	          Statement st=con.createStatement();
	    	  ResultSet rs= st.executeQuery("select * from customer where Account_number="+ac+"");
	          rs.next();
	          System.out.println("\n\t\t   -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
	          System.out.println("\t\t      Current Balance: RS "+rs.getString(7)+"\n\n\t\t\t    Developer: Puskal"+"\n\t\t   -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
	          bk.press();
	        }
	   
	   
	   
	   
	     void withdraw(int ac) throws Exception {
		       Connection con= new Connectionis().connect();
		       Statement st=con.createStatement();
		       ResultSet rs=st.executeQuery("select * from customer where Account_number="+ac+"");
		       rs.next();
		       int bal= rs.getInt(7);
		    	 if(bal==0){
		    		 System.out.println("\n\t\t:) Soory! You have Rs: 0 . so withdraw not possible");
		    	   }
		    	 else {
		    	    Scanner sc= new Scanner(System.in);
		    	    System.out.print("\n\t\tHow much money you want to WITHDRAW:  ");
		    	    int wd=sc.nextInt();
		    	        if(wd>bal ) {
		    				 System.out.println("\n\t\tSoory! You have no enough money");
		    		        	 }
		    		     else {
		    			     int tot=bal-wd;
		    			      st.executeUpdate("update customer set Balance="+tot+" where Account_number="+ac+"");
		    			      System.out.println("\n\t\t. . . . . . . . . . . . . . . . . . ."+"\n\t\t\t Withdraw money: "+wd+"\n\t\t\t Remaining money: "+tot+"\n\t\t. . . . . . . . . . . . . . . . . . . ");
		    			      String op="withdraw";
		    			      statement1(op,wd,tot,ac);
		    		    	 }
		    	     }
		    	 bk.press();
		    	}
		    
		 
    	 	
	   
		   
	      void statement1(String op,int t, int tot, int ac) throws Exception {
	          	Connection con=new Connectionis().connect();
	     	    Statement st=con.createStatement();
		        st.executeUpdate("insert into transaction (Operation,Transacted_Money,Remaining_Money,Account_number) values('"+op+"',"+t+","+tot+","+ac+")");
		   }
	
		   
	
	      void dis_statement1() throws Exception{
			    Connection con=new Connectionis().connect();
			    Statement st=con.createStatement();
			    System.out.println("\n\t\t- - - - - - - - - Enter your choice - - - - - - - - - -\n\t\t\t  1. All statement till now \n\t\t\t  2. Last 5 statement\n\t\t- - - - - - - - - - - - - - - - - - - - - - - - - - - -");
			    int ch=sc.nextInt();
			    System.out.println("\n\t-----***----- Mini Statement -----***-----\n");
			    switch(ch) {
			         case 1:
				          ResultSet rs=st.executeQuery("select * from transaction where Account_number="+bk.ac+"");
				          while(rs.next()) {
					      String s="\t\tTransaction ID: "+rs.getInt(1)+ "\n\t\tDate: "+rs.getTimestamp(2)+"\n\t\t"+rs.getString(4)+" money: "+rs.getInt(5)+"\n\t\tCurrent Money: "+rs.getInt(6);
					      System.out.println(s+"\n\n");
				            }
					      break;
				           
		            case 2:
		    	            ResultSet r=st.executeQuery("select * from transaction where Account_number="+bk.ac+" order by Trans_id desc limit 5");
		    	            while(r.next()) {
		    		        String s="\t\tTransaction ID: "+r.getInt(1)+ "\n\t\tDate: "+r.getTimestamp(2)+"\n\t\t"+r.getString(4)+" money: "+r.getInt(5)+"\n\t\tRemaining Money: "+r.getInt(6);
					        System.out.println(s+"\n\n");
		    	               }
		    	            break;
		    		   }
			    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\tDeveloped At: Texas Imaginology\n\n");
			    bk.press();
	    	}
		
		   
		
		
		
    	  	void pinChange(int ac) throws Exception {
    	  		System.out.print("\t\tEnter new 4 digit pin: ");
    	  		int np=sc.nextInt();
    	  		System.out.print("\t\tConfirm new pin:      ");
    	  		int cp=sc.nextInt();
    	  		if(np==cp)
    	  		  {
    	  			Connection con= new Connectionis().connect();
    	  			Statement st= con.createStatement();
    	  			st.executeUpdate("update customer set pin="+cp+" where Account_number="+ac+"");
    	  			System.out.println("\t\t :) Pin chages successfully");
    	  		     } 
    	  	  else{
    	  		    System.out.println("\n\t\tpassword doesnot match.");
    	  			System.out.print("\n\t\tDo you want again to continue Pin chaganging process (Y/N)?: ");
    	  		     char ch=sc.next().charAt(0);
    	  		          if(ch=='Y' || ch=='y') {
    	  			          pinChange(ac);
    	  		            }
    	  		          else {
    	  		        	   System.out.println("\t\t  Thank you for being with Puskal Bank limited :)");
    	  		             }
    	  			  }
    	  		bk.press();
    	  		}
     }	  	