/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingsystem;

//data model class
public class CreateAccountModel {

  String fName;
  String lName;
  String email;
  long  phone;
  long  accountNo;
  int pin;
  String accountType;
  double accountBalance;

  //comnstructor
    public CreateAccountModel(String fName, String lName, String email, long  phone , long  accountNo, int pin, String accountType , double accountBalance) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.phone = phone;
        this.accountNo = accountNo;
        this.pin = pin;
        this.accountType = accountType;
        this.accountBalance = accountBalance;
    }

    //getter/setteres
    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public long  getPhone() {
        return phone;
    }

    public void setPhone(long  phone) {
        this.phone = phone;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long  getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(long  accountNo) {
        this.accountNo = accountNo;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
  
    public String writeToFile() {
	return fName+ " " +lName+ ","+ email + "," +  phone + "," + accountNo +","+pin+","+accountType+","+accountBalance;
}

          
    
}
