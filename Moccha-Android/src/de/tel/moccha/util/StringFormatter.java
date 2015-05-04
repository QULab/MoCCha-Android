/*
 * Copyright 2015 Quality and Usability Lab, Telekom Innvation Laboratories, TU Berlin..
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
package de.tel.moccha.util;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;

/**
 * Represents a String formatter which formats and creates strings
 * with the given id.
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class StringFormatter {
  
  /**
   * The context which is used to get the strings.
   */
  private final Context ctx;

  public StringFormatter(Context ctx) {
    this.ctx = ctx;
  }
  
  /**
   * Returns the formated string for the string xml id and the given values.
   * Format the string and returns the text formatted with the HTML tags.
   * 
   * @param id the id which identifies the string
   * @param values the values for the string
   * @return the formated string
   */
  public Spanned getFormatedHTMLString(int id, Object ... values) {
    return Html.fromHtml(String.format(ctx.getString(id), values));
  }
  
   /**
   * Returns the formated string for the string xml id and the given values.
   * 
   * @param id the id which identifies the string
   * @param values the values for the string
   * @return the formated string
   */
  public String getFormatedString(int id, Object ... values) {
    return String.format(ctx.getString(id), values);
  }
}
