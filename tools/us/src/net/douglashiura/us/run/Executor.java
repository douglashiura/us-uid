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
import net.douglashiura.us.ScenarioFromText;
import net.douglashiura.us.picon.PiconWithUsuid;
import net.douglashiura.us.serial.InputFile;
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
		}
	}

	public void execute(InteractionRunner firstState) throws UnknownHostException, IOException {
		try {
			firstState.execute(this);
			message(null, Results.END, null);
		} catch (ExceptionInExecution e) {
			message(e.getUuid(), e.getResult(), e.getMessage());
		}
		write.flush();
	}

	public void message(UUID uuid, Results result, String actual) {
		try {
			write.writeObject(new Result(uuid, result, actual));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		Socket client = new Socket("localhost", 6969);
		ObjectInputStream entrada = new ObjectInputStream(client.getInputStream());
		ObjectOutputStream inputStream = new ObjectOutputStream(client.getOutputStream());
		InputFile message;
		try {
			while ((message = (InputFile) entrada.readObject()) != null) {
				Executor executor = new Executor(inputStream);
				executor.execute(new ScenarioFromText(message.getContent()).firstState());
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