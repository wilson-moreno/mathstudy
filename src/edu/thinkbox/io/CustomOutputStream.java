package edu.thinkbox.io;

import java.io.IOException;
import java.io.OutputStream;
import javafx.scene.control.TextArea;

/**
 * This class extends from OutputStream to redirect output to a JTextArrea
 * @author www.codejava.net
 *
 */
public class CustomOutputStream extends OutputStream {
    private TextArea textArea;

    public CustomOutputStream(TextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void write(int b) throws IOException {
        textArea.appendText( String.valueOf( ( char ) b ) );
        textArea.positionCaret( textArea.getLength() );
    }
}
