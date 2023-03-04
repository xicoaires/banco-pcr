package com.example.bancopcr.config;

import com.telesign.MessagingClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TeleSignConfig {

    private String customerId = "115B7C5A-8AD0-492C-A5C3-5F98A61A9B8A";
    private String apiKey = "FGwyhx5YwQQ1qQIhTTEp++GkbS9JR9/87H5yLVHgxcTLeGA8dueb+q8dKU5rJgbkL7xt+wFmckW11HM+oDihZQ==";

    @Bean
    public MessagingClient messagingClient() {
        return new MessagingClient(customerId, apiKey);
    }
}
