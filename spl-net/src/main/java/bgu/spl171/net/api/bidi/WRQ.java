package bgu.spl171.net.api.bidi;

/**
 * Created by romybu on 11/01/17.
 */
public class WRQ extends PacketsWithString {
    private Short opcode;

    public WRQ(){
        super();
        opcode=2;
    }

    public Short getOpcode() {
        return opcode;
    }
}
