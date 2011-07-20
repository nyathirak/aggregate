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

package org.opendatakit.aggregate.client.widgets;

import org.opendatakit.aggregate.client.AggregateUI;
import org.opendatakit.aggregate.client.SecureGWT;
import org.opendatakit.aggregate.client.UrlHash;
import org.opendatakit.aggregate.client.popups.ExternalServicePopup;
import org.opendatakit.aggregate.constants.common.ExternalServicePublicationOption;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ExecutePublishButton extends AButtonBase implements ClickHandler {
 
  private ExternalServicePopup popup;
  
  public ExecutePublishButton(ExternalServicePopup popup) {
    super("<img src=\"images/green_right_arrow.png\" /> Publish");
    this.popup = popup;
    addClickHandler(this);
  }

  @Override
  public void onClick(ClickEvent event) {
    super.onClick(event);
    
    String formId = popup.getFormId(); 
    String name = popup.getName();
    String selectedService = popup.getService();
    ExternalServicePublicationOption serviceOp = popup.getEsOptions();
    
    if (selectedService.equals(ExternalServicePopup.TYPE_FUSION_TABLE)) {
    	SecureGWT.getServicesAdminService().createFusionTable(formId, serviceOp, new OAuthCallback());
    } else { // selectedService.equals(TYPE_SPREAD_SHEET)
    	SecureGWT.getServicesAdminService().createGoogleSpreadsheet(formId, name, serviceOp, new OAuthCallback());
    }
    
    AggregateUI.getUI().getTimer().restartTimer();
    popup.hide();
  }

  private class OAuthCallback implements AsyncCallback<String> {
        
    public void onFailure(Throwable caught) {
        AggregateUI.getUI().reportError(caught);
    }

    public void onSuccess(String result) {
      SecureGWT.getServicesAdminService().generateOAuthUrl(result, new AsyncCallback<String>() {
        @Override
        public void onFailure(Throwable caught) {
            AggregateUI.getUI().reportError(caught);
        }

        @Override
        public void onSuccess(String result) {
          UrlHash.getHash().goTo(result);
        }
      });
    }
  }
}
   
  