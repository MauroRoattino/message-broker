package ar.edu.ues21.messagebrokerproducer.config;


public class Constants {

    public static final String ACKS_CONFIG = "all";

    //public static final Integer  BATCH_SIZE_CONFIG = 1024 * 1024; // 1 MB

    public static final Integer LINGER_MS_CONFIG = 10;
    public static final Integer BUFFER_MEMORY_CONFIG = 128 * 1024 * 1024; // 128 MB
    public static final Integer RETRIES_CONFIG = 10;
    public static final Integer REQUEST_TIMEOUT_MS_CONFIG = 60000;
    public static final Integer RETRY_BACKOFF_MS_CONFIG = 1000;

    public static final String COMPRESSION_TYPE_CONFIG = "gzip";

    public Constants() {
    }
}
