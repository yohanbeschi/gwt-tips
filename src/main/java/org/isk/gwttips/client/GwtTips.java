package org.isk.gwttips.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry Point of the application.
 * 
 * @author Yohan BESCHI
 */
public class GwtTips implements EntryPoint {

	@Override
	public void onModuleLoad() {
		RootLayoutPanel.get().add(new HTML("Hello World !!"));
	}
}
