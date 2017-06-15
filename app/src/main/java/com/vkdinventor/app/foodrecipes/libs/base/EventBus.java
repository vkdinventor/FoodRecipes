package com.vkdinventor.app.foodrecipes.libs.base;

public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
