package com.employee.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.google.gson.annotations.Expose;

@Component
@Entity
@Table(name = "salary")
public class Salary implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose private long id;

	@Column(name = "monthly_amount")
	@Expose private double monthlyAmount;

	@Column(name = "annual_amount")
	@Expose private double annualPackage;

	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_id", referencedColumnName = "id",foreignKey = @ForeignKey(name="emp_sal_fk"))
	private Employee employee;
	

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	

	public long getId() {
		return id;
	}

	public double getMonthlyAmount() {
		return monthlyAmount;
	}

	public void setMonthlyAmount(double monthlyAmount) {
		this.monthlyAmount = monthlyAmount;
	}

	public double getAnnualPackage() {
		return annualPackage;
	}

	public void setAnnualPackage(double annualPackage) {
		this.annualPackage = annualPackage;
	}

	
}
