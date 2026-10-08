package model;

public abstract class ConcertTicket {
  private static final double FLOOR = 1.0;
  private static final double FAN = 1.6;
  private static final double VIP = 3.5;
  private static final double EXPRESS = 500.0;
  private static final double MINIMUM = 100.0;

  private final String name;
  private final double base;
  private final String category;
  private FanZonePass appliedOption = null;

  protected ConcertTicket(String name, double base) {
    this(name, base, "Танцпол");
  }

  protected ConcertTicket(String name, double base, String category) {
    this(name, base, category, null);
  }

  protected ConcertTicket(String name, double base, String category,
      FanZonePass appliedOption) {
    if (name == null || name.isBlank()) {
      System.out.println("Предупреждение: пустое название заменено на «Концерт».");
      name = "Концерт";
    }
    if (!Double.isFinite(base) || base <= 0) {
      System.out.println("Предупреждение: неверная базовая цена заменена на 100 руб.");
      base = MINIMUM;
    }
    this.name = name;
    this.base = base;
    this.category = category == null ? "" : category;
    this.appliedOption = appliedOption;
  }

  public String getName() {
    return name;
  }

  public double getBase() {
    return base;
  }

  public String getCategory() {
    return category;
  }

  public FanZonePass getAppliedOption() {
    return appliedOption;
  }

  protected double getRate() {
    return switch (category) {
      case "Танцпол" -> FLOOR;
      case "Фан-зона" -> FAN;
      case "VIP-ложа" -> VIP;
      default -> {
        System.out.println("Предупреждение: неизвестная категория «" + category
            + "», применён тариф «Танцпол».");
        yield FLOOR;
      }
    };
  }

  protected double round(double price) {
    if (!Double.isFinite(price) || price < 0 || price * 100 >= Long.MAX_VALUE) {
      System.out.println("Предупреждение: сумма вне допустимого диапазона.");
      return 0;
    }
    return Math.round(price * 100) / 100.0;
  }

  public abstract double calculateBillingPrice();

  public double calculateFinalPrice() {
    return calculateBillingPrice();
  }

  public double calculateFinalPrice(FanZonePass pass) {
    return calculateFinalPrice(pass, false);
  }

  public double calculateFinalPrice(FanZonePass pass, boolean fastTrack) {
    double price = calculateBillingPrice();
    if (price <= 0) {
      return 0;
    }
    if (pass == null) {
      System.out.println("Предупреждение: пропуск не указан, рассчитан обычный билет.");
      return price;
    }
    price = round(price + pass.getPrice() + (fastTrack ? EXPRESS : 0));
    if (price > 0) {
      appliedOption = pass;
    }
    return price;
  }

  public double upgradePass(FanZonePass pass) {
    return calculateFinalPrice(pass);
  }

  public double upgradePass(FanZonePass pass, boolean fastTrack) {
    return calculateFinalPrice(pass, fastTrack);
  }
}
