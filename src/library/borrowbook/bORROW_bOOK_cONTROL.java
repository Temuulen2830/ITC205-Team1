package library.borrowbook;
import java.util.ArrayList;
import java.util.List;

import library.entities.Book;
import library.entities.Library;
import library.entities.Loan;
import library.entities.Member;

public class BorrowBookControl {			//Changed "bORROW_bOOK_cONTROL" to BorrowBookControl
	
	private BorrowBookUI uI;
	
	private Library library;			//Changed "lIbRaRy" to library
	private Member member;				//Changed "mEmBeR" to member
	private enum controlState {INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED};	//Changed "CONTROL_STATE" to controlState
	private controlState state;			//Changed "CONTROL_STATE" to controlState and "sTaTe" to state
	
	private List<Book> pendingList;			//Changed "pEnDiNg_LiSt" to pendingList
	private List<Loan> completedList;		//Changed "cOmPlEtEd_LiSt" to completedList
	private Book book;				//Changed "bOoK" to book
	
	
	public BorrowBookControl() {				//Changed "bORROW_bOOK_cONTROL" to BorrowBookControl
		this.library = library.getInstance();		//Changed "lIbRaRy" to library and "Library.GeTiNsTaNcE" to library.getInstance
		state = controlState.INITIALISED;		//Changed "sTaTe" to state and "CONTROL_STATE" to controlState
	}
	

	public void setUI(BorrowBookUI uI) {			//Changed "SeT_Ui" to setUI and "Ui" to uI
		if (!state.equals(controlState.INITIALISED)) 	//Changed "sTaTe" to state and "CONTROL_STATE" to controlState
			throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");
			
		this.uI = uI;					//Changed "Ui" to uI
		uI.setState(BorrowBookUI.uIState.READY);	//Changed "Ui.SeT_StAtE" to uI.setState and "uI_STaTe" to uIState
		state = controlState.READY;			//Changed "sTaTe" to state and "CONTROL_STATE" to controlState
	}

		
	public void swiped(int memberId) {					//Changed "SwIpEd" to swiped and "mEmBeR_Id" to memberId
		if (!state.equals(controlState.READY)) 				//Changed "sTaTe" to state and "CONTROL_STATE" to controlState
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
			
		member = library.getMember(memberId);				//Changed "mEmBeR = lIbRaRy.gEt_MeMbEr(mEmBeR_Id)" to member = library.getMember(memberId)
		if (member == null) {						//Changed "mEmBeR" to member
			uI.display("Invalid memberId");				//Changed "DiSpLaY" to display
			return;
		}
		if (library.canMemberBorrow(member)) {				//Changed "lIbRaRy.cAn_MeMbEr_BoRrOw(mEmBeR)" to library.canMemberBorrow(member)
			pendingList = new ArrayList<>();			//Changed "pEnDiNg_LiSt" to pendingList
			uI.setState(BorrowBookUI.uIState.SCANNING);		//Changed "SeT_StAtE" to setState and "uI_STaTe" to uIState
			state = controlState.SCANNING; 				//Changed "sTaTe" to state and "CONTROL_STATE" to controlState
		}
		else {
			uI.display("Member cannot borrow at this time");	//Changed "DiSpLaY" to display
			uI.setState(BorrowBookUI.uIState.RESTRICTED); 		//Changed "SeT_StAtE" to setState and "uI_STaTe" to uIState
		}
	}
	
	
	public void scanned(int bookId) {				//Changed "ScAnNeD" to scanned and "bOoKiD" to bookId
		book = null;						//Changed "bOoK" to book
		if (!state.equals(controlState.SCANNING)) 		//Changed "sTaTe" to state and "CONTROL_STATE" to controlState
			throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
			
		book = library.getBook(bookId);				//Changed "bOoK = lIbRaRy.gEt_BoOk(bOoKiD)" to book = library.getBook(bookId)
		if (book == null) {					//Changed "bOoK" to book
			uI.display("Invalid bookId");			//Changed "DiSpLaY" to display
			return;
		}
		if (!book.isAvailable()) {				//Changed "bOoK.iS_AvAiLaBlE" to book.isAvailable
			uI.display("Book cannot be borrowed");		//Changed "DiSpLaY" to display
			return;
		}
		pendingList.add(book);					//Changed "pEnDiNg_LiSt" to pendingList and "bOoK" to book
		for (Book b : pendingList) 				//Changed "B" to b and "pEnDiNg_LiSt" to pendingList
			uI.display(b.toString());			//Changed "DiSpLaY" to display and "B" to b
	//In line 79 Changed "lIbRaRy.gEt_NuMbEr_Of_LoAnS_ReMaInInG_FoR_MeMbEr(mEmBeR) - pEnDiNg_LiSt.size()" to library.getNumberOfLoansRemainingForMember(member) - pendingList.size()
		if (library.getNumberOfLoansRemainingForMember(member) - pendingList.size() == 0) {
			uI.display("Loan limit reached");		//Changed "DiSpLaY" to display
			complete();					//Changed "CoMpLeTe" to complete
		}
	}
	
	
	public void complete() {						//Changed "CoMpLeTe" to complete
		if (pendingList.size() == 0) 					//Changed "pEnDiNg_LiSt" to pendingList
			cancel();						//Changed "CaNcEl" to cancel
		
		else {
			uI.display("\nFinal Borrowing List");			//Changed "DiSpLaY" to display
			for (Book book : pendingList) 				//Changed "bOoK" to book and "pEnDiNg_LiSt" to pendingList
				uI.display(book.toString());			//Changed "DiSpLaY" to display and "bOoK" to book
			
			completedList = new ArrayList<Loan>();			//Changed "cOmPlEtEd_LiSt" to completedList
			uI.setState(BorrowBookUI.uIState.FINALISING);		//Changed "SeT_StAtE" to setState and "uI_STaTe" to uIState 
			state = controlState.FINALISING;			//Changed "sTaTe" to state and "CONTROL_STATE" to controlState
		}
	}


	public void CoMmIt_LoAnS() {
		if (!sTaTe.equals(CONTROL_STATE.FINALISING)) 
			throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
			
		for (Book B : pEnDiNg_LiSt) {
			Loan lOaN = lIbRaRy.iSsUe_LoAn(B, mEmBeR);
			cOmPlEtEd_LiSt.add(lOaN);			
		}
		uI.DiSpLaY("Completed Loan Slip");
		for (Loan LOAN : cOmPlEtEd_LiSt) 
			uI.DiSpLaY(LOAN.toString());
		
		uI.SeT_StAtE(BorrowBookUI.uI_STaTe.COMPLETED);
		sTaTe = CONTROL_STATE.COMPLETED;
	}

	
	public void CaNcEl() {
		uI.SeT_StAtE(BorrowBookUI.uI_STaTe.CANCELLED);
		sTaTe = CONTROL_STATE.CANCELLED;
	}
	
	
}
