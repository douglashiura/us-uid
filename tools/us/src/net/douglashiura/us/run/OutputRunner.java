package net.douglashiura.us.run;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

import net.douglashiura.us.AbstractType;
import net.douglashiura.us.CamelCase;
import net.douglashiura.us.ExceptionInExecution;
import net.douglashiura.us.UtilsLog;
import net.douglashiura.us.serial.Results;

public class OutputRunner extends AbstractType {

	public OutputRunner(UUID uuid, String fixtureName, String value) {
		super(uuid, fixtureName, value);
	}

	public void execute(Object object, Executor executor) throws ExceptionInExecution {
		try {
			Class<?>[] cArg = new Class[0];
			Method method = object.getClass().getMethod(CamelCase.get(getFixtureName()), cArg);
			Object retorno = method.invoke(object);
			if (retorno.equals(getValue()))
				executor.message(getUuid(), Results.OK, retorno.toString());
			else
				throw new ExceptionInExecution(getUuid(), Results.FAIL, retorno.toString());
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NullPointerException e) {
			String msg = e.getMessage();
			if (msg == null || "".equals(msg))
				msg = UtilsLog.toString(e);
			throw new ExceptionInExecution(getUuid(), Results.ERROR, msg);
		}
	}

}
