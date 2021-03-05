package org.Main.Worker;

@FunctionalInterface
public interface Function0<Out> {
    public Out apply() throws InterruptedException;
}