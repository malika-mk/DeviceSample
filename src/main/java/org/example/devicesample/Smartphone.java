package org.example.devicesample;


public class Smartphone extends Device{
    protected int screenSize;
    protected int resolution;

    public Smartphone(){}

    public Smartphone(Devicetype type, String name, double price, double weight) {
        super(type, name, price, weight);
    }

    public Smartphone(Devicetype type, String name, double price, double weight, int screenSize, int resolution) {
        super(type, name, price, weight);
        this.screenSize = screenSize;
        this.resolution = resolution;
    }

    public int getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(int screenSize) {
        this.screenSize = screenSize;
    }

    public int getResolution() {
        return resolution;
    }

    public void setResolution(int resolution) {
        this.resolution = resolution;
    }

    @Override
    public String toString() {
        return "Smartphone{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", screenSize=" + screenSize +
                ", resolution=" + resolution +
                '}';
    }
}