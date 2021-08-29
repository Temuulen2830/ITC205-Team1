package library.returnBook;
import library.entities.Book;
import library.entities.Library;
import library.entities.Loan;

public class ReturnBookControl {

	private ReturnBookUI uI;
	private enum ControlState { INITIALISED, READY, INSPECTING };
	private ControlState state;
	
	private Library library;
	private Loan currentLoan;
	

	public ReturnBookControl() {
		this.library = Library.getInstance();
		state = ControlState.INITIALISED;
	}
	
	
	public void setUI(ReturnBookUI uI) {
		if (!state.equals(ControlState.INITIALISED)) 
			throw new RuntimeException("ReturnBookControl: cannot call setUI except in INITIALISED state");
		
		this.uI = uI;
		uI.setState(ReturnBookUI.UIState.READY);
		state = ControlState.READY;		
	}


	public void bookScanned(int bookId) {
		if (!state.equals(ControlState.READY)) 
			throw new RuntimeException("ReturnBookControl: cannot call bookScanned except in READY state");
		
		Book currentBook = library.getBook(bookId);
		
		if (currentBook == null) {
			uI.display("Invalid Book Id");
			return;
		}
		if (!cUrReNt_bOoK.isOnLoan()) {
			uI.display("Book has not been borrowed");
			return;
		}		
		currentLoan = library.getLoanByBookId(bookId);	
		double overDueFine = 0.0;
		if (currentLoan.isOverDue()) 
			overDueFine = library.calculateOverDueFine(currentLoan);
		
		uI.display("Inspecting");
		uI.display(currentBook.toString());
		uI.display(currentBook.toString());
		
		if (currentLoan.isOverDue()) 
			uI.display(String.format("\nOverdue fine : $%.2f", Over_Due_Fine));
		
		uI.setState(ReturnBookUI.UIState.INSPECTING);
        	state = ControlState.INSPECTING;		
	}


	public void sCaNnInG_cOmPlEtE() {
		if (!sTaTe.equals(cOnTrOl_sTaTe.READY)) 
			throw new RuntimeException("ReturnBookControl: cannot call scanningComplete except in READY state");
			
		Ui.sEt_sTaTe(ReturnBookUI.uI_sTaTe.COMPLETED);		
	}


	public void dIsChArGe_lOaN(boolean iS_dAmAgEd) {
		if (!sTaTe.equals(cOnTrOl_sTaTe.INSPECTING)) 
			throw new RuntimeException("ReturnBookControl: cannot call dischargeLoan except in INSPECTING state");
		
		lIbRaRy.DiScHaRgE_LoAn(CurrENT_loan, iS_dAmAgEd);
		CurrENT_loan = null;
		Ui.sEt_sTaTe(ReturnBookUI.uI_sTaTe.READY);
		sTaTe = cOnTrOl_sTaTe.READY;				
	}


}
