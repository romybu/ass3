package bgu.spl171.net.api.bidi.Packets;


public class DATA implements Packet{

    short opcode;
    short packetSize;
    short blockNumber;
    byte[] data;

    public DATA(){
        opcode=3;
        packetSize=-1;
        blockNumber=-1;
    }

    public short getOpcode() {
        return opcode;
    }


    public short getPacketSize() {
        return packetSize;
    }

    public void setPacketSize(short packetSize) {
        this.packetSize = packetSize;
    }

    public short getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(short blockNumber) {
        this.blockNumber = blockNumber;
    }

    public byte[] getData() {
        byte[] ans=new byte[data.length];
        for (int i=0; i<data.length; i++){
            ans[i]=data[i];
        }
        return ans;
    }

    public void initData(short size) {
        this.data = new byte[size];
    }

    //TODO: check this function. its not good
    public void setData(byte[] arr){
        for (int i=0; i<Math.min(data.length,arr.length); i++){
            data[i]=arr[i];
        }
    }
}
