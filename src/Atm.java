import java.io.File;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

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
			    pdfis();
			    System.out.println("\n\t\t\t ------ Do you want to receive this statement in your email ? ----");
			    System.out.println("\t\t\t\t\t\tpress y to send\n\t\t\t\t\t\tpress n to skip");
			    char choice=sc.next().charAt(0);
			    if(choice=='Y' || choice=='y')
			    {
			    	System.out.println("\n\n\t\t\tplease wait. Message is sending ");
			    	email();
			    	System.out.println("\t\t\t Email send successfully");
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
    	  	
    	  	
    	  	void pdfis() throws Exception{
    	  		Document doc= new Document();
    	  		PdfWriter writer=PdfWriter.getInstance(doc,new FileOutputStream("c:/javapdf/transaction.pdf"));
    	  		doc.open();
    	  		Image img= Image.getInstance("C:\\Users\\default.LAPTOP-8VPGBKH3\\Pictures\\Screenshots\\logo.png");
    	  		doc.add(img);
    	  		
    	  		doc.add(new Paragraph("                              Mitapark, kathmandu",FontFactory.getFont(FontFactory.TIMES_BOLD, 12, BaseColor.MAGENTA)));
    	  		doc.add(new Paragraph("\n========================================================================"));
    	  		doc.add(new Paragraph("\n\n"));
    	  		PdfPTable table=new PdfPTable(5);
    	  		
    	  		PdfPCell p1=new PdfPCell(new Paragraph(" Transaction id",FontFactory.getFont(FontFactory.HELVETICA_BOLD,10,Font.BOLD,BaseColor.DARK_GRAY)));
    	  		table.addCell(p1);  //also can do table.addcell("Transaction id") but here only string can use, not any design
    	  		PdfPCell p2=new PdfPCell(new Paragraph("        Date",FontFactory.getFont(FontFactory.HELVETICA_BOLD,10,Font.BOLD,BaseColor.DARK_GRAY)));
    	  		table.addCell(p2); 
    	  		PdfPCell p3=new PdfPCell(new Paragraph("    Operation",FontFactory.getFont(FontFactory.HELVETICA_BOLD,10,Font.BOLD,BaseColor.DARK_GRAY)));
    	  		table.addCell(p3);  
    	  		PdfPCell p4=new PdfPCell(new Paragraph("Transated money (RS)",FontFactory.getFont(FontFactory.HELVETICA_BOLD,10,Font.BOLD,BaseColor.DARK_GRAY)));
    	  		table.addCell(p4); 
    	  		PdfPCell p5=new PdfPCell(new Paragraph("Total Balance (RS)",FontFactory.getFont(FontFactory.HELVETICA_BOLD,10,Font.BOLD,BaseColor.DARK_GRAY)));
    	  		table.addCell(p5);
    	  		
    	  	    Connection  con=new Connectionis().connect();
    	  	    Statement st=con.createStatement();
    	  	    ResultSet rs=st.executeQuery("select * from transaction where Account_number="+bk.ac+"");
		          while(rs.next()) {
		        	  PdfPCell c1= new PdfPCell(new Paragraph(rs.getString(1),FontFactory.getFont(FontFactory.COURIER_BOLD,10,BaseColor.DARK_GRAY)));
		    			table.addCell(c1);
		    			PdfPCell c2= new PdfPCell (new Paragraph(rs.getString(2),FontFactory.getFont(FontFactory.COURIER_BOLD,10,BaseColor.DARK_GRAY)));
		    			table.addCell(c2);
		    			PdfPCell c3=new PdfPCell(new Paragraph(rs.getString(4),FontFactory.getFont(FontFactory.COURIER_BOLD,10,BaseColor.DARK_GRAY)));
		    			table.addCell(c3);
		    			PdfPCell c4=new PdfPCell(new Paragraph(rs.getString(5),FontFactory.getFont(FontFactory.COURIER_BOLD,10,BaseColor.DARK_GRAY)));
		    			table.addCell(c4);
		    			PdfPCell c5=new PdfPCell(new Paragraph(rs.getString(6),FontFactory.getFont(FontFactory.COURIER_BOLD,10,BaseColor.DARK_GRAY)));
		    			table.addCell(c5);
		    	     }
		          doc.add(table);
		          doc.close();
		          System.out.println("\n\t\t\t\t\t   successfully created pdf");
    	  			}	
    	  	
    	  	
    	  	void email() throws Exception {
    	  		String from="puskal.javadeveloper@gmail.com";
    	  		String to="puskal.khadka.18@gmail.com";
    	  		String host="localhost";
    	  		Properties prop= new Properties();
    	  		prop.put("mail.smtp.auth","true");
    	  		prop.put("mail.smtp.starttls.enable","true");
    	  		prop.put("mail.smtp.host","smtp.gmail.com");
    	  		prop.put("mail.smtp.port",587);
    	  		
    	  		Session sess=Session.getInstance(prop,  new javax.mail.Authenticator() {
    	  			protected PasswordAuthentication getPasswordAuthentication() {
    	  				return new PasswordAuthentication(from, "sorry i am unable to disclose my password! you can use you own email and password in this source code -puskal khadka");
    	  				}
    	  		});
    	  		
    	  		MimeMessage msg= new MimeMessage(sess);
    	  		msg.setFrom(new InternetAddress(from));
    	  		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
    	  		msg.setSubject("Transaction history/ Statement");
    	  		 
    	  		Multipart cont= new MimeMultipart();
    	  		
    	  	   MimeBodyPart text= new MimeBodyPart();
    	  	   text.setText("This pdf include the total transaction that you have done through this Account of Puskal Bank Limited");
    	  	   cont.addBodyPart(text);
    	  	   
    	  	   MimeBodyPart pdf= new MimeBodyPart();
    	  	   pdf.attachFile("C:\\javapdf\\transaction.pdf");
    	  	   cont.addBodyPart(pdf);
    	  	   
    	  	   msg.setContent(cont);
    	  	   
    	  	   Transport.send(msg);
    	  		
    	  	}
    	  	
    	  				
   	  	
     }	  	
