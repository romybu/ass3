package bgu.spl171.net.api.bidi;

/**
 * Created by romybu on 11/01/17.
 */
public class RRQ extends PacketsWithString {
    private Short opcode;

    public RRQ(){
        super();
        opcode=1;
    }

    public Short getOpcode() {
        return opcode;
    }
}
