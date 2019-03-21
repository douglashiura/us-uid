package net.douglashiura.scenario.plugin.download;


import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class DialogDownload extends TitleAreaDialog {

    private Text txtServer;

    private String server;

    public DialogDownload(Shell parentShell) {
        super(parentShell);
    }

    @Override
    public void create() {
        super.create();
        setTitle("Server");
        setMessage("Enter with server URL", IMessageProvider.INFORMATION);
    }

    @Override
    protected org.eclipse.swt.widgets.Control createDialogArea(Composite parent) {
        Composite area = (Composite) super.createDialogArea(parent);
        Composite container = new Composite(area, SWT.NONE);
        container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        GridLayout layout = new GridLayout(2, false);
        container.setLayout(layout);
        createServer(container);
        return area;
    }

    private void createServer(Composite container) {
        Label lbtFirstName = new Label(container, SWT.NONE);
        lbtFirstName.setText("URL");

        GridData dataFirstName = new GridData();
        dataFirstName.grabExcessHorizontalSpace = true;
        dataFirstName.horizontalAlignment = GridData.FILL;
        txtServer = new Text(container, SWT.BORDER);
        txtServer.setText("http://localhost:8080/sc3n4r10/");
        txtServer.setLayoutData(dataFirstName);
    }

    @Override
    protected boolean isResizable() {
        return true;
    }

    private void saveInput() {
        server = txtServer.getText();

    }

    @Override
    protected void okPressed() {
        saveInput();
        super.okPressed();
    }

    public String getServer() {
        return server;
    }

}