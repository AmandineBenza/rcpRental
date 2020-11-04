 
package com.sii.rental.ui.parts;

import javax.inject.Inject;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import com.opcoach.training.rental.RentalAgency;

public class AgencyPart {
	
	
	@Inject
	public AgencyPart() {
		
	}
	
	@PostConstruct
	public void postConstruct(Composite parent, RentalAgency agency) {
		
		TreeViewer tree = new TreeViewer(parent);
		
		RentalProvider provider = new RentalProvider();
		tree.setContentProvider(provider);
		tree.setLabelProvider(provider);
		tree.setInput(Arrays.asList(agency));
		
	}
		

	
}