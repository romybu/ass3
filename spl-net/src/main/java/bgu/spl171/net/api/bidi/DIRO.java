package bgu.spl171.net.api.bidi;

/**
 * Created by romybu on 11/01/17.
 */
public class DIRO implements Packet {
    private Short opcode;

    public DIRO(){
        opcode=6;
    }

    public Short getOpcode() {
        return opcode;
    }
}
