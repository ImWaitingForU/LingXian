package com.lingxian.lingxian.view;

/**
 * Created by Administrator on 2016/7/12.
 */
public class MissingPropertiesException extends Exception {

    public MissingPropertiesException () {
        super ();
    }

    public MissingPropertiesException (String detailMessage) {
        super (detailMessage);
    }

    public MissingPropertiesException (String detailMessage, Throwable throwable) {
        super (detailMessage, throwable);
    }

    public MissingPropertiesException (Throwable throwable) {
        super (throwable);
    }
}
