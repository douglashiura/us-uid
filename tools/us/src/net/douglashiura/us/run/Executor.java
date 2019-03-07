package net.douglashiura.us.run;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.UUID;

import net.douglashiura.picon.ProblemaDeCompilacaoException;
import net.douglashiura.us.ExceptionInExecution;
import net.douglashiura.us.picon.PiconWithUsuid;
import net.douglashiura.us.serial.InputFile;
import net.douglashiura.us.serial.Interaction;
import net.douglashiura.us.serial.Result;
import net.douglashiura.us.serial.Results;

public class Executor {

	private ObjectOutputStream write;
	private PiconWithUsuid picon;

	public Executor(ObjectOutputStream write) {
		this.write = write;
		try {
			picon = new PiconWithUsuid();
		} catch (ProblemaDeCompilacaoException e) {
			message(null, 0, Results.END, e.getMessage());
		}
	}

	public void execute(Interaction firstState, Integer index) throws UnknownHostException, IOException {
		try {
			new RecursiveExecutor(this, firstState, index);
			message(null, index, Results.END, null);
		} catch (ExceptionInExecution e) {
			message(e.getUuid(), index, e.getResult(), e.getMessage());
		}
		write.flush();
	}

	public void message(UUID uuid, Integer index, Results result, String actual) {
		try {
			write.writeObject(new Result(uuid, index, result, actual));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		Socket client = new Socket("localhost", 6969);
		ObjectInputStream inputStream = new ObjectInputStream(client.getInputStream());
		ObjectOutputStream outputStream = new ObjectOutputStream(client.getOutputStream());
		InputFile message;
		Integer code = 0;
		try {
			while ((message = (InputFile) inputStream.readObject()) != null) {
				Executor executor = new Executor(outputStream);
				executor.execute(message.getScenario(), message.getIndex());
			}
		} catch (EOFException error) {
			error.printStackTrace();
			code = 1;
		} finally {
			exit(client, inputStream, outputStream, code);
		}
	}

	private static void exit(Socket client, ObjectInputStream inputStream, ObjectOutputStream outputStream, int code)
			throws IOException {
		try {
			inputStream.close();
		} catch (Exception e) {
		}
		try {
			outputStream.close();
		} catch (Exception e) {
		}
		try {
			client.close();
		} catch (Exception e) {
		}
		System.exit(code);
	}

	public PiconWithUsuid getPicon() {
		return picon;
	}

}