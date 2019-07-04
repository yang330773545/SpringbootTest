package com.yang.exception;

public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = -3746988827589677426L;

	public UnauthorizedException(String msg) {
        super(msg);
    }
 
    public UnauthorizedException() {
        super();
    }

}
