package bgu.spl171.net.api;

import bgu.spl171.net.api.bidi.Packets.Packet;
import bgu.spl171.net.api.bidi.Packets.*;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Created by romybu on 11/01/17.
 */
public class MessageEncoderDecoderImp implements MessageEncoderDecoder<Packet> {
    private int counter=0;
    //private int countAck=0;
    byte[] start= new byte[2];
    short opcode=-1;
    boolean isStarted=false;
    Packet toReturn;
    private byte[] bytes = new byte[1 << 10]; //start with 1k
    private int len = 0;


    Packet decodeNextByte(byte nextByte){
        if(counter<2){
            start[counter]=nextByte;
            counter++;
        }
        if (counter==2){
            opcode=bytesToShort(start);
            counter++;
        }
        if (opcode!=-1){
            switch (opcode){
                case 1:{
                    return buildRRQ(nextByte);
                }
                case 2:{
                    return buildWRQ(nextByte);
                }
                case 4:{
                    return buildACK(nextByte);
                }
                case 5:{
                    return buildERROR(nextByte);
                }
                case 6:{

                }
            }
        }
    }

    byte[] encode(Packet message);


    private Packet buildRRQ(byte nextByte) {
        if (!isStarted) {
            toReturn = new RRQ();
            isStarted=true;
        }

        if (nextByte == '0') {
            ((RRQ)toReturn).setString(popString());
            return toReturn;
        }

        pushByte(nextByte);
        return null;

    }

    private Packet buildWRQ(byte nextByte) {
        if (!isStarted) {
            toReturn = new WRQ();
            isStarted=true;
        }

        if (nextByte == '0') {
            ((RRQ)toReturn).setString(popString());
            return toReturn;
        }

        pushByte(nextByte);
        return null;

    }

    private Packet buildACK(byte nextByte){
        if (!isStarted) {
            toReturn = new WRQ();
            isStarted=true;
            counter=10;
        }

        if(counter<12){
            start[counter-10]=nextByte;
            counter++;
        }

        if (counter==12){
            ((ACK)toReturn).setBlockNumber(bytesToShort(start));
            return toReturn;
        }

        return null;

    }

    private Packet buildERROR(byte nextByte){
        if (!isStarted) {
            toReturn = new WRQ();
            isStarted=true;
            counter=10;
        }
        if(counter<12){
            start[counter-10]=nextByte;
            counter++;
        }

        if (counter==12){
            ((ERROR)toReturn).setErrorCode(bytesToShort(start));
            counter++;
            return null;
        }

        if (counter>12) {
            if (nextByte == '0') {
                ((ERROR) toReturn).setErrMsg(popString());
                return toReturn;
            }

            pushByte(nextByte);
        }

        return null;

    }



    public short bytesToShort(byte[] byteArr)
    {
        short result = (short)((byteArr[0] & 0xff) << 8);
        result += (short)(byteArr[1] & 0xff);
        return result;
    }

    public byte[] shortToBytes(short num)
    {
        byte[] bytesArr = new byte[2];
        bytesArr[0] = (byte)((num >> 8) & 0xFF);
        bytesArr[1] = (byte)(num & 0xFF);
        return bytesArr;
    }
    private void pushByte(byte nextByte) {
        if (len >= bytes.length) {
            bytes = Arrays.copyOf(bytes, len * 2);
        }

        bytes[len++] = nextByte;
    }

    private String popString() {
        String result = new String(bytes, 0, len, StandardCharsets.UTF_8);
        len = 0;
        return result;
    }
}
