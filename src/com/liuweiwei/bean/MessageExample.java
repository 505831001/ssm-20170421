package com.liuweiwei.bean;

import java.io.Serializable;

public class MessageExample<T> implements Serializable {
    private int code;
    private String message;
    private T data;

    public MessageExample() {
    }
    public MessageExample(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public MessageExample(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }

    public static <T> MessageExample<T> success() {
        MessageExample<T> example = new MessageExample<>();
        example.setCode(0);
        example.setMessage(null);
        example.setData(null);
        return example;
    }

    public static <T> MessageExample<T> failed(int code, String message) {
        MessageExample<T> example = new MessageExample<>();
        example.setCode(code);
        example.setMessage(message);
        example.setData(null);
        return example;
    }

    public static <T> MessageExample<T> data(T data) {
        MessageExample<T> example = new MessageExample<>();
        example.setCode(0);
        example.setMessage(null);
        example.setData(data);
        return example;
    }
}
