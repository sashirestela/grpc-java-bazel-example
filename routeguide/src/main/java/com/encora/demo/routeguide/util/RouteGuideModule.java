package com.encora.demo.routeguide.util;

import com.encora.demo.routeguide.Feature;
import com.encora.demo.routeguide.RouteGuideGrpc;
import com.encora.demo.routeguide.server.RouteGuideService;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.Collection;

public class RouteGuideModule extends AbstractModule {

  @Provides
  @Named("Port")
  int providePort() {
    return 8980;
  }

  @Provides
  ServerBuilder<?> provideServiceBuilder() {
    return ServerBuilder.forPort(providePort());
  }

  @Provides
  RouteGuideGrpc.RouteGuideImplBase provideRouteGuideService() throws IOException {
    Collection<Feature> features = RouteGuideUtil.parseFeatures(RouteGuideUtil.getDefaultFeaturesFile());
    RouteGuideService service = new RouteGuideService(features);
    return service;
  }

  @Provides
  Channel provideChannel() {
    String target = "localhost:" + providePort();
    ManagedChannel channel = ManagedChannelBuilder.forTarget(target).usePlaintext().build();
    return channel;
  }
}