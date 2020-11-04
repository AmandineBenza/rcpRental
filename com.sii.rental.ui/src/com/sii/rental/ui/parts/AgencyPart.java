 
package com.sii.rental.ui.parts;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import com.opcoach.training.rental.RentalAgency;

public class AgencyPart {
	
	
	@Inject
	public AgencyPart() {
		
	}
	
	@PostConstruct
	public void postConstruct(Composite parent, RentalAgency agency, IEclipseContext ctx) {
		
		TreeViewer tree = new TreeViewer(parent);
		
		RentalProvider provider = ContextInjectionFactory.make(RentalProvider.class, ctx);
		tree.setContentProvider(provider);
		tree.setLabelProvider(provider);
		tree.setInput(Arrays.asList(agency));
		
	}
		

	
}