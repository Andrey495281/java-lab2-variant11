import billing.TicketOffice;
import model.ConcertTicket;
import model.CorporateSubscription;
import model.FanZonePass;
import model.UserSubscription;
import service.impl.EmailNotification;
import service.impl.SmsNotification;

public class Main {
  public static void main(String[] args) {
    FanZonePass pass = new FanZonePass("Дополнительный пропуск", 300);
    TicketOffice sms = new TicketOffice(new SmsNotification());
    TicketOffice email = new TicketOffice(new EmailNotification());

    ConcertTicket first = new UserSubscription("Рок-концерт", 2000);
    ConcertTicket second = new UserSubscription("Джазовый концерт", 2000, "Фан-зона");
    ConcertTicket third = new UserSubscription("Симфонический концерт", 2000, "VIP-ложа");
    ConcertTicket group = new CorporateSubscription("Концерт для компании", 2000, "VIP-ложа", 5);

    System.out.println("Вариант 11. Билеты на концерт");
    sms.processPayment(first, "+79001234567", first.calculateFinalPrice());
    sms.processPayment(second, "89001234567", second.calculateFinalPrice(pass));
    sms.processPayment(third, "+79001234567", third.calculateFinalPrice(pass, true));
    email.processPayment(group, "company@example.com", group.upgradePass(pass, true));

    System.out.println("\nПроверка некорректных данных");
    ConcertTicket invalid = new CorporateSubscription("Проверка", -10, "Неизвестно", 0);
    System.out.printf("Исправленная стоимость: %.2f руб.%n", invalid.calculateBillingPrice());
    sms.getNotificationService().sendNotification("Проверка", "123");
    email.getNotificationService().sendNotification("Проверка", "wrong-address");
    sms.processPayment(first, "+79001234567", -1);

    TicketOffice.printGlobalStats();
  }
}
