package stocks.exception;

public enum ErrorCode {
	SA_SUCCESS("200", "SUCCESS"),
	SA_BADREQUEST("400", "Bad Request"),
	SA_INVALID_STOCKID("400", "Bad Request: Invalid Stock Id"),
	SA_STOCKID_DUPLICATE("400", "Can't add! Stock Id already present"),
	SA_BLANK_STOCKID("400", "StockId can't be blank"),
	SA_BLANK_NAME("400", "Stock name can't be blank"),
	SA_BLANK_PRICE("400", "Stock price can't be blank"),
	SA_INVALID_PRICE("400", "Price is invalid");
	
	private final String code;
	private final String message;
	
	private ErrorCode(final String code, final String message){
		this.code = code;
		this.message= message;
	}
	
	public String getErrorCode(){
		return this.code;
	}
	
	public String getMessage(){
		return this.message;
	}
}
