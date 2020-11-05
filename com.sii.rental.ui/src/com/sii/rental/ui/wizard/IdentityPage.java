package com.sii.rental.ui.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;

public class IdentityPage extends WizardPage {
	
	private Text customerName;

	public IdentityPage() {
		super("wizardPage");
		setTitle("Wizard Page title");
		setDescription("Wizard Page description");
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);

		setControl(container);
		container.setLayout(new GridLayout(2, false));
		
		Label lblName = new Label(container, SWT.NONE);
		lblName.setText("Name : ");
		
		customerName = new Text(container, SWT.BORDER);
		customerName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		customerName.setText("DefaultName");
	}

	public String getCustomerName() {
		return customerName.getText();
	}
	
	@Override
	public boolean isPageComplete() {
		return getCustomerName().length() > 0;
	}
	
	
}
