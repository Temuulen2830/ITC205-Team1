package library.payfine;
import library.entities.*;

public class PayFineControl {

	private PayFineUI ui;    //Changed Ui to ui
	private enum ControlState { INITIALISED, READY, PAYING, COMPLETED, CANCELLED }    //Changed cOnTrOl_sTaTe to ControlState
	private ControlState  state;    //Changed cOnTrOl_sTaTe to ControlState  and StAtE to state

	private Library library;    //Changed LiBrArY to library
	private Member member;    //Changed MeMbEr to member

	public PayFineControl() {
		library = Library.getInstance();    //Changed LiBrArY to library and GeTiNsTaNcE() to getInstance() and removed this.
		state = ControlState .INITIALISED;    //Changed and StAtE to state and cOnTrOl_sTaTe to ControlState
	}

	public void setUi(PayFineUI uI) {    //Changed SeT_uI to setUi
		if (!state.equals(ControlState .INITIALISED)) {    //Changed and StAtE to state and cOnTrOl_sTaTe to ControlState
			throw new RuntimeException("PayFineControl: cannot call setUI except in INITIALISED state");
		}

		this.ui = uI;    //Changed Ui to ui
		uI.setUi(PayFineUI.UiState.READY);    //Changed SeT_StAtE to setUi and uI_sTaTe to UiState
		state = ControlState .READY;    //Changed and StAtE to state and cOnTrOl_sTaTe to ControlState
	}

	public void cardSwiped(int memberId) {    //Changed CaRd_sWiPeD to cardSwiped and MeMbEr_Id to memberId
		if (!state.equals(ControlState .READY)) {    //Changed and StAtE to state and cOnTrOl_sTaTe to ControlState
			throw new RuntimeException("PayFineControl: cannot call cardSwiped except in READY state");
		}

		member = library.getMember(memberId);    //Changed MeMbEr to member, LiBrArY to library, gEt_MeMbEr tp getMember and MeMbEr_Id to memberId

		if (member == null) {    //Changed MeMbEr to member
			ui.display("Invalid Member Id");    //Changed DiSplAY to display and Ui to ui
			return;
		}

		ui.display(member.toString());    //Changed Ui to ui, DiSplAY to display and MeMbEr to member
		ui.setUi(PayFineUI.UiState.PAYING);    //Changed Ui to ui, SeT_StAtE to setUi and uI_sTaTe to UiState
		state = ControlState .PAYING;    //Changed and StAtE to state and cOnTrOl_sTaTe to ControlState
	}

	public void cancel() {    //Changed CaNcEl to cancel
		ui.setUi(PayFineUI.UiState.CANCELLED);    //Changed Ui to ui, SeT_StAtE to setUi and uI_sTaTe to UiState
		state = ControlState .CANCELLED;    //Changed and StAtE to state and cOnTrOl_sTaTe to ControlState
	}

	public double payFine(double amount) {    //Changed PaY_FiNe to payFine and AmOuNt to amount
		if (!state.equals(ControlState.PAYING)) {    //Changed and StAtE to state and cOnTrOl_sTaTe to ControlState
			throw new RuntimeException("PayFineControl: cannot call payFine except in PAYING state");
		}
		double change = member.payFine(amount);    //Changed ChAnGe to change, MeMbEr to member, PaY_FiNe to payFine and AmOuNt to amount
		if (change > 0) {    //Changed ChAnGe to change
			ui.display(String.format("Change: $%.2f", change));    //Changed Ui to ui, DiSplAY to display and ChAnGe to change
		}
		ui.display(member.toString());    //Changed Ui to ui, DiSplAY to display and MeMbEr to member
		ui.setUi(PayFineUI.UiState.COMPLETED);    //Changed Ui to ui, SeT_StAtE to setUi and uI_sTaTe to UiState
		state = ControlState.COMPLETED;    //Changed and StAtE to state and cOnTrOl_sTaTe to ControlState
		return change;    //Changed ChAnGe to change
	}
}
