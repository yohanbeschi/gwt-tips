package org.isk.gwttips.client.common;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource.NotStrict;
import com.google.gwt.resources.client.ImageResource;

/**
 * This interface contains CSS and image files.
 * 
 * @author Yohan Beschi
 */
public interface AppResources extends ClientBundle {
	  @NotStrict
	  @Source("org/isk/gwttips/public/gwttips.css")
	  CssResources css();
	  
	  @Source("org/isk/gwttips/public/icons/info.png")
      ImageResource infoIcon();
	  
	  @Source("org/isk/gwttips/public/icons/success.png")
      ImageResource successIcon();
	  
	  @Source("org/isk/gwttips/public/icons/warning.png")
      ImageResource warningIcon();
	  
	  @Source("org/isk/gwttips/public/icons/error.png")
      ImageResource errorIcon();
}
