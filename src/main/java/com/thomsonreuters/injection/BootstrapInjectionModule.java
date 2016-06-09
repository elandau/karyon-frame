package com.thomsonreuters.injection;

import com.google.inject.Singleton;
import com.netflix.governator.annotations.Modules;
import com.thomsonreuters.handler.HealthCheck;
import com.thomsonreuters.injection.module.MainModule;
import io.netty.buffer.ByteBuf;
import netflix.adminresources.resources.KaryonWebAdminModule;
import netflix.karyon.KaryonBootstrap;
import netflix.karyon.ShutdownModule;
import netflix.karyon.archaius.ArchaiusBootstrap;
import netflix.karyon.servo.KaryonServoModule;
import netflix.karyon.transport.http.KaryonHttpModule;

@ArchaiusBootstrap()
@KaryonBootstrap(name = "karyon-frame", healthcheck = HealthCheck.class)
@Singleton
@Modules(include = { ShutdownModule.class, KaryonServoModule.class, KaryonWebAdminModule.class,
    MainModule.class,
    BootstrapInjectionModule.KaryonRxRouterModuleImpl.class })
public interface BootstrapInjectionModule {
  class KaryonRxRouterModuleImpl extends KaryonHttpModule<ByteBuf, ByteBuf> {

    protected KaryonRxRouterModuleImpl() {
      super("karyonAsyncModule", ByteBuf.class, ByteBuf.class);
    }

    @Override
    protected void configureServer() {
      server().port( 7001 );
    }

    @Override
    protected void configure() {
      bindRouter().to(AppRouter.class);
      super.configure();
    }

  }
}
