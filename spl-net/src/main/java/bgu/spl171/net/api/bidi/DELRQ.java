package bgu.spl171.net.api.bidi;

/**
 * Created by romybu on 11/01/17.
 */
public class DELRQ  extends PacketsWithString {
    private Short opcode;

    public DELRQ(){
        super();
        opcode=8;
    }

    public Short getOpcode() {
        return opcode;
    }
}
