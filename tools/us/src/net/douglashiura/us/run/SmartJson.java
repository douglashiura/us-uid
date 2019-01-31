package net.douglashiura.us.run;

import net.douglashiura.us.Input;
import net.douglashiura.us.Interaction;
import net.douglashiura.us.Output;

public class SmartJson {
	public static final Object INTERACTION = "br.ufsc.leb.uid.scenario.Interacao";
	public static final Object OUTPUT = "br.ufsc.leb.uid.scenario.SystemOutput";
	public static final Object INPUT = "br.ufsc.leb.uid.scenario.UserInput";
	public static final Object TRANSACTION = "br.ufsc.leb.uid.scenario.Transaction";

	public static String from(Interaction interaction, String file, Integer path) {
		StringBuilder builder = new StringBuilder();
		builder.append(String.format("{\"heads\":{\"file\":\"%s\",\"path\":%s},\"elemtens\":[", file, path));
		makeJsonFrom(interaction, builder);
		builder.append("]}");
		return builder.toString();
	}

	private static void makeJsonFrom(Interaction interaction, StringBuilder builder) {
		builder.append("{");
		builder.append(String.format("\"type\":\"%s\",\"id\":\"%s\",\"fixtureName=\":\"%s\"", INTERACTION,
				interaction.getUuid(), interaction.getFixtureName()));
		builder.append("}");
		for (Input input : interaction.getInputs()) {
			builder.append(",{");
			builder.append(String.format(
					"\"type\":\"%s\",\"id\":\"%s\",\"composite\":\"%s\",\"fixtureName\":\"%s\",\"value\":\"%s\"", INPUT,
					input.getUuid(), interaction.getUuid(), input.getFixtureName(), input.getValue()));
			builder.append("}");
		}
		for (Output output : interaction.getOutputs()) {
			builder.append(",{");
			builder.append(String.format(
					"\"type\":\"%s\",\"id\":\"%s\",\"composite\":\"%s\",\"fixtureName\":\"%s\",\"value\":\"%s\"",
					OUTPUT, output.getUuid(), interaction.getUuid(), output.getFixtureName(), output.getValue()));
			builder.append("}");
		}
		if (interaction.getTransaction() != null && interaction.getTransaction().getTarget() != null) {
			builder.append(",{");
			builder.append(String.format("\"type\":\"%s\",\"id\":\"%s\",\"source=\":\"%s\",\"target\":\"%s\"",
					TRANSACTION, interaction.getTransaction().getUuid(), interaction.getUuid(),
					interaction.getTransaction().getTarget().getUuid()));
			builder.append("},");
			makeJsonFrom(interaction.getTransaction().getTarget(), builder);
		}

	}

}
