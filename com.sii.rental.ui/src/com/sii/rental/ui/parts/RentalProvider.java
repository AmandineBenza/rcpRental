package com.sii.rental.ui.parts;

import java.util.List;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class RentalProvider extends LabelProvider implements ITreeContentProvider, IColorProvider {
	

	@Override
	public Object[] getElements(Object inputElement) {
		
		if(inputElement instanceof List<?>) {
			return ((List<?>) inputElement).toArray();
		}
			
		return null;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		
		if(parentElement instanceof RentalAgency) {
			RentalAgency agency = (RentalAgency) parentElement;
			return new Node[] {
				new Node(agency, Node.CUSTOMER),
				new Node(agency, Node.RENTAL),
				new Node(agency, Node.OBJECTSTORENT)	
			};
		}
		
		else if (parentElement instanceof Node) {
			return ((Node) parentElement).getChildren();
		}
		
		return null;
	}
	
	@Override
	public String getText(Object element) {
		
		if (element instanceof RentalAgency) {
			return ((RentalAgency) element).getName();	
		}
		
		else if (element instanceof Customer) {
			return ((Customer) element).getDisplayName();
		}
		
		else if (element instanceof RentalObject) {
			return ((RentalObject) element).getName();
		}
		
		return super.getText(element);
	}
	
	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		
		return element instanceof RentalAgency || element instanceof Node;
	}
	
	@Override
	public Color getForeground(Object element) {
		
		if (element instanceof Customer) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_CYAN);
			
		}
		
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	class Node {
		
		public static final String CUSTOMER = "Customer";
		public static final String RENTAL = "Rental";
		public static final String OBJECTSTORENT = "Objects to rent";


		private RentalAgency agency;
		
		private String label;

		public Node(RentalAgency agency, String label) {
			super();
			this.agency = agency;
			this.label = label;
		}
		
		public Object[] getChildren() {
			
			if(label == CUSTOMER) {
				return agency.getCustomers().toArray();
			}
			
			if(label == RENTAL) {
				return agency.getRentals().toArray();
			}
			
			if(label == OBJECTSTORENT) {
				return agency.getObjectsToRent().toArray();
			}
			
			return null;
		}
		
		@Override
		public String toString() {
			return label;
		}
				
	}


}
