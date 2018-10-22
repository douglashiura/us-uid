package net.douglashiura.us.run;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

import net.douglashiura.us.CamelCase;
import net.douglashiura.us.ExceptionInExecution;
import net.douglashiura.us.UtilsLog;
import net.douglashiura.us.serial.Results;

public class UTransaction {

	private UInteraction target;
	private UUID uuid;

	public UTransaction(UUID uuid, UInteraction source, UInteraction target) {
		this.uuid = uuid;
		this.target = target;
	}

	public UInteraction getTarget() {
		return target;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void execute(Object instance, Executor executor) throws ExceptionInExecution {
		try {
			Class<?>[] cArg = new Class[0];
			Method method = instance.getClass().getMethod(CamelCase.to(target.getFixtureName()), cArg);
			method.invoke(instance);
			executor.message(getUuid(), Results.OK, null);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NullPointerException e) {
			String msg=e.getMessage();
			if(msg==null||"".equals(msg))
				msg=UtilsLog.toString(e);
			throw new ExceptionInExecution(getUuid(), Results.ERROR, msg);
		}
		target.execute(executor);
	}

}
