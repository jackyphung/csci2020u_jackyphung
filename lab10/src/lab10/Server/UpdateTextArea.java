package lab10.Server;

import javafx.scene.control.TextArea;

public class UpdateTextArea {
    public static TextArea text;
    public static String messages = "";

    public UpdateTextArea(TextArea text){
        this.text = text;
    }

    public static void updateText(String message){
        messages += message + "\n\n";
        text.setText(messages);
    }
}
