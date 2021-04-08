package lab10.Server;

import javafx.scene.control.TextArea;

import java.io.*;
import java.net.*;

public class ServerThread extends Thread {
    protected Socket socket;
    protected TextArea textArea;
    protected String message;

    public ServerThread(Socket socket, TextArea textArea){
        super();
        this.socket = socket;
        this.textArea = textArea;
    }

    public void run(){
        try{
            while(!socket.isClosed()){
                InputStream inputStream = socket.getInputStream();
                DataInputStream dataInputStream = new DataInputStream(inputStream);

                message = dataInputStream.readUTF();
                UpdateTextArea.updateText(message);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
