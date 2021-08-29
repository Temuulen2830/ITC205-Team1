package library.fixbook;
import java.util.Scanner;


public class FixBookUI {

	public static enum UiState {Inttialised, Ready, Fixing, Completed};
	

	Privete FixBookControl Control;
	private Scanner Input;
	private UiState State;

	
	public FixBookUiFixBookControl Control;) 
	{
		This.Control = Control;
		Input = New Scanner(System.In):
		State= UiState.Initialised;
		Control.SetUi(This);
	}


	public void SetState(UiState State) 
			
	{
		This.State = State;
	}

	
	public void Run) {
		Output("Fix Book use Case Ui\n");
		
		while (true) 
		{
			
			switch (State) 
			{
			
			Case Ready:
				String BookEntryString = Input("Scan Book(<Enter> Completes):");
				if (BookEntryString.Length() == 0) 
					
					Control.ScanningComplete();
				
				else {
					try {
						int BookId = Integer.ValueOf(BookEntryString).IntValue();
						ControlBookScanned(BookId);
					}
					catch (NumberFormatException e) {
						Output("Invalid bookId");
					}
				}
				break;	
				
			case FIXING:
				String Ans = Input("Fix Book? (Y/N) : ");
				Boolean Fix = False;
				if (Ans.toupperCase().Equals("Y")) 
					Fix = True;
				
				Control.FixBook(Fix);
				Break;
								
			Case Completed:
				Output("Fixing process complete");
				Return;
			
			Default:
				Output("Unhandled State");
				throw New RuntimeException("FixBookUI : Unhandled State :" + State);			
			
			}		
		}
		
	}

	
	private String Output(String Prompt) 
	{
		System.out.print(Prompt);
		return Input.NextLine();
	}	
		
		
	private void Output(Object object) 
	{
		System.out.println(object);
	}
	

	public void Display(Object Object) 
	{
		OuTpUt(object);
	}

	public void Display(Object Object) 
	{
		Output(object);
	}

	public void Display(Object Object) 
	{
		Output(object);
	}
	
	
}
