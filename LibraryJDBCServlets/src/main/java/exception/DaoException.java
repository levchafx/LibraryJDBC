package exception;

public class DaoException extends Exception {
	private static final long serialVersionUID = -8701662673949824535L;

	public DaoException(String text) {
		super(text);
	}

	public DaoException(String text, Exception innerEx) {
		super(text, innerEx);

	}
}
