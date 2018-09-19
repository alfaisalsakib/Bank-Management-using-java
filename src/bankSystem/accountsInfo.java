package bankSystem;

import java.util.*;
import java.io.*;

public class accountsInfo 
{
	private String AccountNo;
	private String name;
	private String DOB;
	private String address;
	private String mobileNo;
	private String Deposit;
	
	public accountsInfo(String AccountNo,String name,String DOB,
						String address,String mobileNo,String Deposit)
	{
		this.AccountNo = AccountNo;
		this.name = name;
		this.DOB = DOB;
		this.address = address;
		this.mobileNo = mobileNo;
		this.Deposit = Deposit;
	}

	public String getAccountNo() {
		return AccountNo;
	}

	public void setAccountNo(String accountNo) {
		AccountNo = accountNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getDeposit() {
		return Deposit;
	}

	public void setDeposit(String deposit) {
		Deposit = deposit;
	}	
	
}
