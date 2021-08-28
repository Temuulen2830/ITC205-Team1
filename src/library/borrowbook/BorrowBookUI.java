package library.borrowbook;
import java.util.Scanner;


public class BorrowBookUI {
	
	public static enum uIState {INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED};	
								//Changed in line 7 "uI_STaTe" to uIState and the white space at the start and end of the bracket were deleted
	private BorrowBookControl control;			//Changed "bORROW_bOOK_cONTROL" to BorrowBookControl and "CoNtRoL" to control
	private Scanner input;					//Changed "InPuT" to input
	private uIState state;					//Changed "uI_STaTe" to uIState and "StaTe" to state

	
	public BorrowBookUI(BorrowBookControl control) {	//Changed "bORROW_bOOK_cONTROL" to BorrowBookControl
		this.control = control;				//Changed "CoNtRoL" to control
		input = new Scanner(System.in);			//Changed "InPuT" to input
		state = uIState.INITIALISED;			//Changed "StaTe" to state and "uI_STaTe" to uIState
		control.setUI(this);				//Changed "SeT_Ui" to setUI
	}

	
	private String input(String prompt) {		//Changed "iNpUT" to input and "PrOmPt" to prompt
		System.out.print(prompt);		//Changed "PrOmPt" to prompt
		return input.nextLine();		//Changed "InPuT" to input
	}	
		
		
	private void output(Object object) {		//Changed "OuTpUt" to output and "ObJeCt" to object
		System.out.println(object);		//Changed "ObJeCt" to object
	}
	
			
	public void setState(uIState state) {		//Changed "SeT_StAtE" to setState and "uI_STaTe StAtE" to uIState state
		this.state = state;			//Changed "StaTe" to state
	}

	
	public void run() {					//Changed "RuN" to run
		output("Borrow Book Use Case UI\n");		//Changed "OuTpUt" to output
		
		while (true) {
			
			switch (state) {			//Changed "StaTe" to state
			
			case CANCELLED:				
				output("Borrowing Cancelled");	//Changed "OuTpUt" to output
				return;

				
			case READY:		
				String memStr = input("Swipe member card (press <enter> to cancel): ");		//Changed "MEM_STR" to memStr and "iNpUT" to input
				if (memStr.length() == 0) {							//Changed "MEM_STR" to memStr
					control.cancel();							//Changed "CoNtRoL.CaNcEl" to control.cancel
					break;
				}
				try {
					int memberId = Integer.valueOf(memStr).intValue();		//Changed "MeMbEr_Id" to memberId and "MEM_STR" to memStr
					control.swiped(memberId);					//Changed "CoNtRoL.SwIpEd(MeMbEr_Id)" to control.swiped(memberId)
				}
				catch (NumberFormatException e) {
					output("Invalid Member Id");					//Changed "OuTpUt" to output
				}
				break;

				
			case RESTRICTED:
				input("Press <any key> to cancel");					//Changed "iNpUT" to input
				control.cancel();							//Changed "CoNtRoL.CaNcEl" to control.cancel
				break;
			
				
			case SCANNING:
				String bookStringInput = input("Scan Book (<enter> completes): ");	//Changed "BoOk_StRiNg_InPuT" to bookStringInput and "iNpUT" to input
				if (bookStringInput.length() == 0) {					//Changed "BoOk_StRiNg_InPuT" to bookStringInput
					control.complete();						//Changed "CoNtRoL.CoMpLeTe" to control.complete
					break;
				}
				try {
					int bid = Integer.valueOf(bookStringInput).intValue();	//Changed "BiD" to bid and "BoOk_StRiNg_InPuT" to bookStringInput
					control.scanned(bid);					//Changed "CoNtRoL.ScAnNeD(BiD)" to control.scanned(bid)
					
				} catch (NumberFormatException e) {
					output("Invalid Book Id");				//Changed "OuTpUt" to output
				} 
				break;
					
				
			case FINALISING:
				String ans = input("Commit loans? (Y/N): ");			//Changed "AnS" to ans and "iNpUT" to input
				if (ans.toUpperCase().equals("N")) {				//Changed "AnS" to ans
					control.cancel();					//Changed "CoNtRoL.CaNcEl" to control.cancel
					
				} else {
					control.commitLoans();					//Changed "CoNtRoL.CoMmIt_LoAnS" to control.commitLoans
					input("Press <any key> to complete ");			//Changed "iNpUT" to input
				}
				break;
				
				
			case COMPLETED:
				output("Borrowing Completed");							//Changed "OuTpUt" to output
				return;
	
				
			default:
				output("Unhandled state");							//Changed "OuTpUt" to output
				throw new RuntimeException("BorrowBookUI : unhandled state :" + state);		//Changed "StaTe" to state
			}
		}		
	}


	public void display(Object object) {			//Changed "DiSpLaY" to display
		output(object);					//Changed "OuTpUt" to output
	}


}
