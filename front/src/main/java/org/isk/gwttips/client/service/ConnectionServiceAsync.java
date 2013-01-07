package org.isk.gwttips.client.service;

import org.isk.gwttips.shared.UserUI;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ConnectionServiceAsync {
    void connect(String login, String password, AsyncCallback<UserUI> callback );
}
