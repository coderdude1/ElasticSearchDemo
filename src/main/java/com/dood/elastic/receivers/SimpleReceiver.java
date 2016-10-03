package com.dood.elastic.receivers;

//@Service //commented out using java config to load this bean via config
public class SimpleReceiver {

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
    }
}
