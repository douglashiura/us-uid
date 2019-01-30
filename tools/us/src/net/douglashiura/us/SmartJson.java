package net.douglashiura.us;

import net.douglashiura.us.run.Scenarios;

public class SmartJson {

	public static String from(Interaction interaction, String file, Integer path) {
		StringBuilder builder = new StringBuilder();
		builder.append(String.format("{\"heads\":{\"file\":\"%s\",\"path\":%s},\"elemtens\":[", file, path));
		makeJsonFrom(interaction, builder);
		builder.append("]}");
		return builder.toString();
	}

	private static void makeJsonFrom(Interaction interaction, StringBuilder builder) {
		builder.append("{");
		builder.append(String.format("\"type\":\"%s\",\"id\":\"%s\",\"fixtureName=\":\"%s\"",
				Scenarios.INTERACTION, interaction.getUuid(), interaction.getFixtureName()));
		builder.append("}");
		for (Input input : interaction.getInputs()) {
			builder.append(",{");
			builder.append(String.format(
					"\"type\":\"%s\",\"id\":\"%s\",\"composite\":\"%s\",\"fixtureName\":\"%s\",\"value\":\"%s\"",
					Scenarios.INPUT, input.getUuid(), interaction.getUuid(), input.getFixtureName(),
					input.getValue()));
			builder.append("}");
		}
		for (Output output : interaction.getOutputs()) {
			builder.append(",{");
			builder.append(String.format(
					"\"type\":\"%s\",\"id\":\"%s\",\"composite\":\"%s\",\"fixtureName\":\"%s\",\"value\":\"%s\"",
					Scenarios.OUTPUT, output.getUuid(), interaction.getUuid(), output.getFixtureName(),
					output.getValue()));
			builder.append("}");
		}
		if (interaction.getTransaction() != null && interaction.getTransaction().getTarget() != null) {
			builder.append(",{");
			builder.append(String.format("\"type\":\"%s\",\"id\":\"%s\",\"source=\":\"%s\",\"target\":\"%s\"",
					Scenarios.TRANSACTION, interaction.getTransaction().getUuid(), interaction.getUuid(),
					interaction.getTransaction().getTarget().getUuid()));
			builder.append("},");
			makeJsonFrom(interaction.getTransaction().getTarget(), builder);
		}

	}

}
