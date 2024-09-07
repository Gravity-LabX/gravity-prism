package xyz.hedy.gravity.prism.handler;

/**
 * Created by hedy on 2024/9/6.
 */
public interface PrismEvents {

    void onConnect(String source, String destination);

    void onHeartbeat(String source, String destination);

    void onRelayed(String source, String destination, byte[] payload);

    void onDisconnect(String source, String destination);
}
