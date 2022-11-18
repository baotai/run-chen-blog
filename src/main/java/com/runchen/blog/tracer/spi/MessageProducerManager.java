package com.runchen.blog.tracer.spi;

/**
 * @author Jason Song(song_s@ctrip.com)
 */
public interface MessageProducerManager {
  /**
   * @return the message producer
   */
  MessageProducer getProducer();
}
