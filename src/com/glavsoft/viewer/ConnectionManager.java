// Copyright (C) 2010, 2011, 2012 GlavSoft LLC.
// All rights reserved.
//
//-------------------------------------------------------------------------
// This file is part of the TightVNC software.  Please visit our Web site:
//
//                       http://www.tightvnc.com/
//
// This program is free software; you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation; either version 2 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License along
// with this program; if not, write to the Free Software Foundation, Inc.,
// 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
//-------------------------------------------------------------------------
//

package com.glavsoft.viewer;

import com.glavsoft.exceptions.CommonException;
import com.glavsoft.rfb.protocol.ProtocolSettings;
import com.glavsoft.viewer.swing.ConnectionParams;
import com.glavsoft.viewer.swing.UiSettings;
import com.glavsoft.viewer.swing.Utils;
import com.glavsoft.viewer.swing.gui.ConnectionDialog;
import com.glavsoft.viewer.swing.gui.ConnectionsHistory;
import com.glavsoft.viewer.swing.ssh.SshConnectionManager;
import com.sun.corba.se.impl.javax.rmi.CORBA.Util;

import javax.swing.*;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectionManager {
	private final WindowListener appWindowListener;
	private volatile boolean forceConnectionDialog;
	private JFrame containerFrame;
	private final boolean isApplet;
	private ConnectionsHistory connectionsHistory;

	public ConnectionManager(WindowListener appWindowListener, boolean isApplet) {
		this.appWindowListener = appWindowListener;
		this.isApplet = isApplet;
	}

	protected void showReconnectDialog(String title, String message) {
		JOptionPane reconnectPane = new JOptionPane(message + "\n Reconectar?",
				JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);

		if (reconnectPane.getValue() == null
				|| (Integer) reconnectPane.getValue() == JOptionPane.NO_OPTION) {
			

		} else {
//			forceConnectionDialog = !isApplet;
		}
	}

	Socket connectToHost(final ConnectionParams connectionParams,
			ProtocolSettings settings, UiSettings uiSettings)
			throws IOException, IOException {
		Socket socket = null;

		boolean wasError = false;
		int port;
		boolean hasJsch;
		
		try {
			Class.forName("com.jcraft.jsch.JSch");
			
			hasJsch = true;
		} catch (ClassNotFoundException e) {
			hasJsch = false;
		}

		String host;

		host = connectionParams.hostName;
		port = connectionParams.getPortNumber();
		socket = new Socket(host, port);
		wasError = false;

		return socket;
	}

	public void initSettingFromHistory(ConnectionParams connectionParams,
			ProtocolSettings protocolSettings, UiSettings uiSettings,
			int paramSettingsMask) {
		if (null == connectionsHistory)
			connectionsHistory = new ConnectionsHistory(connectionParams);
		if (connectionParams.isHostNameEmpty()) {
			connectionParams.completeEmptyFieldsFrom(connectionsHistory
					.getMostSuitableConnection(connectionParams));
			forceConnectionDialog = true;
		}
		protocolSettings.copySerializedFieldsFrom(connectionsHistory
				.getProtocolSettings(connectionParams),
				paramSettingsMask & 0xffff);
		uiSettings.copyDataFrom(connectionsHistory
				.getUiSettingsData(connectionParams),
				(paramSettingsMask >> 16) & 0xffff);
		protocolSettings.addListener(connectionsHistory);
		uiSettings.addListener(connectionsHistory);
	}

	public void showConnectionErrorDialog(final String message) {
		JOptionPane errorPane = new JOptionPane(message,
				JOptionPane.ERROR_MESSAGE);
		final JDialog errorDialog = errorPane.createDialog(containerFrame,
				"Error de Conexión");
		Utils.decorateDialog(errorDialog);
		errorDialog.setVisible(true);
	}

	public void setContainerFrame(JFrame containerFrame) {
		if (this.containerFrame != null
				&& this.containerFrame != containerFrame) {
			this.containerFrame.dispose();
		}
		this.containerFrame = containerFrame;
	}

}