package bgu.spl171.net.api.bidi;

/**
 * Created by alonam on 1/11/17.
 */
public class ACK implements Packet{
    short opcode;
    short blockNumber;

    public ACK(){
        opcode=4;
        blockNumber=-1;
    }

    public short getOpcode() {
        return opcode;
    }

    public short getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(short blockNumber) {
        this.blockNumber = blockNumber;
    }
}
