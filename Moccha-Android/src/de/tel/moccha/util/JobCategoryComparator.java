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

import de.tel.moccha.entities.job.JobCategory;
import de.zell.android.util.EntityComparator;
import de.zell.android.util.db.Entity;

/**
 *
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class JobCategoryComparator extends EntityComparator {
  
  
  @Override
  public int compare(Entity arg0, Entity arg1) {
    return ((JobCategory) arg0).getName().compareTo(((JobCategory) arg1).getName());
  }
  
}
