package model;

public class CorporateSubscription extends ConcertTicket {
  private final int activeLicensesCount;

  public CorporateSubscription(String name, double base, String category,
      int activeLicensesCount) {
    super(name, base, category);
    if (activeLicensesCount < 1) {
      System.out.println("Предупреждение: неверное число мест заменено на 1.");
      activeLicensesCount = 1;
    }
    this.activeLicensesCount = activeLicensesCount;
  }

  public int getActiveLicensesCount() {
    return activeLicensesCount;
  }

  @Override
  public double calculateBillingPrice() {
    return round(getBase() * getRate() * activeLicensesCount);
  }

  @Override
  public double calculateFinalPrice(FanZonePass pass, boolean fastTrack) {
    System.out.println("Предупреждение: корпоративный заказ не использует отдельный"
        + " пропуск и проход без очереди. Применена стандартная цена группы.");
    return calculateBillingPrice();
  }
}
