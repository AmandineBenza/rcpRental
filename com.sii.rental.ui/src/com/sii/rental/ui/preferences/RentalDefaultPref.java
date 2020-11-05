package com.sii.rental.ui.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.graphics.RGB;
import org.osgi.service.prefs.BackingStoreException;

import com.sii.rental.ui.RentalUIConstants;

public class RentalDefaultPref extends AbstractPreferenceInitializer implements RentalUIConstants {
	
	public RentalDefaultPref() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeDefaultPreferences() {
		
		IEclipsePreferences node = DefaultScope.INSTANCE.getNode(PLUGIN_ID);
		
		if (node != null) {
			node.put(PREF_CUSTOMER_COLOR, StringConverter.asString(new RGB(113, 192, 249)));
			
			try {
				node.flush();
			} 
			catch (BackingStoreException e) {
				e.printStackTrace();
			}
		}


	}

}
