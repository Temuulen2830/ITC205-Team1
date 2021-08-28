package library.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class Member implements Serializable {

	private String lastName;    //Changed LaSt_NaMe to lastName
	private String firstName;    //Changed FiRsT_NaMe to firstName
	private String emailAddress;    //Changed EmAiL_AdDrEsS to emailAddress
	private int phoneNumber;    //Changed PhOnE_NuMbEr to phoneNumber
	private int memberId;    //Changed MeMbEr_Id to memberId
	private double finesOwing;    //Changed FiNeS_OwInG to finesOwing
	
	private Map<Integer, Loan> currentLoans;    //Changed cUrReNt_lOaNs to currentLoans

	
	public Member(String lastName, String firstName, String emailAddress, int phoneNumber, int memberId) {    //Changed LaSt_NaMe to lastName,FiRsT_NaMe to firstName,EmAiL_AdDrEsS to emailAddress,PhOnE_NuMbEr to phoneNumber and MeMbEr_Id to memberId
		this.lastName = lastName;    //Changed LaSt_NaMe to lastName
		this.firstName = firstName;    //Changed FiRsT_NaMe to firstName
		this.emailAddress = emailAddress;    //Changed EmAiL_AdDrEsS to emailAddress
		this.phoneNumber = phoneNumber;    //Changed PhOnE_NuMbEr to phoneNumber
		this.memberId = memberId;    //Changed MeMbEr_Id to memberId
		
		this.currentLoans = new HashMap<>();    //Changed cUrReNt_lOaNs to currentLoans
	}

	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Member:  ").append(memberId).append("\n")     //Changed MeMbEr_Id to memberId
		  .append("  Name:  ").append(lastName).append(", ").append(firstName).append("\n")    //Changed LaSt_NaMe to lastName and FiRsT_NaMe to firstName
		  .append("  Email: ").append(emailAddress).append("\n")    //Changed EmAiL_AdDrEsS to emailAddress
		  .append("  Phone: ").append(phoneNumber)    //Changed PhOnE_NuMbEr to phoneNumber
		  .append("\n")
		  .append(String.format("  Fines Owed :  $%.2f", finesOwing))    //Changed FiNeS_OwInG to finesOwing
		  .append("\n");
		
		for (Loan loan : currentLoans.values()) {    //Changed LoAn to loan and  cUrReNt_lOaNs to currentLoans
			sb.append(loan).append("\n");    //Changed LoAn to loan
		}		  
		return sb.toString();
	}

	
	public int getId() {    //Changed GeT_ID to getId and MeMbEr_Id to memberId
		return memberId;
	}

	
	public List<Loan> getLoans() {    //Changed GeT_LoAnS to getLoans
		return new ArrayList<Loan>(currentLoans.values());    //Changed cUrReNt_lOaNs to currentLoans
	}

	
	public int getNumberOfCurrentLoans() {    //Changed gEt_nUmBeR_Of_CuRrEnT_LoAnS to getNumberOfCurrentLoans
		return currentLoans.size();    //Changed cUrReNt_lOaNs to currentLoans
	}

	
	public double finesOwed() {    //Changed FiNeS_OwEd to finesOwed
		return finesOwing;    //Changed FiNeS_OwInG to finesOwing
	}

	
	public void takeOutLoan(Loan loan) {    //Changed TaKe_OuT_LoAn to takeOutLoan and lOaN to loan
		if (!currentLoans.containsKey(loan.getId())) {    //Changed cUrReNt_lOaNs to currentLoans and lOaN to loan and GeT_Id to getId
			currentLoans.put(loan.getId(), loan);    //Changed cUrReNt_lOaNs to currentLoans and lOaN to loan and GeT_Id to getId
		} else {
			throw new RuntimeException("Duplicate loan added to member");
		}
	}

	
	public String getLastName() {    //Changed GeT_LaSt_NaMe to getLastName
		return lastName;    //Changed LaSt_NaMe to lastName
	}

	
	public String getFirstName() {    //Changed GeT_FiRsT_NaMe to getFirstName
		return firstName;    //Changed FiRsT_NaMe to firstName
	}


	public void addFine(double fine) {    //Changed AdD_FiNe to addFine
		finesOwing += fine;    //Changed FiNeS_OwInG to finesOwing
	}
	
	public double payFine(double amount) {    //Changed PaY_FiNe to payFine and AmOuNt to amount
		if (amount < 0) {    //Changed AmOuNt to amount
			throw new RuntimeException("Member.payFine: amount must be positive");
		}

		double change = 0;
		if (amount > finesOwing) {    //Changed AmOuNt to amount and FiNeS_OwInG to finesOwing
			change = amount - finesOwing;    //Changed AmOuNt to amount and FiNeS_OwInG to finesOwing
			finesOwing = 0;    //Changed FiNeS_OwInG to finesOwing
		}
		else
			finesOwing -= amount;    //Changed FiNeS_OwInG to finesOwing and AmOuNt to amount
		
		return change;
	}


	public void dischargeLoan(Loan loan) {    //Changed dIsChArGeLoAn to dischargeLoan and LoAn to loan
		if (currentLoans.containsKey(loan.getId())) {    //Changed cUrReNt_lOaNs to currentLoans and and LoAn to loan and GeT_Id to getId
			currentLoans.remove(loan.getId());    //Changed cUrReNt_lOaNs to currentLoans and and LoAn to loan and GeT_Id to getId
		} else {
			throw new RuntimeException("No such loan held by member");
		}
	}

}
