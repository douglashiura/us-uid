package net.douglashiura.us.project.util;

import net.douglashiura.us.serial.Result;
import net.douglashiura.us.serial.Results;
import net.douglashiura.usuid.plugin.type.Rateable;

public interface Notificable {

	void addResult(Result result, Rateable element);

	void finishyATestExecution(Results generalResult);

}
