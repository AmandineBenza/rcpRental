 
package com.sii.rental.ui.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import com.opcoach.training.rental.Rental;
import com.sii.rental.core.RentalCoreActivator;

public class RentalPropertyPart {
	
	
	private Label rentedObjectlabel1;


	@Inject
	public RentalPropertyPart() {
		
	}
	
	@PostConstruct
	public void createContent(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setText("Hello");	
		
		parent.setLayout(new GridLayout(1, false));
		
		Group infoGroup = new Group(parent, SWT.NONE);
		infoGroup.setText("Informations");
		infoGroup.setLayout(new GridLayout(2, false));
		
		rentedObjectlabel1 = new Label(infoGroup, SWT.NONE);
		GridData gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.horizontalSpan = 2;
		rentedObjectlabel1.setLayoutData(gd);
		setRental(RentalCoreActivator.getAgency().getRentals().get(0));
	}
	
	public void setRental(Rental r) 
	{
		rentedObjectlabel1.setText(r.getRentedObject().getName());

	}
		
	
	@Focus
	public void onFocus() {
		
	}
	
	
}