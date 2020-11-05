package com.sii.rental.ui.wizard;

import org.eclipse.jface.wizard.Wizard;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalFactory;

public class CustomerWizard extends Wizard {
	
	private IdentityPage idPage;
	private RentalAgency agency;

	public CustomerWizard(RentalAgency agency) {
		setWindowTitle("New Customer");
		this.agency = agency;
	}

	@Override
	public void addPages() {
		idPage = new IdentityPage();
		addPage(idPage);
	}

	@Override
	public boolean performFinish() {
		Customer c = RentalFactory.eINSTANCE.createCustomer();
		c.setFirstName("Customer");
		c.setLastName(idPage.getCustomerName());
		agency.getCustomers().add(c);	
		System.out.println("Creation of new customer" + c.getDisplayName());
		
		return true;
	}

}
