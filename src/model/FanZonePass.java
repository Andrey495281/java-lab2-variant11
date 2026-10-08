package model;

public class FanZonePass {
  private final String name;
  private double price;

  public FanZonePass(String name, double price) {
    if (name == null || name.isBlank()) {
      System.out.println("Предупреждение: пустое название заменено на «Пропуск».");
      name = "Пропуск";
    }
    this.name = name;
    setPrice(price);
  }

  public String getName() {
    return name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    if (!Double.isFinite(price) || price < 0) {
      System.out.println("Предупреждение: неверная цена пропуска заменена на 0 руб.");
      price = 0;
    }
    this.price = price;
  }
}
