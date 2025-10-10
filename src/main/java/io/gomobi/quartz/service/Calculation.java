package io.gomobi.quartz.service;

public interface Calculation<T> {

     T calculate(T data);

}
