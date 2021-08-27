package library.payfine;
import java.util.Scanner;

public class PayFineUi {

	public enum UiState { INITIALISED, READY, PAYING, COMPLETED, CANCELLED };    //Changed uI_sTaTe to UiState

	private PayFineControl control;    //Changed pAY_fINE_cONTROL to payFineControl and CoNtRoL to control
	private Scanner input;
	private UiState state;    //Changed uI_sTaTe to UiState and StAtE to state

	public PayFineUi(PayFineControl control) {    //Changed pAY_fINE_cONTROL to payFineControl
		this.control = control;    //Changed CoNtRoL to control
		input = new Scanner(System.in);
		state = UiState.INITIALISED;    //Changed StAtE to state and uI_sTaTe to UiState
		control.setUi(this);    //Changed SeT_uI to setUi
	}

	public void setUi(UiState state) {    //Changed SeT_uI to setUi, uI_sTaTe to uiState
		this.state = state;    //Changed uI_sTaTe to UiState and StAtE to state
	}

	public void run() {    //Changed RuN to run
		output("Pay Fine Use Case UI\n");

		while (true) {

			switch (state) {    //Changed StAtE to state

				case READY:
					String memberString = input("Swipe member card (press <enter> to cancel): ");    //Changed Mem_Str to memberString
					if (memberString.length() == 0) {    //Changed Mem_Str to memberString
						control.cancel();    //Changed CoNtRoL to control and CaNcEl to cancel
						break;
					}
					try {
						int memberId = Integer.valueOf(memberString).intValue();    //Changed Mem_ID to memberId and Mem_Str to memberString
						control.cardSwiped(memberId);    //Changed Mem_ID to memberId, CoNtRoL to control and CaRd_sWiPeD to cardSwiped
					}
					catch (NumberFormatException e) {
						output("Invalid memberId");
					}
					break;

				case PAYING:
					double amount = 0;    //Changed AmouNT to amount
					String amountString = input("Enter amount (<Enter> cancels) : ");    //Changed Amt_Str to amountString
					if (amountString.length() == 0) {    //Changed Amt_Str to amountString
						control.cancel();    //Changed CoNtRoL to control and CaNcEl to cancel
						break;
					}
					try {
						amount = Double.valueOf(amountString).doubleValue();    //Changed AmouNT to amount and Amt_Str to amountString
					}
					catch (NumberFormatException e) {
						output("Invalid amounr");
					}
					if (amount <= 0) {    //Changed AmouNT to amount
						output("Amount must be positive");
						break;
					}
					control.payFine(amount);    //Changed CoNtRoL to control, PaY_FiNe to payFine and AmouNT to amount
					break;

				case CANCELLED:
					output("Pay Fine process cancelled");
					return;

				case COMPLETED:
					output("Pay Fine process complete");
					return;

				default:
					output("Unhandled state");
					throw new RuntimeException("FixBookUI : unhandled state :" + state);    //Changed StAtE to state
			}
		}
	}

	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}

	private void output(Object object) {
		System.out.println(object);
	}


	public void display(Object object) {
		output(object);
	} //Changed DiSplAY to display

}
