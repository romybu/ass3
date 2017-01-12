package bgu.spl171.net.api.bidi;

import bgu.spl171.net.api.MessagingProtocol;
import bgu.spl171.net.api.bidi.Packets.Packet;
import bgu.spl171.net.srv.BlockingConnectionHandler;
import bgu.spl171.net.srv.ConnectionHandler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.function.Supplier;

/**
 * Created by romybu on 11/01/17.
 */
public class ConnectionsImpl implements Connections<Packet>{
    private HashMap<Integer, ConnectionHandler> allConnections=new HashMap<Integer, ConnectionHandler>();
    private int numOfConnections=0;
    private final Supplier<BlockingConnectionHandlerNew> protocolFactory;




    boolean send(int connectionId, Packet msg);


    public void broadcast(Packet msg){
        for (int key: allConnections.keySet()){
            send(key,msg);
        }

    }

    public void disconnect(int connectionId){
        allConnections.remove(connectionId);
    }

    public void addToConnections(){
        allConnections.put(numOfConnections, )
    }
}
