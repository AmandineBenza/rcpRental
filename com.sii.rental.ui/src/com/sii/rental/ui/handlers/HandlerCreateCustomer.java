 
package com.sii.rental.ui.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Evaluate;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;

import com.opcoach.training.rental.RentalAgency;
import com.sii.rental.ui.wizard.CustomerWizard;

public class HandlerCreateCustomer {
	
	@CanExecute
	@Evaluate
	public boolean evaluate(@Named(IServiceConstants.ACTIVE_SELECTION) Object o) 
	{
		return o instanceof RentalAgency;
		
	}

	@Execute
	public void execute(Shell shell, @Named(IServiceConstants.ACTIVE_SELECTION) RentalAgency agency) {
		CustomerWizard wizard = new CustomerWizard(agency);
		WizardDialog dialog = new WizardDialog(shell, wizard);
		dialog.open();

	}
		
}