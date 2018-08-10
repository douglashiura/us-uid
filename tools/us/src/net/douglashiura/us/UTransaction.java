package net.douglashiura.us;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.douglashiura.us.serial.Result.Results;

public class UTransaction {

	private UInteraction aTarget;
	private String id;

	public UTransaction(String id, UInteraction aSource, UInteraction aTarget) {
		this.id = id;
		this.aTarget = aTarget;
	}

	public UInteraction getTarget() {
		return aTarget;
	}

	public String getId() {
		return id;
	}

	public void execute(Object instance, Executor executor) throws ExceptionInExecution {
		try {
			Class<?>[] cArg = new Class[0];
			Method method = instance.getClass().getMethod(CamelCase.to(aTarget.getModel()), cArg);
			method.invoke(instance);
			executor.message(getId(), Results.OK, null);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NullPointerException e) {
			String msg=e.getMessage();
			if(msg==null||"".equals(msg))
				msg=UtilsLog.toString(e);
			throw new ExceptionInExecution(getId(), Results.ERRO, msg);
		}
		aTarget.execute(executor);
	}

}
