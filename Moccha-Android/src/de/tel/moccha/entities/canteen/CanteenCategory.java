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
package de.tel.moccha.entities.canteen;

import de.tel.moccha.entities.Category;
import de.zell.android.util.json.JSONElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a dish category, for example main menu.
 * 
 * @author Christopher Zell <zelldon91@googlemail.com>
 */
public class CanteenCategory extends Category {
  
  @JSONElement(name="dishes")
  private List<Dish> dishes;

  public CanteenCategory() {
  }

  public List<Dish> getDishes() {
    return dishes;
  }

  public void setDishes(List<Dish> dishes) {
    this.dishes = dishes;
  }
  
  
  public void addDish(Dish d) {
    if (dishes == null)
      dishes = new ArrayList<Dish>();
    
    dishes.add(d);
  }
  
}
