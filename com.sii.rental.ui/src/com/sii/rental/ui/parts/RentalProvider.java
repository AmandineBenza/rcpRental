package com.sii.rental.ui.parts;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;
import com.sii.rental.ui.RentalUIConstants;

public class RentalProvider extends LabelProvider implements ITreeContentProvider, IColorProvider, RentalUIConstants {
	
	
	@Inject @Named(RENTAL_UI_IMG_REGISTRY)
	private ImageRegistry registry;
	
	@Inject @Named(RENTAL_UI_PREF_STORE)
	private IPreferenceStore prefStore;

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
	public Image getImage(Object element) {
		
		if (element instanceof Customer) {	
			return registry.get(IMG_CUSTOMER);
		}
		
		if (element instanceof RentalAgency) {
			return registry.get(IMG_AGENCY);
			
		}
		
		if (element instanceof RentalObject) {
			return registry.get(IMG_RENTAL_OBJECT);	
		}
		
		if (element instanceof Rental) {
			return registry.get(IMG_RENTAL);
		}
		
		return null;	
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
			return getAColor(PREF_CUSTOMER_COLOR);
			
		}
		
		else if (element instanceof Rental) {
			return getAColor(PREF_RENTAL_COLOR);
			
		}
		
		else if (element instanceof RentalObject) {
			return getAColor(PREF_RENTAL_OBJECT_COLOR);
			
		}
		
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Color getAColor(String prefKString) {
		
		String rgbKString = prefStore.getString(prefKString);
		
		ColorRegistry reg = JFaceResources.getColorRegistry(); 
		Color col = reg.get(rgbKString);
		
		if(col == null) {
			reg.put(rgbKString, StringConverter.asRGB(rgbKString));
			col = reg.get(rgbKString);
		}
		return col;	
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

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + ((agency == null) ? 0 : agency.hashCode());
			result = prime * result + ((label == null) ? 0 : label.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			if (agency == null) {
				if (other.agency != null)
					return false;
			} else if (!agency.equals(other.agency))
				return false;
			if (label == null) {
				if (other.label != null)
					return false;
			} else if (!label.equals(other.label))
				return false;
			return true;
		}

		private RentalProvider getEnclosingInstance() {
			return RentalProvider.this;
		}
		
				
	}


}
