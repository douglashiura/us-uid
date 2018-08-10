package net.douglashiura.us;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.douglashiura.us.serial.Result.Results;

public class UInput extends AbstractType {

	public UInput(String id, String model, String value) {
		super(id, model, value);
	}

	public void execute(Object instance, Executor executor) throws ExceptionInExecution {
		try {
			Class<?>[] cArg = new Class[] { String.class };
			Method method = instance.getClass().getMethod(CamelCase.set(getModel()), cArg);
			method.invoke(instance,getValue());
			executor.message(getId(), Results.OK, null);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NullPointerException e) {
			String msg=e.getMessage();
			if(msg==null||"".equals(msg))
				msg=UtilsLog.toString(e);
			throw new ExceptionInExecution(getId(), Results.ERRO, msg);
		}
	}

}
