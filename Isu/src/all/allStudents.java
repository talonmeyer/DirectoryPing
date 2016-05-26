package all;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JEditorPane;

public class allStudents {

	public static void main(String[] args) throws MalformedURLException, IOException {
		JEditorPane editorPane = new JEditorPane();
		editorPane.setPage(new URL("http://www.google.com"));

	}

}
