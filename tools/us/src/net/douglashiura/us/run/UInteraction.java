package net.douglashiura.us.run;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.douglashiura.picon.ProblemaDeCompilacaoException;
import net.douglashiura.us.ExceptionInExecution;
import net.douglashiura.us.UtilsLog;
import net.douglashiura.us.serial.Results;

public class UInteraction {
	private String fixtureName;
	private UTransaction transaction;
	private List<UInput> inputs;
	private List<UOutput> outputs;
	private UUID uuid;

	public UInteraction(UUID uuid, String fixtureName) {
		this.uuid = uuid;
		this.fixtureName = fixtureName;
		this.inputs = new ArrayList<>();
		this.outputs = new ArrayList<>();
	}

	public String getFixtureName() {
		return fixtureName;
	}

	public void execute(Executor executor) throws ExceptionInExecution {
		Object instance = null;
		try {
			Class<?> klass = Annotations.getFixture(fixtureName);
			instance = klass.newInstance();
			executor.message(uuid, Results.OK, null);
			executor.getPicon().settings(instance);
		} catch (InstantiationException | IllegalAccessException | NullPointerException | IOException
				| ClassNotFoundException | IllegalArgumentException | URISyntaxException
				| ProblemaDeCompilacaoException e) {
			String msg = e.getMessage();
			if (msg == null || "".equals(msg))
				msg = UtilsLog.toString(e);
			throw new ExceptionInExecution(uuid, Results.ERROR, msg);
		}
		for (UOutput aOutput : outputs)
			aOutput.execute(instance, executor);
		for (UInput aInput : inputs)
			aInput.execute(instance, executor);
		if (transaction != null)
			transaction.execute(instance, executor);

	}

	public UTransaction getTransaction() {
		return transaction;
	}

	public void setTransaction(UTransaction transaction2) {
		this.transaction = transaction2;
	}

	public List<UInput> getInputs() {
		return inputs;
	}

	public void addInput(UInput input) {
		inputs.add(input);

	}

	public List<UOutput> getOutputs() {
		return outputs;
	}

	public void addOutput(UOutput output) {
		outputs.add(output);
	}

	public UUID getUuid() {
		return uuid;
	}

}
