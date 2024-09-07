package xyz.hedy.gravity.prism.protocol;

import lombok.Getter;

import java.io.Serializable;

/**
 * Created by hedy on 2024/9/6.
 */

public final class GravityPayload implements Serializable {

    private static final long serialVersionUID = 6508271736024195818L;

    @Getter
    public enum Type {
        CONNECT((byte) 0x60), HEARTBEAT((byte) 0x65), RELAYED((byte) 0x66);

        private final byte value;

        Type(byte value) {
            this.value = value;
        }
    }

    @Getter
    private final String magic = "Gravity";

    @Getter
    private long len;

    @Getter
    private Type type;

    @Getter
    private String source;

    @Getter
    private String destination;

    @Getter
    private byte[] payload;

    private GravityPayload(Type type, byte[] payload) {
        this.type = type;
        this.payload = payload;
        this.len = payload.length;
    }

    public static GravityPayload buildPayload(Type type, byte[] payload) {
        return new GravityPayload(type, payload);
    }

}
