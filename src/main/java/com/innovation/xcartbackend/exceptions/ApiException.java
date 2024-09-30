package com.innovation.xcartbackend.exceptions;

/**
 * A custom exception which extends RunTime Exception in java.
 * @author Ajinkya.Deshpande
 *
 */
public class ApiException extends RuntimeException{

/**
 * Constructor
 * @param message
 */
	 /** Constructs a new runtime exception with the specified cause and a
     * detail message of {@code (cause==null ? null : cause.toString())}
     * (which typically contains the class and detail message of
     * {@code cause}).  This constructor is useful for runtime exceptions
     * that are little more than wrappers for other throwables.
     *
     * @param  cause the cause (which is saved for later retrieval by the
     *         {@link #getCause()} method).  (A {@code null} value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     * 
     */
	public ApiException(String message) {
		super(message);
		
	}

	/**
	 * A No Args Constructor.
	 */
	public ApiException() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
