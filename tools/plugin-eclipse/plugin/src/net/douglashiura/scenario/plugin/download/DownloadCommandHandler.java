package net.douglashiura.scenario.plugin.download;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.internal.refresh.MonitorJob;
import org.eclipse.core.internal.resources.Folder;
import org.eclipse.core.internal.resources.Project;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ICoreRunnable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.handlers.HandlerUtil;

import com.google.gson.GsonBuilder;

public class DownloadCommandHandler extends AbstractHandler implements ICoreRunnable {

	private DialogDownload dialog;
	private Folder folder;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		TreeSelection aEvent = (TreeSelection) HandlerUtil.getCurrentSelection(event);
		if (aEvent.getFirstElement() instanceof Folder
				&& ((Folder) aEvent.getFirstElement()).getParent() instanceof Project) {
			folder = (Folder) aEvent.getFirstElement();
			dialog = new DialogDownload(null);
			dialog.create();
			if (dialog.open() == Window.OK) {
				Job job = MonitorJob.create("Download Scenarios from " + dialog.getServer(), this);
				job.schedule();
			}
		} else {
			MessageDialog.openInformation(null, "Fault", "Select a folder in a Java project!");
		}
		return null;
	}

	@Override
	public void run(IProgressMonitor monitor) throws CoreException {
		String server = dialog.getServer();
		server = server.endsWith("/") ? server : server + "/";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(server + "scenaries/");

		try {
			httpget.setHeader("accept", "text/json");
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity);
			@SuppressWarnings("unchecked")
			List<String> elements = new GsonBuilder().create().fromJson(content, List.class);
			int indexFile = 0;
			for (String file : elements) {
				monitor.worked(indexFile++);
				httpget = new HttpGet(server + "/files" + file);
				response = httpclient.execute(httpget);
				entity = response.getEntity();
				byte[] scenario = EntityUtils.toByteArray(entity);
				String[] folders = file.split("" + File.separatorChar);
				IFolder aFolder = folder;
				for (int indexFolder = 0; indexFolder < folders.length - 1; indexFolder++) {
					aFolder = aFolder.getFolder(folders[indexFolder]);
					if (!aFolder.exists()) {
						aFolder.create(true, true, monitor);
					}
				}
				IFile aFile = aFolder.getFile(folders[folders.length - 1]);
				if (aFile.exists()) {
					aFile.setContents(new ByteArrayInputStream(scenario), 0, monitor);
				} else {
					aFile.create(new ByteArrayInputStream(scenario), 0, monitor);
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpget.releaseConnection();
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		monitor.done();
	}

}