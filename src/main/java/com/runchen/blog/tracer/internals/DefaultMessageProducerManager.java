package com.runchen.blog.tracer.internals;

import com.runchen.blog.tracer.internals.cat.CatMessageProducer;
import com.runchen.blog.tracer.internals.cat.CatNames;
import com.runchen.blog.tracer.spi.MessageProducer;
import com.runchen.blog.tracer.spi.MessageProducerManager;
import com.runchen.blog.util.ClassLoaderUtil;

/**
 * @author Jason Song(song_s@ctrip.com)
 */
public class DefaultMessageProducerManager implements MessageProducerManager {
  private static MessageProducer producer;

  public DefaultMessageProducerManager() {
    if (ClassLoaderUtil.isClassPresent(CatNames.CAT_CLASS)) {
      producer = new CatMessageProducer();
    } else {
      producer = new NullMessageProducerManager().getProducer();
    }
  }

  @Override
  public MessageProducer getProducer() {
    return producer;
  }
}
