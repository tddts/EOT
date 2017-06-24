/*
 * Copyright 2017 Tigran Dadaiants
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

package com.github.jdtk0x5d.eve.jet.tools.filter;

import com.github.jdtk0x5d.eve.jet.model.db.ResultOrder;
import com.google.common.collect.ComparisonChain;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class ResultOrderFilter {

  private final Logger logger = LogManager.getLogger(ResultOrderFilter.class);

  private Comparator<ResultOrder> comparator = new ResultOrderComparator();


  public List<ResultOrder> filter(List<ResultOrder> orders) {
    if (orders.isEmpty()) return orders;

    orders.sort(comparator);

    ResultOrder previous;
    ResultOrder current;

    for (int i = 1; i < orders.size(); i++) {
      previous = orders.get(i - 1);
      current = orders.get(i);

      if (previous.getSellLocation().equals(current.getSellLocation())
          && previous.getSellPrice().equals(current.getSellPrice())
          && previous.getBuyPrice().equals(current.getBuyPrice())
          && previous.getProfitPerUnit().equals(current.getProfitPerUnit())
          && previous.getItemCargoFreeVolume() > 0) {

        double freeCargo = previous.getItemCargoFreeVolume();
        int capability = (int) (freeCargo / current.getItemVolume());

        if (capability > 1) {

          double addedVolume = capability * current.getItemVolume();
          double profitChange = capability * previous.getProfitPerUnit();

          previous.setItemCargoVolume(previous.getItemCargoVolume() + addedVolume);
          previous.setItemCargoFreeVolume(previous.getItemCargoFreeVolume() - addedVolume);
          previous.setProfit(previous.getProfit() + profitChange);

          current.setItemCargoVolume(current.getItemCargoVolume() - addedVolume);
          current.setItemCargoFreeVolume(current.getItemCargoFreeVolume() + addedVolume);
          current.setProfit(current.getProfit() - profitChange);

          if (current.getItemCargoVolume() <= 0) {
            orders.remove(i);
            i--;
          }

        }
      }
    }

    logger.debug("Orders after filtration: " + orders.size());
    return orders;
  }

  private class ResultOrderComparator implements Comparator<ResultOrder> {

    @Override
    public int compare(ResultOrder o1, ResultOrder o2) {
      return ComparisonChain.start()
          .compare(o1.getSellLocation(), o2.getSellLocation())
          .compare(o1.getBuyLocation(), o2.getBuyLocation())
          .compare(o1.getTypeId(), o2.getTypeId())
          .result();
    }
  }

}
