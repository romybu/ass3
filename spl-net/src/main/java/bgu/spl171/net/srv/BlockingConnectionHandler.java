package bgu.spl171.net.srv;

import bgu.spl171.net.api.MessageEncoderDecoder;
import bgu.spl171.net.api.MessagingProtocol;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import bgu.spl171.net.api.bidi.*;
import bgu.spl171.net.srv.bidi.*;

public class BlockingConnectionHandler<T> implements Runnable, ConnectionHandler<T> {

    private final BidiMessagingProtocol<T> protocol;
    private final MessageEncoderDecoder<T> encdec;
    private final Socket sock;
    private BufferedInputStream in;
    private BufferedOutputStream out;
    private volatile boolean connected = true;

    public BlockingConnectionHandler(Socket sock, MessageEncoderDecoder<T> reader, BidiMessagingProtocol<T> protocol) {
        this.sock = sock;
        this.encdec = reader;
        this.protocol = protocol;
    }

    @Override
    public void run() {

        try (Socket sock = this.sock) { //just for automatic closing
            int read;

            in = new BufferedInputStream(sock.getInputStream());


            while (!protocol.shouldTerminate() && connected && (read = in.read()) >= 0) {
                T nextMessage = encdec.decodeNextByte((byte) read);
                if (nextMessage != null) {
                    //T response = protocol.process(nextMessage);
                    protocol.process(nextMessage);
                    //if (response != null) {
                      //  out.write(encdec.encode(response));
                     //   out.flush();
                    //}
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void close() throws IOException {
        connected = false;
        sock.close();
    }

    public void send(T msg){
        try(Socket sock = this.sock) {
            out = new BufferedOutputStream(sock.getOutputStream());
            //if (msg != null) {
                out.write(encdec.encode(msg));
                out.flush();
            //}
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
