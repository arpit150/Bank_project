import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
public class Mainclass {
	
public static void main(String[] args) throws Exception, InterruptedException{
     
	int ch;
	System.out.println("\n\n\n\t\t\t\t\t\t      Welecome To");
	System.out.println("\t\t\t\t\t\t  Puskal Bank Limited");
	Thread.sleep(300);
	System.out.print("\n\n\t\t\t\t      Developed");
	Thread.sleep(160);
	System.out.print(" by");
	Thread.sleep(160);
	System.out.print(" Puskal");
	Thread.sleep(160);
	System.out.print(" khadka");
	Thread.sleep(160);System.out.print(" at");Thread.sleep(160);System.out.print(" Texas");Thread.sleep(160);System.out.println(" Imaginology");Thread.sleep(230);
	
	    do {
	     	System.out.println("\n\n\n\n\n\t\t\t\t\t\t~~~~~~~~~~~~~~~~~~~~~~~~~"); 
		    System.out.println("\t\t\t\t\t\t||    Puskal Bank LTD  ||");                         
	        System.out.println("\t\t\t\t@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	        Date d= new Date();
	        SimpleDateFormat sd= new SimpleDateFormat("yyyy-mm-dd  hh:mm:ss");
	        System.out.println("\t\t\t\t||\t\t\t     Date: "+sd.format(d)+"\t||\n\t\t\t\t||\t\t\t\t\t\t\t||");
	        System.out.println("\t\t\t\t||\t\t1)\tGo to Bank\t\t\t||\n\t\t\t\t||\t\t\t\t\t\t\t||");
	        System.out.println("\t\t\t\t||\t\t2)\tGo To ATM\t\t\t||\n\t\t\t\t||\t\t\t\t\t\t\t||");
	        System.out.println("\t\t\t\t||\t\t3)\tExit     \t\t\t||\n\t\t\t\t||\t\t\t\t\t\t\t||");
	        System.out.println("\t\t\t\t@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	        System.out.print("\n\t---> Select your choice from menu <---  ");
	        Scanner sc1= new Scanner(System.in);
	        ch=sc1.nextInt();
	        System.out.println();
	        switch(ch) {
	                 case 1: 
    	                  Bank b= new Bank();
    	    	          b.menu1();
                          break;
             
                     case 2:
    	                   Atm a= new Atm();
    	                   a.menu2();
    	                   break;
                     case 3:
                    	 System.out.println("Thank for being with us  -Puskal khadka");
   	                  }
           }while(ch<3);
        }
     }


//yubraj.kalanthoki@texasintl.edu.np
//make pdf of statement
//send  pdf statement to email_pdf_per month (day) ___ __ ___using  itext5 library for pdf