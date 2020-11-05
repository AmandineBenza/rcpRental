package com.sii.rental.ui.processor;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;

public class RentalProcessor {

	public RentalProcessor() {
		// TODO Auto-generated constructor stub
	}
	
	@Execute
	public void changeTitle(MApplication appli) {
		MWindow window = appli.getChildren().get(0);
		window.setLabel("Rental Application " + System.currentTimeMillis());
	}

}
