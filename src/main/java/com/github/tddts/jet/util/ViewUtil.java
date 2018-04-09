/*
 * Copyright 2018 Tigran Dadaiants
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.tddts.jet.util;

import com.github.tddts.jet.exception.BrowserOpeningException;

import java.awt.*;
import java.net.URI;

/**
 * Class providing utility methods used for view.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class ViewUtil {

  /**
   * Open given URI in Web Browser.
   *
   * @param uri page URI
   * @throws BrowserOpeningException in case of any errors
   */
  public static void openWebpage(URI uri) throws BrowserOpeningException {
    Thread thread = new Thread(() -> openPage(uri));
    thread.start();
  }

  private static void openPage(URI uri) {
    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
      try {
        desktop.browse(uri);
      } catch (Exception e) {
        throw new BrowserOpeningException(e);
      }
    }
  }

}
