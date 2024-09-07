package xyz.hedy.gravity.prism.handler;

import io.vertx.core.Handler;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.net.NetSocket;
import xyz.hedy.gravity.prism.handler.impl.TCPSocketPrismImpl;
import xyz.hedy.gravity.prism.handler.impl.WebSocketPrismImpl;

/**
 * Created by hedy on 2024/9/6.
 */
public interface Prism<T> extends Handler<T> {

    static Prism<ServerWebSocket> websocketPrism() {
        return new WebSocketPrismImpl();
    }

    static Prism<NetSocket> tcpSocketPrism() {
        return new TCPSocketPrismImpl();
    }

}
