package net.douglashiura.leb.uid.scenario.html;

import net.douglashiura.leb.uid.scenario.data.Folder;
import net.douglashiura.leb.uid.scenario.data.Scenario;

public class FolderToHtml {

	public static String toHtml() {
		StringBuffer html = new StringBuffer();
		of(Folder.getDefault(), html);
		return html.toString();
	}

	private static void of(Folder folder, StringBuffer html) {
		html.append(divOpen(folder.getDeep()));
		html.append("<img alt=\"folder\" src=\"resources/images/folder.png\" height=\"20px\" width=\"20px\" >");
		html.append(folder.getName());
		html.append(divClose());

		for (Folder umFolder : folder.getFolders()) {
			of(umFolder, html);
		}

		for (Scenario anFile : folder.getScenaries()) {
			html.append(divOpen(folder.getDeep() + 1));
			html.append("<img alt=\"folder\" src=\"resources/images/us.png\" height=\"20px\" width=\"20px\" >");
			html.append(linkFor(anFile));
			html.append(divClose());
		}
	}

	private static String linkFor(Scenario anFile) {
		String id = anFile.getFile().getAbsolutePath().split("us-uid")[1];
		id = id.replace("\\", "/");
		return String.format("<a href=\"Editor.jsp?scenario=files%s\">%s</a>", id, anFile.getName());
	}

	private static Object divClose() {
		return "</div>";
	}

	private static String divOpen(int left) {
		return String.format("<div style='padding-left:%spx;'>", left * 15);
	}

}
