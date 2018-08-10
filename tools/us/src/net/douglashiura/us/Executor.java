package net.douglashiura.us;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import net.douglashiura.picon.ProblemaDeCompilacaoException;
import net.douglashiura.us.picon.PiconWithUsuid;
import net.douglashiura.us.serial.Input;
import net.douglashiura.us.serial.Result;
import net.douglashiura.us.serial.Result.Results;

public class Executor {

	private ObjectOutputStream write;
	private PiconWithUsuid picon;

	public Executor(ObjectOutputStream write) {
		this.write = write;
		try {
			picon = new PiconWithUsuid();
		} catch (ProblemaDeCompilacaoException e) {
		}
	}

	public void execute(UInteraction firstState) throws UnknownHostException, IOException {
		try {
			firstState.execute(this);
			message(null, Results.END, null);
		} catch (ExceptionInExecution e) {
			message(e.getId(), e.getResult(), e.getMensagem());
		}
		write.flush();
	}

	public void message(String id, Results result, String actual) {
		try {
			write.writeObject(new Result(id, result, actual));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		Socket client = new Socket("localhost", 6969);
		ObjectInputStream entrada = new ObjectInputStream(client.getInputStream());
		ObjectOutputStream inputStream = new ObjectOutputStream(client.getOutputStream());
		Input message;
		try {
			while ((message = (Input) entrada.readObject()) != null) {
				Executor executor = new Executor(inputStream);
				executor.execute(new UScenario(message.getContent()).firstState());
			}
		} catch (EOFException terminouAntes) {

		} finally {
			client.close();
		}
	}

	public PiconWithUsuid getPicon() {
		return picon;
	}

}