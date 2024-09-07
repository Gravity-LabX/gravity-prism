package xyz.hedy.gravity.prism.handler.impl;

import io.vertx.core.http.ServerWebSocket;
import xyz.hedy.gravity.prism.handler.Prism;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by hedy on 2024/9/6.
 */
public class WebSocketPrismImpl implements Prism<ServerWebSocket> {

    private final Map<String, ServerWebSocket> connections = new ConcurrentHashMap<>();


    @Override
    public void handle(ServerWebSocket serverWebSocket) {
        serverWebSocket.closeHandler(v -> {
            connections.remove(serverWebSocket.textHandlerID());
        });
        serverWebSocket.handler(buffer -> {
            connections.values().forEach(ws -> {
                ws.writeFinalTextFrame(buffer.toString());
            });
        });
        connections.put(serverWebSocket.textHandlerID(), serverWebSocket);

    }
}
