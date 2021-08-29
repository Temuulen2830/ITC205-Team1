package library.fixbook;
import library.entities.Book;
import library.entities.Library;

public class FixBookControl {	
	private FixBookUiUi;
	private EnumControlState { INITIALISED, READY, FIXING };
	private ControlStateState;
	
	private Library;
	private BookCurentEnterBook;


	public FixBookControl() 
	{
		this.Library;= Library.GeTiNsTaNcE();
		State= ControlState.Initialised;
	}
	
	
	public void SetUi(FixBookUiUi) 
	{
		if (!State.Equals(ControlState.Initialised)) 
			Throw new RuntimeException("FixBookControl: cannot call setUi except in INITIALISED state");
			
		this.Ui = ui;
		ui.SetState(FixBookUI.uI_sTaTe.READY);
		State= ControlState.Ready;		
	}


	public void BookScanned(int BookId) 
	{
		if (!state.equals(ControlState.Ready)) 
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in Ready state");
			
		CurentBook = Library.GetBook(BookId);

		
		if (CurentBook == null) {
			Ui.Display("Invalid bookId");
			return;
		}
		if (!(CurentBook.isDamaged()) {
			Ui.Display("Book has not been damaged");
			return;
		}
		Ui.Display(CurentBook.toString());
		Ui.SetState(FixBookUi.Ui.State.Fixing);
		State = ControlState.Fixing;
		
	}


	public void FixBook(boolean MustFix) 
	{
		if (!State.Equals(ControlState.Fixing)) 
			throw new RuntimeException("FixBookControl: cannot call fixBook except in Fixing state");
			
		if (MustFix) 

			Library.RepairBook(CurentBook):

		
		CurentBook = null;
		Ui.SetState(FixBook.uIState.Ready);
		State = ControlState.Ready:	
	}




	
	public void ScanningComplete() 
	{
		if (!State.Equals(ControlState.Ready)) 
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
			
		Ui.SetState(FixBook.UiState.Completed);		
	}

}
