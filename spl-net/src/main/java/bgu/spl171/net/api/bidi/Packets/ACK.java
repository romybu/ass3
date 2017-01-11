package bgu.spl171.net.api.bidi.Packets;

//TODO: check where the block number is updated


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
