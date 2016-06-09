package com.thomsonreuters.handler;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.reactivex.netty.channel.StringTransformer;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import io.reactivex.netty.protocol.http.server.RequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.functions.Func1;

@Singleton
public class HelloRequestHandler implements RequestHandler<ByteBuf, ByteBuf> {
  private static final Logger log = LoggerFactory.getLogger(HelloRequestHandler.class);

  @Inject
  HelloRequestHandler() {
  }
 
  @Override
  public Observable<Void> handle(HttpServerRequest<ByteBuf> request, HttpServerResponse<ByteBuf> response) {
    return Observable.just("Hello").flatMap(new Func1<String, Observable<Void>>() {
      @Override
      public Observable<Void> call(String s) {
        response.setStatus(HttpResponseStatus.OK);
        response.writeAndFlush(s, StringTransformer.DEFAULT_INSTANCE);
        return response.close();
      }
    });
  }
}
