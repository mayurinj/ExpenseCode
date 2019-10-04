/* 									DTO FOR EXPENSE CODE MODULE
 												
 * TableName: expensedetails
 * Table Schema: expenseCode (integer), expenseType (String), expenseDescription (String)
*/

package com.cg.ems.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="expensedetails")
public class Expense {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int expenseCode;
	private String expenseType;
	private String expenseDescription;
	
	//Default Constructor
	public Expense() {
		super();
	}
	
	//Parameterized Constructor
	public Expense(int expenseCode, String expenseType, String expenseDescription) {
		super();
		this.expenseCode = expenseCode;
		this.expenseType = expenseType;
		this.expenseDescription = expenseDescription;
	}
	
	
	//Getters and Setters for Expense Code, Expense Description and Expense Type
	public int getExpenseCode() {
		return expenseCode;
	}
	public void setExpenseCode(int expenseCode) {
		this.expenseCode = expenseCode;
	}
	public String getExpenseType() {
		return expenseType;
	}
	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}
	public String getExpenseDescription() {
		return expenseDescription;
	}
	public void setExpenseDescription(String expenseDescription) {
		this.expenseDescription = expenseDescription;
	}
	
	//Overriden method toString()
	@Override
	public String toString() {
		return "Expense [expenseCode=" + expenseCode + ", expenseType=" + expenseType + ", expenseDescription="
				+ expenseDescription + "]";
	}
	
}
