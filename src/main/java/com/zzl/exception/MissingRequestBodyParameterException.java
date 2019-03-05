/**
 *
 */
package com.zzl.exception;

import org.springframework.core.NestedRuntimeException;

/**
 * @author lute
 */
public class MissingRequestBodyParameterException extends NestedRuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 9118152228019296954L;

    private String parameterName;

    /**
     * @param parameterName
     */
    public MissingRequestBodyParameterException(String parameterName) {
        super("");
        this.parameterName = parameterName;
    }

    /* (non-Javadoc)
     * @see org.springframework.core.NestedRuntimeException#getMessage()
     */
    @Override
    public String getMessage() {
        return "Required request body parameter '" + this.parameterName + "' is not present";
    }

    /**
     * @return the parameterName
     */
    public final String getParameterName() {
        return parameterName;
    }

}
