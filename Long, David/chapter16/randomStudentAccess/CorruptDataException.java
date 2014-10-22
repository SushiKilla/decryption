package randomStudentAccess;

public class CorruptDataException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public CorruptDataException() {super();}
	public CorruptDataException(String message){super(message);}
	public String getMessage(){return super.getMessage();}
}
