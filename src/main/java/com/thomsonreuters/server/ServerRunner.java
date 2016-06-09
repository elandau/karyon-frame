package com.thomsonreuters.server;

import com.netflix.governator.guice.BootstrapModule;
import com.thomsonreuters.injection.BootstrapInjectionModule;
import netflix.karyon.Karyon;

import java.io.IOException;

public class ServerRunner {
  public static void main(String[] args) throws IOException {
    Karyon.forApplication(BootstrapInjectionModule.class, (BootstrapModule[]) null).startAndWaitTillShutdown();
  }
}
