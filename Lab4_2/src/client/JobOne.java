package client;

import Interfaces.Executable;

import java.io.Serializable;
import java.math.BigInteger;

public class JobOne implements Executable, Serializable {

    private final static long serialVersionUID = -1;

    @Override
    public Object execute() {
        BigInteger res = BigInteger.ONE;
        return res;
    }
}
