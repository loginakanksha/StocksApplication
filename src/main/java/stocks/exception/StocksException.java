package stocks.exception;

public class StocksException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 529558829135639677L;
	
	private String errorCode;
	private String errorMessage;
	
	
	public StocksException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode.getErrorCode();
		this.errorMessage = errorCode.getMessage();
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
