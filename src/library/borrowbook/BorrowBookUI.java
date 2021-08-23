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

	
	private String iNpUT(String PrOmPt) {
		System.out.print(PrOmPt);
		return InPuT.nextLine();
	}	
		
		
	private void OuTpUt(Object ObJeCt) {
		System.out.println(ObJeCt);
	}
	
			
	public void SeT_StAtE(uI_STaTe StAtE) {
		this.StaTe = StAtE;
	}

	
	public void RuN() {
		OuTpUt("Borrow Book Use Case UI\n");
		
		while (true) {
			
			switch (StaTe) {			
			
			case CANCELLED:
				OuTpUt("Borrowing Cancelled");
				return;

				
			case READY:
				String MEM_STR = iNpUT("Swipe member card (press <enter> to cancel): ");
				if (MEM_STR.length() == 0) {
					CoNtRoL.CaNcEl();
					break;
				}
				try {
					int MeMbEr_Id = Integer.valueOf(MEM_STR).intValue();
					CoNtRoL.SwIpEd(MeMbEr_Id);
				}
				catch (NumberFormatException e) {
					OuTpUt("Invalid Member Id");
				}
				break;

				
			case RESTRICTED:
				iNpUT("Press <any key> to cancel");
				CoNtRoL.CaNcEl();
				break;
			
				
			case SCANNING:
				String BoOk_StRiNg_InPuT = iNpUT("Scan Book (<enter> completes): ");
				if (BoOk_StRiNg_InPuT.length() == 0) {
					CoNtRoL.CoMpLeTe();
					break;
				}
				try {
					int BiD = Integer.valueOf(BoOk_StRiNg_InPuT).intValue();
					CoNtRoL.ScAnNeD(BiD);
					
				} catch (NumberFormatException e) {
					OuTpUt("Invalid Book Id");
				} 
				break;
					
				
			case FINALISING:
				String AnS = iNpUT("Commit loans? (Y/N): ");
				if (AnS.toUpperCase().equals("N")) {
					CoNtRoL.CaNcEl();
					
				} else {
					CoNtRoL.CoMmIt_LoAnS();
					iNpUT("Press <any key> to complete ");
				}
				break;
				
				
			case COMPLETED:
				OuTpUt("Borrowing Completed");
				return;
	
				
			default:
				OuTpUt("Unhandled state");
				throw new RuntimeException("BorrowBookUI : unhandled state :" + StaTe);			
			}
		}		
	}


	public void DiSpLaY(Object object) {
		OuTpUt(object);		
	}


}
