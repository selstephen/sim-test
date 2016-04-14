package com.Simudyne;

/**
 * Created by selwynstephen on 14/04/16.
 */
public enum AutoRenew {
    DO_NOT_AUTO_RENEW (0), AUTO_RENEW(1);

    private final Integer value;
    private AutoRenew(Integer value) {
        this.value = value;
    }
    public Integer getValue(){
        return  value;
    }
}
