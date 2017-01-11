package bgu.spl171.net.api.bidi;

/**
 * Created by romybu on 11/01/17.
 */
public class LOGRQ extends PacketsWithString {
    private Short opcode;

    public LOGRQ(){
        super();
        opcode=7;
    }
    public Short getOpcode() {
        return opcode;
    }
}
