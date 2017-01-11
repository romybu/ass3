package bgu.spl171.net.api.bidi;

import bgu.spl171.net.api.bidi.Packets.Packet;

import java.util.HashMap;

/**
 * Created by romybu on 11/01/17.
 */
public class BidiMessagingProtocolPacket implements BidiMessagingProtocol<Packet> {
    private static HashMap<String userName, int id>();

    void start(int connectionId, Connections<T> connections){}

    void process(T message){}

    boolean shouldTerminate(){}
}
