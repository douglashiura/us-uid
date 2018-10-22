package net.douglashiura.us.run;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

import net.douglashiura.us.AbstractType;
import net.douglashiura.us.CamelCase;
import net.douglashiura.us.ExceptionInExecution;
import net.douglashiura.us.UtilsLog;
import net.douglashiura.us.serial.Results;

public class UInput extends AbstractType {

	public UInput(UUID uuid, String fixtureName, String value) {
		super(uuid, fixtureName, value);
	}

	public void execute(Object instance, Executor executor) throws ExceptionInExecution {
		try {
			Class<?>[] cArg = new Class[] { String.class };
			Method method = instance.getClass().getMethod(CamelCase.set(getFixtureName()), cArg);
			method.invoke(instance,getValue());
			executor.message(getUuid(), Results.OK, null);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NullPointerException e) {
			String msg=e.getMessage();
			if(msg==null||"".equals(msg))
				msg=UtilsLog.toString(e);
			throw new ExceptionInExecution(getUuid(), Results.ERROR, msg);
		}
	}

}
