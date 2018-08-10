package net.douglashiura.us;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.douglashiura.us.serial.Result.Results;

public class UOutput extends AbstractType {

	public UOutput(String id, String model, String value) {
		super(id, model, value);
	}

	public void execute(Object object, Executor executor) throws ExceptionInExecution {
		try {
			Class<?>[] cArg = new Class[0];
			Method method = object.getClass().getMethod(CamelCase.get(getModel()), cArg);
			Object retorno = method.invoke(object);
			if (retorno.equals(getValue()))
				executor.message(getId(), Results.OK, retorno.toString());
			else
				throw new ExceptionInExecution(getId(), Results.FAIL, retorno.toString());
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NullPointerException e) {
			String msg = e.getMessage();
			if (msg == null || "".equals(msg))
				msg = UtilsLog.toString(e);
			throw new ExceptionInExecution(getId(), Results.ERRO, msg);
		}
	}

}
