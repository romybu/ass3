package bgu.spl171.net.api.bidi;

/**
 * Created by alonam on 1/11/17.
 */
public class DISC implements Packet {

    short opcode;

    public DISC(){
        opcode=10;
    }
}
