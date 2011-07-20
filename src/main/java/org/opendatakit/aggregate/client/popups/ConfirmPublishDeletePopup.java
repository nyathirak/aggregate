/*
 * Copyright (C) 2011 University of Washington
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.opendatakit.aggregate.client.popups;

import org.opendatakit.aggregate.client.externalserv.ExternServSummary;
import org.opendatakit.aggregate.client.widgets.ClosePopupButton;
import org.opendatakit.aggregate.client.widgets.ExecuteDeletePublishButton;
import org.opendatakit.aggregate.constants.common.OperationalStatus;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;

/**
 * Popup asking for confirmation to delete an external service publisher
 * 
 * @author mitchellsundt@gmail.com
 *
 */
public class ConfirmPublishDeletePopup extends PopupPanel {

  public ConfirmPublishDeletePopup(ExternServSummary publisher) {
    super(false);
    setModal(true);

    FlexTable layout = new FlexTable();
    
    final String action = ((publisher.getStatus() == OperationalStatus.COMPLETED) ||
	 		 (publisher.getStatus() == OperationalStatus.ABANDONED)) ?
			 "remove" : "stop publishing and remove";

    HTML message = new HTML(
        "Delete this publisher?"
        + "<br/>Do you wish to " + action + " this location?");
    layout.setWidget(0, 0, message);
    layout.setWidget(0, 1, new ExecuteDeletePublishButton(publisher, this));
    layout.setWidget(0, 2, new ClosePopupButton(this));

    setWidget(layout);
  }
}