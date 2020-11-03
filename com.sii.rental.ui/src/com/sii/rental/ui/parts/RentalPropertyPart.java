 
package com.sii.rental.ui.parts;

import javax.inject.Inject;
import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.e4.ui.di.Focus;

public class RentalPropertyPart {
	
	
	@Inject
	public RentalPropertyPart() {
		
	}
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setText("Hello");	
	}
		
	
	@Focus
	public void onFocus() {
		
	}
	
	
}