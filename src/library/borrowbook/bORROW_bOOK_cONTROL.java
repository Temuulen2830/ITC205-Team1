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
	
	
	public void ScAnNeD(int bOoKiD) {
		bOoK = null;
		if (!sTaTe.equals(CONTROL_STATE.SCANNING)) 
			throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
			
		bOoK = lIbRaRy.gEt_BoOk(bOoKiD);
		if (bOoK == null) {
			uI.DiSpLaY("Invalid bookId");
			return;
		}
		if (!bOoK.iS_AvAiLaBlE()) {
			uI.DiSpLaY("Book cannot be borrowed");
			return;
		}
		pEnDiNg_LiSt.add(bOoK);
		for (Book B : pEnDiNg_LiSt) 
			uI.DiSpLaY(B.toString());
		
		if (lIbRaRy.gEt_NuMbEr_Of_LoAnS_ReMaInInG_FoR_MeMbEr(mEmBeR) - pEnDiNg_LiSt.size() == 0) {
			uI.DiSpLaY("Loan limit reached");
			CoMpLeTe();
		}
	}
	
	
	public void CoMpLeTe() {
		if (pEnDiNg_LiSt.size() == 0) 
			CaNcEl();
		
		else {
			uI.DiSpLaY("\nFinal Borrowing List");
			for (Book bOoK : pEnDiNg_LiSt) 
				uI.DiSpLaY(bOoK.toString());
			
			cOmPlEtEd_LiSt = new ArrayList<Loan>();
			uI.SeT_StAtE(BorrowBookUI.uI_STaTe.FINALISING);
			sTaTe = CONTROL_STATE.FINALISING;
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
