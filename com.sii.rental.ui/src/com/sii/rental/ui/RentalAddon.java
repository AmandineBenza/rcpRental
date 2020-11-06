 
package com.sii.rental.ui;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.IColorProvider;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.opcoach.e4.preferences.ScopedPreferenceStore;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.helpers.RentalAgencyGenerator;


public class RentalAddon implements RentalUIConstants {
	
	private Map<String, Palette> paletteManager = new HashMap<String, Palette>();
	
	@PostConstruct
	public void publishInContext(IEclipseContext ctx, IExtensionRegistry registry) {
		ctx.set(RentalAgency.class, RentalAgencyGenerator.createSampleAgency());
		ctx.set(RENTAL_UI_IMG_REGISTRY, getLocalImageRegistry());
		ctx.set(RENTAL_UI_PREF_STORE, new ScopedPreferenceStore(InstanceScope.INSTANCE, PLUGIN_ID));
		readPalettes(registry, ctx);
		getExtensions(registry);
	}
	
	/**
	 * A method to create and initialize an ImageRegistry
	 * @return a initialized ImageRegistry that can be put in the context
	 */
	ImageRegistry getLocalImageRegistry()
	{
		// Get the bundle using the universal method to get it from the current class
		Bundle b = FrameworkUtil.getBundle(getClass());  
		
		// Create a local image registry
		ImageRegistry reg = new ImageRegistry();

		// Then fill the values...
		reg.put(IMG_CUSTOMER, ImageDescriptor.createFromURL(b.getEntry(IMG_CUSTOMER)));
		reg.put(IMG_RENTAL, ImageDescriptor.createFromURL(b.getEntry(IMG_RENTAL)));
		reg.put(IMG_RENTAL_OBJECT, ImageDescriptor.createFromURL(b.getEntry(IMG_RENTAL_OBJECT)));
		reg.put(IMG_AGENCY, ImageDescriptor.createFromURL(b.getEntry(IMG_AGENCY)));
		reg.put(IMG_COLLAPSE_ALL, ImageDescriptor.createFromURL(b.getEntry(IMG_COLLAPSE_ALL)));
		reg.put(IMG_EXPAND_ALL, ImageDescriptor.createFromURL(b.getEntry(IMG_EXPAND_ALL)));

		return reg;
	}
	
	@Inject
	@Optional 
	public void reactCustomer(@UIEventTopic("customer/*") Customer customer) {
		System.out.println("The Customer " + customer.getDisplayName() + " was copied.");
	}
	
	private void getExtensions(IExtensionRegistry registry) {
		
		IExtensionPoint extensionPoint = registry.getExtensionPoint("org.eclipse.e4.workbench.model");
		IExtension[] extensions = extensionPoint.getExtensions();
		
		for (IExtension ext : extensions) {
			
			for (IConfigurationElement conf : ext.getConfigurationElements()) {
				
				if(conf.getName().equals("fragment")) {
					System.out.println("Model fragment : " + conf.getAttribute("uri") + " found in " + conf.getNamespaceIdentifier());
				}
				
				else if(conf.getName().equals("processor")) {
					System.out.println("Processor : " + conf.getAttribute("class") + " found in " + conf.getNamespaceIdentifier());
				}
			}
			
		}
	}
	
	
	private void readPalettes(IExtensionRegistry registry, IEclipseContext context) {
		
		IExtensionPoint extensionPoint = registry.getExtensionPoint("com.sii.rental.ui.palette");
		IExtension[] extensions = extensionPoint.getExtensions();
		
		for (IExtension ext : extensions) {
			
			for (IConfigurationElement conf : ext.getConfigurationElements()) {
				
				Palette palette = new Palette();
				palette.setId(conf.getAttribute("id"));
				palette.setName(conf.getAttribute("name"));
				
				Bundle bundle = Platform.getBundle(conf.getNamespaceIdentifier());
				try {
					Class <?> clazz = bundle.loadClass(conf.getAttribute("paletteClass"));
					IColorProvider provider = (IColorProvider) ContextInjectionFactory.make(clazz, context);
					palette.setProvider(provider);
					paletteManager.put(palette.getId(), palette);
					System.out.println("Creation of palette " + palette.getName());
				} 
				catch (ClassNotFoundException | InvalidRegistryObjectException e) {
					e.printStackTrace();
				}

			}
			
		}
	}



}
