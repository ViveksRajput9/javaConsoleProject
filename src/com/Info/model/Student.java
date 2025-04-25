package com.Info.model;

import java.util.Objects;

public class Student {
	private int id;
	private String firstName ;
	private String lastName;
	private String mobileNumber;
	private String courseName;
	private String className;
	private String address;
	private String fatherName;
	private String motherName;
	private double income;
	private String joinDate;

	public Student(int id, String firstName, String lastName, String mobileNumber, String courseName, String className,
			String address, String fatherName, String motherName, double income, String joinDate) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.courseName = courseName;
		this.className = className;
		this.address = address;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.income = income;
		this.joinDate = joinDate;
	}
	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public String getCourseName() {
		return courseName;
	}

	public String getClassName() {
		return className;
	}

	public String getAddress() {
		return address;
	}

	public String getFatherName() {
		return fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public double getIncome() {
		return income;
	}

	public String getJoinDate() {
		return joinDate;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", mobileNumber="
				+ mobileNumber + ", courseName=" + courseName + ", className=" + className + ", address=" + address
				+ ", fatherName=" + fatherName + ", motherName=" + motherName + ", income=" + income + ", joinDate="
				+ joinDate + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(address, firstName, lastName, mobileNumber,
				motherName,fatherName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(address, other.address) && Objects.equals(className, other.className)
				&& Objects.equals(courseName, other.courseName) && Objects.equals(fatherName, other.fatherName)
				&& Objects.equals(firstName, other.firstName) && id == other.id
				&& Double.doubleToLongBits(income) == Double.doubleToLongBits(other.income)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(mobileNumber, other.mobileNumber)
				&& Objects.equals(motherName, other.motherName);
	}
}