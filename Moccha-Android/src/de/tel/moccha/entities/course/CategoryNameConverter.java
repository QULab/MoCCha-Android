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
package de.tel.moccha.entities.course;

import java.util.HashMap;

/**
 * Represents the course category name converter which returns for the
 * given university the corresponding long name.
 * 
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class CategoryNameConverter {
  
  public static final String KEY_TUB = "tub";
  public static final String VALUE_TUB = "Technische Universität Berlin";
  public static final String KEY_UDK = "udk";
  public static final String VALUE_UDK = "Universität der Künste";
  public static final String KEY_STAATSOPER = "staatsoper";
  public static final String VALUE_STAATSOPER = "Staatsoper";
  /**
   * Key is the short version of the name, value is 
   * the long name.
   * 
   * Like: tub -> Technische Universitaet Berlin
   */
  private static final HashMap<String, String> NAMES = new HashMap<String, String>();
  static {
    NAMES.put(KEY_TUB, VALUE_TUB);
    NAMES.put(KEY_UDK, VALUE_UDK);
    NAMES.put(KEY_STAATSOPER, VALUE_STAATSOPER);
  }
  
  /**
   * Returns for the given key (short name) the long name version.
   * If the key doesn't exists in the map the key is returned.
   * 
   * @param key the key in the map
   * @return the value if not null otherwise the key
   */
  public static String getLongName(String key) {
    String value = NAMES.get(key);
    return value == null ? key : value;
  }
}
