package com.sii.rental.ui.preferences;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;

import com.sii.rental.ui.RentalUIConstants;

public class RentalColorsPrefPage extends FieldEditorPreferencePage implements RentalUIConstants {

	public RentalColorsPrefPage() {
		super(GRID);
	}


	@Override
	protected void createFieldEditors() {
		addField(new ColorFieldEditor(PREF_CUSTOMER_COLOR, "Customer Color", getFieldEditorParent()));

	}

}
