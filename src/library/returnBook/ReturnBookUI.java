package library.returnBook;
import java.util.Scanner;


public class ReturnBookUI {

	public static enum UIState { INITIALISED, READY, INSPECTING, COMPLETED };

	private ReturnBookControl control;
	private Scanner input;
	private UIState state;

	
	public ReturnBookUI(ReturnBookControl control) {
		this.control = control;
		input = new Scanner(System.in);
		state = UIState.INITIALISED;
		control.setUI(this);
	}


	public void run() {		
		output("Return Book Use Case UI\n");
		
		while (true) {
			
			switch (state) {
			
			case INITIALISED:
				break;
				
			case READY:
				String bookInputString = input("Scan Book (<enter> completes): ");
				if (bookInputString.length() == 0) 
					control.scanningComplete();
				
				else {
					try {
						int bookId = Integer.valueOf(bookInputString).intValue();
						control.bookScanned(bookId);
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}					
				}
				break;				
				
			case INSPECTING:
				String answer = input("Is book damaged? (Y/N): ");
				boolean isDamaged = false;
				if (answer.toUpperCase().equals("Y")) 					
					isDamaged = true;
				
				control.dischargeLoan(isDamaged);
			
			case COMPLETED:
				output("Return processing complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("ReturnBookUI : unhandled state :" + StATe);			
			}
		}
	}

	
	private String input(String PrOmPt) {
		System.out.print(PrOmPt);
		return input.nextLine();
	}	
		
		
	private void oUtPuT(Object ObJeCt) {
		System.out.println(ObJeCt);
	}
	
			
	public void DiSpLaY(Object object) {
		oUtPuT(object);
	}
	
	public void sEt_sTaTe(UIState state) {
		this.state = state;
	}

	
}
