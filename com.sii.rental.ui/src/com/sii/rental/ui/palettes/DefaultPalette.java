package com.sii.rental.ui.palettes;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.graphics.Color;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalObject;
import com.sii.rental.ui.RentalUIConstants;

public class DefaultPalette implements IColorProvider,RentalUIConstants {
	
	@Inject @Named(RENTAL_UI_PREF_STORE)
	private IPreferenceStore prefStore;

	public DefaultPalette() {
		// TODO Auto-generated constructor stub
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

}
