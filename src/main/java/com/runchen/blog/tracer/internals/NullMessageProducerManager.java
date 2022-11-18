package com.runchen.blog.tracer.internals;

import com.runchen.blog.tracer.spi.MessageProducer;
import com.runchen.blog.tracer.spi.MessageProducerManager;

/**
 * @author Jason Song(song_s@ctrip.com)
 */
public class NullMessageProducerManager implements MessageProducerManager {
  private static final MessageProducer producer = new NullMessageProducer();

  @Override
  public MessageProducer getProducer() {
    return producer;
  }
}
