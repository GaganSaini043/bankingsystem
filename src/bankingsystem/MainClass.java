package bankingsystem;

import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import bankingsystem.CreateAccountModel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainClass extends CreateAccountModel{
    
    //list to store the account detail
    public static ArrayList<CreateAccountModel> createAcc = new ArrayList<CreateAccountModel>();
    public static String filePath = "\"D:/documents/account.txt\"";

    //constructor
     public MainClass(String fName, String lName, String email, long  phone , long  accountNo, int pin, String accountType , double accountBalance) {
        super(fName, lName, email, phone , accountNo, pin, accountType ,accountBalance );
    }
     
    public static void main(String[] args) throws IOException {
        
        //method to start the process 
        fillData();
    }
    
    //method to select the option from banking service and trasaction
    public static void fillData() throws IOException{
       
       readFromFile(createAcc);
       Scanner sc= new Scanner(System.in);
       System.out.println("---Welcome to Banking system---");
       System.out.println("");
       System.out.println("Select any option:"+ "\n" +"1.Banking Service"+ "\n" +"2.Transaction"+"\n"+"3.Exit");
      
       switch(sc.nextInt()){
           case 1:
               bankingService();
               break;
           case 2:
               transaction();
               break;
           case 3:
               break;
       }
    
    }
    
    //perform create , edit and display account details 
    public static void bankingService(){
        
        Scanner sc= new Scanner(System.in);
        System.out.println("");
        System.out.println("Select any option:"+ "\n" +"1.Create Account"+ "\n" +"2.Edit Account Details"+
                       "\n"+"3.Display Account Detail"+"\n"+"4.Exit");
        switch(sc.nextInt()){
           case 1:
                {
                    try {
                        createAccount();
                    } catch (IOException ex) {
                        Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
               break;
               
           case 2:
               editDetails();
               bankingService();
               break;
               
           case 3:
               displayDetails();
               break;
               
           case 4:
        {
            try {
                fillData();
            } catch (IOException ex) {
                Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
               break;
       }
        
    }
    
    //create account by entering information
    public static void createAccount() throws IOException {
        
        //create a new file named account.txt, if the file exists will be overwritten
        String filename= filePath;
	FileWriter emp = new FileWriter(filename);
	PrintWriter pw=new PrintWriter(emp);
        
       
        
        Scanner sc= new Scanner(System.in);
        String accountType  = "";
        
        do{
        
        System.out.println("");
        System.out.println("Select account type:"+ "\n" +" 1.Current"+ "\n" +" 2.Savings"+ "\n" +" 3.Salary"+ "\n" +" 4.NRI"+"\n"+"5.Exit");
        int type= sc.nextInt();
        
        System.out.println("");
        System.out.print("Enter your first name: ");
        String firstName = sc.next();
        
        System.out.print("Enter your last name: ");
        String lastName = sc.next();
        
        System.out.print("Enter your email address: ");
        String email = sc.next();
        
        System.out.print("Enter your phone no: ");
        long  phone = sc.nextLong();
        
        System.out.print("Enter your account no: ");
        long  accountNo = sc.nextLong();
        
        System.out.print("Set your pin: ");
        int pin = sc.nextInt();
        
        System.out.print("Confirm your pin: ");
        int confirmPin = sc.nextInt();
        
        switch(type){
            
            case 1:
                accountType = "Current";
                break;
            case 2:
                accountType = "Savings";
                break;
            case 3:
                accountType = "Salary";
                break;
            case 4:
                accountType = "NRI";
                break;
            case 5:
                bankingService();
                break;
                
        }
        
        double accountBalance = 0.0;
        if(pin == confirmPin){
            
            //fill data model
            CreateAccountModel ca = new CreateAccountModel(firstName , lastName , email , phone , accountNo , pin , accountType , accountBalance);
            
            //add to list
            createAcc.add(ca);
            
         

        }
        else{
            
            System.out.print("Set your pin: ");
            pin = sc.nextInt();

            System.out.print("Confirm your pin: ");
            confirmPin = sc.nextInt();
            
        }
            
            System.out.println("");
            System.out.println("Want to add more account ? yes/no");
            
        }
        
         while(sc.next().equalsIgnoreCase("yes"));
        //close text file
         writeToFile(createAcc , false);
         bankingService();

    }
    
    //to display account details
    public static void displayDetails(){
        
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        System.out.print("Enter your account no: ");
        int accountNo = sc.nextInt();

        if(createAcc.size() > 0){
            
            //for loop to get entered account no
            for(int i = 0 ; i < createAcc.size() ; i++)
            {
                if(createAcc.get(i).accountNo == accountNo){
                    System.out.print("Enter your pin: ");
                    int pin = sc.nextInt();
                    
                    if(createAcc.get(i).pin == pin){
                        System.out.println("");
                        System.out.println("====== Account Details ======");
                      System.out.println("Name : "+createAcc.get(i).fName +" "+createAcc.get(i).lName);
                      System.out.println("Email : "+createAcc.get(i).email );
                      System.out.println("Phone : "+createAcc.get(i).phone );
                      System.out.println("Account No : "+createAcc.get(i).accountNo );
                      System.out.println("Account Type : "+createAcc.get(i).accountType );
                    }
                } 
                else{
                     if(i > createAcc.size()){
                        System.out.println("Account doesnot exists!");
                         System.out.println("want to exit? y/n");
                         if(sc.next().equalsIgnoreCase("y")){
                              try {
                                  fillData();
                              } catch (IOException ex) {
                                  Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                              }
                         }
                         else{
                             displayDetails();
                         }                  
                     }
                }
            }   
        }
        else{
        }
        
        try {
            writeToFile(createAcc , false);
        } catch (IOException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
                      System.out.println("");
                      System.out.println("Press 0 to exit");
                      
                      if(sc.nextInt()== 0){
                        bankingService();
                      }
    }
    
    //method to edit account details
    public static void editDetails(){
        
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        System.out.print("Enter your account no: ");
        double accountNo = sc.nextDouble();

        if(createAcc.size() > 0){
            
            //for loop to get entered account no
            for(int i = 0 ; i < createAcc.size() ; i++)
            {
                if(createAcc.get(i).accountNo == accountNo){
                    System.out.print("Enter your pin: ");
                    int pin = sc.nextInt();
                    
                    if(createAcc.get(i).pin == pin){
                        
                      System.out.println("");
                      System.out.println("====== Account Details ======");
                      System.out.println("Name : "+createAcc.get(i).fName +" "+createAcc.get(i).lName);
                      System.out.println("Email : "+createAcc.get(i).email );
                      System.out.println("Phone : "+createAcc.get(i).phone );
                      System.out.println("Account No : "+createAcc.get(i).accountNo );
                      System.out.println("Account Type : "+createAcc.get(i).accountType );
                      
                      do{
                          System.out.println("");
                          System.out.println("What do you want to edit ?"+"\n" + "1.Name"+"\n" + "2.email address"+"\n" + "3.phone"+"\n" + "4.pin"+"\n" + "5.Exit");
                        
                          //switch case to perform edit accoprding to the value entered by the user
                      switch(sc.nextInt()){
                          case 1:
                                System.out.println("Enter your new first name: ");
                                String firstName = sc.next();

                                System.out.println("Enter your new last name: ");
                                String lastName = sc.next();
                                
                                createAcc.get(i).setfName(firstName);
                                createAcc.get(i).setlName(lastName);
                
                              break;
                          case 2:
                                System.out.println("Enter your new email address: ");
                                String email = sc.next();
                                createAcc.get(i).setEmail(email);
                              break;
                          case 3:
                                System.out.println("Enter your new phone no: ");
                                long phone = sc.nextLong();
                                createAcc.get(i).setPhone(phone);
                              break;
                          case 4:
                                System.out.println("Enter your current pin: ");
                                int cPin = sc.nextInt();

                                if(createAcc.get(i).pin == cPin){

                                    System.out.println("Set your new pin: ");
                                    int setPin = sc.nextInt();

                                    System.out.println("Confirm your pin: ");
                                    int confirmPin = sc.nextInt();

                                    createAcc.get(i).setPin(setPin);

                                 }
                                else{
                                    System.out.println("pin is incorrect ");
                                }
                              break;
                          case 5:
                              bankingService();
                              break;
                          default:
                              bankingService();
                              break;
                      }

                          System.out.println("");
                          System.out.println("----------------- Details Updated Successfully!! -----------------");
                          System.out.println("");
                          System.out.println("Want to edit more?yes/no ");
                      }
                      while(sc.next().equalsIgnoreCase("yes"));
                      System.out.println("");
                      System.out.println("Press 0 to exit");
                      
                      if(sc.nextInt()== 0){
                        bankingService();
                      }
    
                    }
                }
                 else{
                    if(i > createAcc.size()){
                          System.out.println("Account doesnot exists!");
                         System.out.println("want to exit? y/n");
                         if(sc.next().equalsIgnoreCase("y")){
                              try {
                                  fillData();
                              } catch (IOException ex) {
                                  Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                              }
                         }
                         else{
                             editDetails();
                         }
                         
                    }
                   
                }
               
            }
            
        }
       
        try {
            writeToFile(createAcc , true);
        } catch (IOException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    //method for transaction process
    public static void transaction() throws IOException{
        
        Scanner sc= new Scanner(System.in);
        
        
        if(createAcc.size() > 0){
            System.out.println("");
            System.out.println("Enter your account no:");
            double accNo = sc.nextDouble();
            
            for(int i = 0 ; i < createAcc.size() ; i++)
            {
                if(createAcc.get(i).accountNo == accNo){
                    System.out.println("");
                    System.out.println("Enter pin to continue or Enter 0 to exit");
                    int pin = sc.nextInt();
                    if(pin == 0){
                    }
                    else{
                        if(createAcc.get(i).pin == pin){
                            
                            do{
                            
                                System.out.println("");
                            System.out.println("1.Display the current balance" + "\n" + "2. Deposit money" + "\n" + "3. Withdraw money" + "\n" + "4. Transfer money to other accounts within the bank" + "\n" + "5. Pay utility bills" + "\n" + "6. Exit");
                            
                            switch(sc.nextInt()){
                                //display current balance
                                case 1:
                                    System.out.println("");
                                    System.out.println("Your account balance is : " + createAcc.get(i).getAccountBalance());
                                   
                                    break;
                                    
                                    //to deposit money in your account
                                case 2:
                                    System.out.println("Your current account balance is :"+ createAcc.get(i).getAccountBalance());
                                    System.out.println("Enter the amount you want to deposit :");
                                    double amount = sc.nextDouble();
                                    if(amount > 0){
                                        createAcc.get(i).setAccountBalance(amount);
                                        System.out.println("Your new account balance is :"+ createAcc.get(i).getAccountBalance());

                                    }
                                    else{
                                        System.out.println("Enter the valid amount");
                                    }
                                     try {
                                        writeToFile(createAcc , true);
                                    } catch (IOException ex) {
                                        Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                   
                                    break;
                                    
                                    //to withdraw money from your account
                                case 3:
                                    System.out.println("Your current account balance is :"+ createAcc.get(i).getAccountBalance());
                                    System.out.println("Enter the amount you want to withdraw :");
                                    double withdrawAmount = sc.nextDouble();
                                    if(withdrawAmount > 0){
                                        if(createAcc.get(i).getAccountBalance() > withdrawAmount){
                                            double leftAmount = createAcc.get(i).getAccountBalance()-withdrawAmount;
                                            createAcc.get(i).setAccountBalance(leftAmount);
                                            System.out.println("");
                                           
                                            System.out.println("======Transaction Successful======");
                                            System.out.println("Your new account balance is :"+ createAcc.get(i).getAccountBalance());
                                        }
                                        else{
                                            System.out.println("======Transaction Denied======");
                                            System.out.println("You do not have sufficient amount to withdraw :");
                                        }
                                         try {
                                            writeToFile(createAcc , true);
                                        } catch (IOException ex) {
                                            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                       
                                    }
                                    else{
                                        System.out.println("Enter the valid amount");
                                    }
                                    break;
                                    
                                    //to transfer money to another account
                                case 4:
                                    double transMoney = 0.0;
                                    transMoney = transferMoney(createAcc.get(i).getAccountBalance());
                                    if(transMoney > 0){
                                       
                                        double leftamount = createAcc.get(i).getAccountBalance()-transMoney;
                                        createAcc.get(i).setAccountBalance(leftamount);
                                        System.out.println("Your new account balance is :"+ createAcc.get(i).getAccountBalance());
                                    }
                                     try {
                                        writeToFile(createAcc , true);
                                    } catch (IOException ex) {
                                        Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    break;
                                    
                                    //to pay utility bills
                                case 5:
                                    
                                    double amnt = payBill(createAcc.get(i).getAccountBalance());
                                    double leftAmount = createAcc.get(i).getAccountBalance()-amnt;
                                    createAcc.get(i).setAccountBalance(leftAmount);
                                    System.out.println("Your new account balance is :"+ createAcc.get(i).getAccountBalance());
                                    try {
                                        writeToFile(createAcc , true);
                                    } catch (IOException ex) {
                                        Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    break;
                                    
                                    //to exit
                                case 6:
                                     fillData();
                                    break;
                                    
                                default:
                                    transaction();
                                    break;
                            }
                            
                            System.out.println("want to exit? y/n");
                                
                            }
                            while(sc.next().equalsIgnoreCase("n"));
                             fillData();
                        }
                        }
                    }
                 else{
                         if(i > createAcc.size()){
                         System.out.println("Account doesnot exists!");
                         System.out.println("want to exit? y/n");
                         if(sc.next().equalsIgnoreCase("y")){
                             fillData();
                         }
                         else{
                             transaction();
                         }
                         
                         }                
                }
                    
                }
            }
        else{
            System.out.println("No Account exists! First create an account to perform any transaction");
            fillData();
        }
        
        }
    
    //method for transferring money
    public static double transferMoney(double amnt){
    
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter the account number to which you want to tranfer money :");
        double accountNo = sc.nextDouble();
        double amount = 0.0;
        
        //for loop to get entered account no
        for(int i = 0 ; i < createAcc.size() ; i++)
        {
            if(createAcc.get(i).getAccountNo() == accountNo){
                
                System.out.println("Your current account balance is :"+ amnt);
                System.out.println("Enter the amount you want to tranfer :");
                amount = sc.nextDouble();
                if(amount > 0){
                 
                 double totalamt = amount+createAcc.get(i).getAccountBalance();
                 createAcc.get(i).setAccountBalance(totalamt);
                 System.out.println("");
                 System.out.println("=====Transaction Successful=====");
                }
                else{
                    System.out.println("Enter the valid amount");
                }
                break;
                
            }
            
            else{
                 if(i > createAcc.size()){
                         System.out.println("");
                          System.out.println("Account doesnot exists!");
                         System.out.println("want to exit? y/n");
                         if(sc.next().equalsIgnoreCase("y")){
                              try {
                                  fillData();
                              } catch (IOException ex) {
                                  Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                              }
                         }
                         else{
                             try {
                                 transaction();
                             } catch (IOException ex) {
                                 Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                             }
                         }         
                    }
            }
             try {
            writeToFile(createAcc , true);
        } catch (IOException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        return amount;
    }
    
    //method to pay utility bills
    public static double payBill(double amnt){
        double billAmt = 0;
        try {
            System.out.println("Your current account balance is :"+ amnt);
            Scanner sc= new Scanner(System.in);
            System.out.println("Select any:" + "\n"+ "1. Water" + "\n" +"2. Electricity" +"\n" +"3. Gas"+"\n"+"4. Internet"+"\n" + "5. Exit");
            
           
            
            switch(sc.nextInt()){
                
                case 1:
                    
                    System.out.println("Enter Water bill amount: ");
                    billAmt = sc.nextDouble();
                    
                    break;
                case 2:
                    
                    System.out.println("Enter Electricity bill amount: ");
                    billAmt = sc.nextDouble();
                    
                    break;
                case 3:
                    
                    System.out.println("Enter Gas bill amount: ");
                    billAmt = sc.nextDouble();
                    
                    break;
                case 4:
                    
                    System.out.println("Enter Internet bill amount: ");
                    billAmt = sc.nextDouble();
                    
                    break;
                case 5:
                    transaction();
                    break;
                default:
                    System.out.println("Entered number is not correct");
                    break;
                    
            }
           
        } catch (IOException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     return billAmt;
    }
    
    
    public static void writeToFile(ArrayList<CreateAccountModel> createAcc, boolean flag) throws IOException{
        
            String filename= filePath;
            FileWriter emp = new FileWriter(filename);
            PrintWriter pw=new PrintWriter(emp);
            
            for (int i=0; i<createAcc.size() ; i++){            
                pw.println(createAcc.get(i).writeToFileModel());        
                } 
                  
            pw.close();
    }
    
    //method to read data from text file
    public static void readFromFile(ArrayList<CreateAccountModel> createAcc) throws IOException
	{
            File f = new File(filePath);
		if (f.exists()) {
                    
                    FileInputStream empFile=new FileInputStream(filePath);
		BufferedReader br=new BufferedReader(new InputStreamReader(empFile));
		
		CreateAccountModel ft;
		String line = null;
                
                FileReader fr = new FileReader(filePath);
  
                int i;
                while ((line=br.readLine()) !=null){
                        String fields[]=line.split(",");
                        String fName=fields[0];
                        System.out.print("fname" + fName);
                        String lName=fields[1];
			String email=fields[2];
                        long phone=Long.parseLong(fields[3]);
                        long  accountNo=Long.parseLong(fields[4]);
                        int pin=Integer.parseInt(fields[5]);
                        String accType=fields[6];
                        double accountBalance=Double.parseDouble(fields[7]);
//			if(fields.length==7) {
				ft=new CreateAccountModel(fName, lName, email, phone , accountNo, pin, accType, accountBalance);
				createAcc.add(ft);
                                System.out.println("list" + createAcc);
                }
                
                    

                    }
		
    
        }
    
}