package com.example.bancopcr.service;

import com.telesign.MessagingClient;
import com.telesign.RestClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {
    private MessagingClient messagingClient;
    private SmsService smsService;

    @Value("${telesign.sms.sender}")
    private String SENDER_ID;

    @Autowired
    public SmsService(MessagingClient messagingClient) {
        this.messagingClient = messagingClient;
        this.smsService = smsService;
    }

    public void enviarSms(String numeroDestino, String mensagem) throws Exception {
        RestClient.TelesignResponse response = messagingClient.message(numeroDestino, mensagem, SENDER_ID, null);
        if (response.ok) {
            System.out.println("Mensagem enviada: " + response.json);
        } else {
            throw new Exception("Erro ao enviar mensagem: ");// + response.code + " " + response.error);
        }
    }
}
