package model;

public class UserSubscription extends ConcertTicket {
  public UserSubscription(String name, double base) {
    super(name, base);
  }

  public UserSubscription(String name, double base, String category) {
    super(name, base, category);
  }

  public UserSubscription(String name, double base, String category, FanZonePass pass) {
    super(name, base, category, pass);
  }

  @Override
  public double calculateBillingPrice() {
    return round(getBase() * getRate());
  }
}
