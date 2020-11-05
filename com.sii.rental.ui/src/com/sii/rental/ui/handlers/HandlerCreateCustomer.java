 
package com.sii.rental.ui.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Evaluate;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.widgets.Display;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalFactory;

public class HandlerCreateCustomer {
	
	@CanExecute
	@Evaluate
	public boolean evaluate(@Named(IServiceConstants.ACTIVE_SELECTION) Object o) 
	{
		return o instanceof RentalAgency;
		
	}

	@Execute
	public void execute(Display display, @Named(IServiceConstants.ACTIVE_SELECTION) RentalAgency agency) {
		Customer c = RentalFactory.eINSTANCE.createCustomer();
		c.setFirstName("Customer");
		c.setLastName("Num"+System.currentTimeMillis());
		agency.getCustomers().add(c);	
		System.out.println("Creation of new customer" + c.getDisplayName());
	}
		
}