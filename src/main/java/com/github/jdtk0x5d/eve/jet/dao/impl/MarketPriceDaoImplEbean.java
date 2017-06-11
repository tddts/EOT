package com.github.jdtk0x5d.eve.jet.dao.impl;

import com.github.jdtk0x5d.eve.jet.dao.MarketPriceDao;
import com.github.jdtk0x5d.eve.jet.model.db.CachedMarketPrice;
import org.springframework.stereotype.Repository;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Repository
public class MarketPriceDaoImplEbean extends EbeanAbstractDao<CachedMarketPrice> implements MarketPriceDao {

  public MarketPriceDaoImplEbean() {
    super(CachedMarketPrice.class);
  }
}
