package com.glavsoft.viewer;

import com.glavsoft.exceptions.CommonException;

/**
 * @author dime at tightvnc.com
 */
public class CancelConnectionException extends CommonException {

    public CancelConnectionException(String message) {
        super(message);
    }
}
