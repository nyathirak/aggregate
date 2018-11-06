/*
 * Copyright (C) 2010 University of Washington
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
package org.opendatakit.aggregate.format;

import java.util.List;
import org.opendatakit.aggregate.submission.Submission;
import org.opendatakit.common.persistence.exception.ODKDatastoreException;
import org.opendatakit.common.web.CallingContext;

/**
 * @author wbrunette@gmail.com
 * @author mitchellsundt@gmail.com
 */
public interface SubmissionFormatter {
  public void beforeProcessSubmissions(CallingContext cc);

  /**
   * Implementation should call before..., ...Segment, after... .
   *
   * @param submissions
   * @param cc
   * @throws ODKDatastoreException
   */
  public void processSubmissions(List<Submission> submissions, CallingContext cc) throws ODKDatastoreException;

  public void processSubmissionSegment(List<Submission> submissions, CallingContext cc) throws ODKDatastoreException;

  public void afterProcessSubmissions(CallingContext cc);
}
