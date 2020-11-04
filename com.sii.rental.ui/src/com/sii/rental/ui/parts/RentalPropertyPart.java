 
package com.sii.rental.ui.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;

public class RentalPropertyPart {
	
	
	private Label rentedObjectlabel1;
	private Label customerLabel1;
	private Label startLabel;
	private Label endLabel;


	@Inject
	public RentalPropertyPart() {
		
	}
	
	@PostConstruct
	public void createContent(Composite parent, RentalAgency agency) {
		Label label = new Label(parent, SWT.NONE);
		label.setText("Hello");	
		
		parent.setLayout(new GridLayout(1, false));
		
		Group infoGroup = new Group(parent, SWT.NONE);
		infoGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		infoGroup.setText("Informations");
		infoGroup.setLayout(new GridLayout(2, false));
		
		rentedObjectlabel1 = new Label(infoGroup, SWT.NONE);
		GridData gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.horizontalSpan = 2;
		rentedObjectlabel1.setLayoutData(gd);
		
		Label rentedTo = new Label(infoGroup, SWT.BORDER);
		rentedTo.setText("Loué à ");
		
		customerLabel1 = new Label (infoGroup, SWT.NONE);
		
		Group grpDates = new Group(parent, SWT.NONE);
		grpDates.setText("Dates");
		grpDates.setLayout(new GridLayout(2, false));
		
		Label lblNewLabel = new Label(grpDates, SWT.NONE);
		lblNewLabel.setText("Du : ");
		
		startLabel = new Label(grpDates, SWT.NONE);
		
		Label lblNewLabel_3 = new Label(grpDates, SWT.NONE);
		lblNewLabel_3.setText("Au : ");
		
		endLabel = new Label(grpDates, SWT.NONE);

		setRental(agency.getRentals().get(0));
	}
	
	public void setRental(Rental r) 
	{
		if (rentedObjectlabel1 != null) {
			rentedObjectlabel1.setText(r.getRentedObject().getName());
			customerLabel1.setText(r.getCustomer().getDisplayName());
			startLabel.setText(r.getStartDate().toString());
			endLabel.setText(r.getEndDate().toString());	
		}
	}
	
	@Inject @Optional
	public void receiveSelection(@Named(IServiceConstants.ACTIVE_SELECTION) Rental r) {
		setRental(r);	
	}
		
	
	@Focus
	public void onFocus() {
		
	}
}