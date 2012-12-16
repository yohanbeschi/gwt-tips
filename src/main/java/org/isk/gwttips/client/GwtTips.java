package org.isk.gwttips.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.isk.gwttips.client.common.AppResources;
import org.isk.gwttips.client.i18n.ErrorConstants;
import org.isk.gwttips.client.i18n.TextConstants;
import org.isk.gwttips.client.i18n.TextMessages;
import org.isk.gwttips.client.logging.CustomLogger;
import org.isk.gwttips.client.service.ConnectionService;
import org.isk.gwttips.client.service.ConnectionServiceAsync;
import org.isk.gwttips.shared.UserUI;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.SubmitButton;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Entry Point of the application.
 * 
 * @author Yohan Beschi
 */
public class GwtTips implements EntryPoint {

    private final static Logger LOGGER = Logger.getLogger(GwtTips.class.getName());
    private final static Logger HTML_LOGGER = CustomLogger.getHtmlLogger(GwtTips.class.getName());

    final static public AppResources RESOURCES = GWT.create(AppResources.class);

    final static public ErrorConstants ERROR_CONSTANTS = GWT.create(ErrorConstants.class);
    final static public TextConstants TEXT_CONSTANTS = GWT.create(TextConstants.class);
    final static public TextMessages TEXT_MESSAGES = GWT.create(TextMessages.class);

    private ConnectionServiceAsync connectionService = GWT.create(ConnectionService.class);

    @Override
    public void onModuleLoad() {
        LOGGER.fine("Module initializing...");

        // Inject CSS
        final CssResource cssResource = RESOURCES.css();
        cssResource.ensureInjected();

        // Build View
        final AbsolutePanel view = this.buildView();
        this.bind();

        // RPC

        RootLayoutPanel.get().add(view);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // View
    // -----------------------------------------------------------------------------------------------------------------
    private TextBox loginField;
    private PasswordTextBox passwordField;
    private SubmitButton submitButton;
    private DecoratorPanel decPanel;

    private Label errorMsg;
    private SimplePanel errorBox;

    private FlowPanel flowPanel;
    private DeckPanel deckPanel;

    private Label labelConnected;

    private AbsolutePanel buildView() {
        // ---- Error box
        this.errorMsg = new InlineLabel("");
        this.errorBox = new SimplePanel(errorMsg);
        this.errorBox.addStyleName(RESOURCES.css().errorBox());
        this.errorBox.setWidth("300px");
        this.errorBox.setVisible(false);

        // ---- Login Box
        this.loginField = new TextBox();
        this.loginField.setWidth("150px");
        this.passwordField = new PasswordTextBox();
        this.passwordField.setWidth("150px");
        this.submitButton = new SubmitButton(TEXT_CONSTANTS.loginFieldSubmit());
        this.submitButton.addStyleName("gwt-Button");

        // Create a table to layout the form options
        final FlexTable layout = new FlexTable();
        layout.setCellSpacing(6);
        final FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();

        layout.setHTML(0, 0, TEXT_CONSTANTS.loginIntroMessage());
        cellFormatter.setColSpan(0, 0, 2);
        cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

        layout.setHTML(1, 0, TEXT_CONSTANTS.loginFieldLogin());
        layout.setWidget(1, 1, this.loginField);

        layout.setHTML(2, 0, TEXT_CONSTANTS.loginFieldPassword());
        layout.setWidget(2, 1, this.passwordField);

        layout.setWidget(3, 0, this.submitButton);
        cellFormatter.setColSpan(3, 0, 2);
        cellFormatter.setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_CENTER);

        // Wrap in a FormPanel
        final FormPanel formPanel = new FormPanel();
        formPanel.add(layout);

        // If connected
        this.labelConnected = new Label();

        // Show the form or a welcome message
        this.deckPanel = new DeckPanel();
        this.deckPanel.add(formPanel);
        this.deckPanel.add(labelConnected);
        this.deckPanel.showWidget(0);

        // Wrap in a DecoratorPanel
        this.decPanel = new DecoratorPanel();
        this.decPanel.setWidget(deckPanel);
        this.decPanel.addStyleName(RESOURCES.css().loginBoxError());
        this.decPanel.addStyleName(RESOURCES.css().loginBoxNoError());

        // Page
        this.flowPanel = new FlowPanel();
        this.flowPanel.add(this.decPanel);

        // ---- Container
        final AbsolutePanel container = new AbsolutePanel();
        container.getElement().getStyle().setPosition(Position.ABSOLUTE);
        container.getElement().getStyle().setHeight(100, Unit.PCT);
        container.getElement().getStyle().setWidth(100, Unit.PCT);
        container.addAttachHandler(new Handler() {
            @Override
            public void onAttachOrDetach(AttachEvent event) {
                Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {

                    @Override
                    public void execute() {
                        GwtTips.this.loginField.setFocus(true);
                    }
                });
            }
        });

        container.add(this.flowPanel);

        HTML_LOGGER.fine(container.toString());

        return container;
    }

    public HasValue<String> getLoginField() {
        return this.loginField;
    }

    public HasValue<String> getPasswordField() {
        return this.passwordField;
    }

    public HasClickHandlers getSubmitButton() {
        return this.submitButton;
    }

    public void setErrorMessage(final String errorMessage) {
        this.errorMsg.setText(errorMessage);

        if (this.flowPanel.getWidgetCount() == 1) {
            this.errorBox.setVisible(true);
            this.decPanel.removeStyleName(RESOURCES.css().loginBoxNoError());
            this.decPanel.addStyleName(RESOURCES.css().loginBoxError());
            this.flowPanel.insert(this.errorBox, 0);
        }
    }

    public void connected(final String firstname, final String lastname) {
        this.labelConnected.setText(TEXT_MESSAGES.connectionSuccessful(firstname, lastname));
        this.deckPanel.showWidget(1);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Action
    // -----------------------------------------------------------------------------------------------------------------
    private void bind() {
        this.getSubmitButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                GwtTips.this.handleSubmit();
            }
        });
    }

    private void handleSubmit() {
        final String login = this.getLoginField().getValue();
        final String password = this.getPasswordField().getValue();

        this.connectionService.connect(login, password, new AsyncCallback<UserUI>() {
            @Override
            public void onFailure(Throwable caught) {
                final String errorMsg = ERROR_CONSTANTS.getString(caught.getMessage());

                // This is not a real error! We don't wan to have on failure message in production.
                LOGGER.log(Level.WARNING, errorMsg, caught);

                GwtTips.this.setErrorMessage(errorMsg);
            }

            @Override
            public void onSuccess(UserUI result) {
                LOGGER.fine("Connection successful");
                GwtTips.this.connected(result.getFirstname(), result.getLastname());
            }
        });
    }
}
