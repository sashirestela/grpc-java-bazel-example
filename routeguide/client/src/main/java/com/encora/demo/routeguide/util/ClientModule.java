package com.encora.demo.routeguide.util;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ClientModule extends AbstractModule {

  @Provides
  @Named("Port")
  int providePort() {
    return 8980;
  }

  @Provides
  Channel provideChannel() {
    String target = "localhost:" + providePort();
    ManagedChannel channel = ManagedChannelBuilder.forTarget(target).usePlaintext().build();
    return channel;
  }
}