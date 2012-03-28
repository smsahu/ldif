/*
 * LDIF
 *
 * Copyright 2011-2012 Freie Universität Berlin, MediaEvent Services GmbH & Co. KG
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ldif.local.scheduler

import ldif.util.{ReportPublisher, Register, Publisher}

/**
 * Created by IntelliJ IDEA.
 * User: andreas
 * Date: 3/6/12
 * Time: 7:19 PM
 * To change this template use File | Settings | File Templates.
 */

class ImportJobPublisher extends Publisher with Register[ReportPublisher] {
  def getPublisherName = "Import Job"

  def getLink: Option[String] = None
}
