package bgu.spl171.net.api.bidi;

/**
 * Created by romybu on 11/01/17.
 */
public class PacketsWithString implements Packet {
    private String string;
    private byte lastByte;

    public PacketsWithString(){
        string="";
        lastByte=0;
    }
    public String getString() {
        return string;
    }

    public void setString(String str) {
        string = string+str;
    }

    public byte getLastByte() {
        return lastByte;
    }

}
