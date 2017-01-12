package bgu.spl171.net.api.bidi;

import bgu.spl171.net.api.bidi.Packets.Packet;

import java.util.HashMap;

/**
 * Created by romybu on 11/01/17.
 */
public class BidiMessagingProtocolPacket implements BidiMessagingProtocol<Packet> {
    private static HashMap map;
    private Connections<Packet> connections;
    private int connectionId;
    private boolean shouldTerminate = false;

    public void start(int connectionId, Connections<Packet> connections){
        this.connections =connections ;
        this.connectionId=connectionId;
    }

    public void process(Packet message){
        short opcode=message.getOpcode();
        switch (opcode){
            case 1:{

            }
        }
    }

    public boolean shouldTerminate(){
        return  shouldTerminate;
    }
}
