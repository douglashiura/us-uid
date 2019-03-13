package test.net.douglashiura;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.UUID;

import org.junit.Test;

import net.douglashiura.us.serial.InputFile;
import net.douglashiura.us.serial.Interaction;

public class TestSerialization {

	@Test
	public void testName() throws Exception {
		byte[] bytes = bytesUtf();
		String string = new String(bytes,"ISO-8859-1");

		//		ByteArrayOutputStream fileutf = new ByteArrayOutputStream();
//		DataOutputStream data = new DataOutputStream(fileutf);
//		data.writeUTF(str);
//		String utf8StringObject = new String(fileutf.toByteArray(), "UTF-8");

//----------------------------------------------
//		ByteArrayInputStream fileutfin = new ByteArrayInputStream(string.getBytes("ISO-8859-1"));
//		DataInputStream datautf = new DataInputStream(fileutfin);
//		bytes = new byte[datautf.available()];
//		datautf.read(bytes);
//		String utf8 = new String(bytes);
//		System.out.println(utf8);
//		bytes = utf8.getBytes("ISO-8859-1");
		System.out.println(string);
		ByteArrayInputStream fileo = new ByteArrayInputStream(string.getBytes("ISO-8859-1"));
		ObjectInputStream inputStream = new ObjectInputStream(fileo);
		assertEquals("hello", inputStream.readObject());
	}

	private byte[] bytesUtf() throws IOException {
		InputFile object = new InputFile("kkkk", new Interaction(UUID.randomUUID(), ""), 1);

		
		ByteArrayOutputStream file = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(file);
		out.writeObject(object);
		byte[] bytes = file.toByteArray();
		return bytes;
	}

}
