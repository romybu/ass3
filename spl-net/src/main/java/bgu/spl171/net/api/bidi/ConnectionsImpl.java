package bgu.spl171.net.api.bidi;

import bgu.spl171.net.api.MessagingProtocol;
import bgu.spl171.net.api.bidi.Packets.Packet;
import bgu.spl171.net.srv.BlockingConnectionHandler;
import bgu.spl171.net.srv.ConnectionHandler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/**
 * Created by romybu on 11/01/17.
 */
public class ConnectionsImpl<T> implements Connections<T>{
    private ConcurrentHashMap<Integer, ConnectionHandler> allConnections=new ConcurrentHashMap<Integer, ConnectionHandler>();
    private AtomicInteger numOfConnections=new AtomicInteger();


    public boolean send(int connectionId, T msg) {
        if (!allConnections.containsKey(connectionId))
            return false;
        allConnections.get(connectionId).send(msg); //need to check that connection id exists - could be null
        return true;
    }


    public void broadcast(T msg){
        for (int key: allConnections.keySet()){
            send(key,msg);
        }
    }

    public void disconnect(int connectionId){
        allConnections.remove(connectionId);
    }

    public void addToConnections(ConnectionHandler<T> connectionHandler){
        allConnections.put(numOfConnections.get(), connectionHandler);
    }
}
