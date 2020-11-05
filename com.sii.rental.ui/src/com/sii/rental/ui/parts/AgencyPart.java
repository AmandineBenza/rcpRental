
package com.sii.rental.ui.parts;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.Preference;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import com.opcoach.training.rental.RentalAgency;
import com.sii.rental.ui.RentalUIConstants;

public class AgencyPart implements RentalUIConstants{

	private static final String MENU_ID = "com.sii.rental.ui.popupmenuhello";
	private TreeViewer tree;

	@Inject
	public AgencyPart() {

	}

	@PostConstruct
	public void postConstruct(Composite parent, RentalAgency agency, IEclipseContext ctx, ESelectionService selectionService,EMenuService menuService) {
		
		tree = new TreeViewer(parent);
		
		RentalProvider provider = ContextInjectionFactory.make(RentalProvider.class, ctx);
		tree.setContentProvider(provider);
		tree.setLabelProvider(provider);
		tree.setInput(Arrays.asList(agency));
		tree.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection structSelec = (IStructuredSelection) event.getSelection();
				
				if(structSelec.size() == 1) {
					selectionService.setSelection(structSelec.getFirstElement());
				}
				
				else {
					
					selectionService.setSelection(structSelec.toArray());
	
				}
			}
		});
		
		// Register popup menu on tree widget
		menuService.registerContextMenu(tree.getControl(), MENU_ID);
		//Listen to model modifications
		agency.eAdapters().add(new AdapterImpl() {
			@Override
			public void notifyChanged(Notification msg) {
				tree.refresh(true);
			}		
		});
	
	}
	
	@Inject @Optional
	public void refreshTree(@Preference(value=PREF_CUSTOMER_COLOR)String customerColor, 
			@Preference(value=PREF_RENTAL_COLOR)String rentalColor, 
			@Preference(value=PREF_RENTAL_OBJECT_COLOR)String rentalObjectColor) {
		
		if (tree != null) {
			tree.refresh();	
		}
	}

}