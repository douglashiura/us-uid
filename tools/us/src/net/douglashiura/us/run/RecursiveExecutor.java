package net.douglashiura.us.run;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;

import net.douglashiura.picon.ProblemaDeCompilacaoException;
import net.douglashiura.us.CamelCase;
import net.douglashiura.us.ExceptionInExecution;
import net.douglashiura.us.UtilsLog;
import net.douglashiura.us.serial.Input;
import net.douglashiura.us.serial.Interaction;
import net.douglashiura.us.serial.Output;
import net.douglashiura.us.serial.Results;
import net.douglashiura.us.serial.Transaction;

public class RecursiveExecutor {

	private Executor executor;

	public RecursiveExecutor(Executor executor, Interaction firstState) throws ExceptionInExecution {
		this.executor = executor;
		execute(firstState);
	}

	private void execute(Interaction interaction) throws ExceptionInExecution {
		Object instance = null;
		try {
			Class<?> klass = Annotations.getFixture(interaction.getFixtureName());
			instance = klass.getConstructors()[0].newInstance();
			executor.message(interaction.getUuid(), Results.OK, null);
			executor.getPicon().settings(instance);
		} catch (InstantiationException | IllegalAccessException | NullPointerException | IOException
				| ClassNotFoundException | IllegalArgumentException | URISyntaxException | ProblemaDeCompilacaoException
				| InvocationTargetException | SecurityException e) {
			String msg = e.getMessage();
			if (msg == null || "".equals(msg))
				msg = UtilsLog.toString(e);
			throw new ExceptionInExecution(interaction.getUuid(), Results.ERROR, msg);
		}
		for (Output aOutput : interaction.getOutputs()) {
			execute(aOutput, instance);
		}
		for (Input aInput : interaction.getInputs()) {
			execute(aInput, instance);
		}
		if (interaction.getTransaction() != null) {
			execute(interaction.getTransaction(), instance);
		}
	}

	private void execute(Transaction transaction, Object instance) throws ExceptionInExecution {
		try {
			Class<?>[] cArg = new Class[0];
			Method method = instance.getClass().getMethod(CamelCase.to(transaction.getTarget().getFixtureName()), cArg);
			method.invoke(instance);
			executor.message(transaction.getUuid(), Results.OK, null);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NullPointerException e) {
			String msg = e.getMessage();
			if (msg == null || "".equals(msg))
				msg = UtilsLog.toString(e);
			throw new ExceptionInExecution(transaction.getUuid(), Results.ERROR, msg);
		}
		execute(transaction.getTarget());
	}

	private void execute(Input input, Object instance) throws ExceptionInExecution {
		try {
			Class<?>[] cArg = new Class[] { String.class };
			Method method = instance.getClass().getMethod(CamelCase.set(input.getFixtureName()), cArg);
			method.invoke(instance, input.getValue());
			executor.message(input.getUuid(), Results.OK, null);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NullPointerException e) {
			String msg = e.getMessage();
			if (msg == null || "".equals(msg))
				msg = UtilsLog.toString(e);
			throw new ExceptionInExecution(input.getUuid(), Results.ERROR, msg);
		}
	}

	private void execute(Output output, Object object) throws ExceptionInExecution {
		try {
			Class<?>[] cArg = new Class[0];
			Method method = object.getClass().getMethod(CamelCase.get(output.getFixtureName()), cArg);
			Object retorno = method.invoke(object);
			if (retorno.equals(output.getValue()))
				executor.message(output.getUuid(), Results.OK, retorno.toString());
			else
				throw new ExceptionInExecution(output.getUuid(), Results.FAIL, retorno.toString());
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NullPointerException e) {
			String msg = e.getMessage();
			if (msg == null || "".equals(msg))
				msg = UtilsLog.toString(e);
			throw new ExceptionInExecution(output.getUuid(), Results.ERROR, msg);
		}
	}
}
