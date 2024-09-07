package xyz.hedy.gravity.prism.server;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.http.WebSocketFrame;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import xyz.hedy.gravity.prism.handler.Prism;
import xyz.hedy.gravity.prism.handler.impl.WebSocketPrismImpl;

/**
 * Created by hedy on 2024/9/6.
 */
public class GravityPrismServerVerticle extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        router.route(HttpMethod.GET, "/").handler(request -> {
            request.response().end("gravity prism.");
        });
        Prism<ServerWebSocket> prism = new WebSocketPrismImpl();
        vertx.createHttpServer()
            .webSocketHandler(ws -> {
                ws.closeHandler(v -> {
                    System.out.println("websocket closed.");
                });
                ws.handler(buffer -> {
                    System.out.println(buffer.toString());
                    ws.writeFrame(WebSocketFrame.textFrame("hello", false));
                    ws.writeFrame(WebSocketFrame.continuationFrame(Buffer.buffer("gravity"), false));
                    ws.writeFinalTextFrame("prism.");
                });
                ws.writeTextMessage("hello gravity prism.");
            })
            .requestHandler(router)
            .listen(8080, res -> {
                if (res.succeeded()) {
                    startPromise.complete();
                } else {
                    startPromise.fail(res.cause());
                }
            });
    }

}
