package billing;

import model.ConcertTicket;
import service.NotificationService;

public class TicketOffice {
  private static int totalOrdersCount;
  private static double totalRevenue;
  private final NotificationService notificationService;

  public TicketOffice(NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  public NotificationService getNotificationService() {
    return notificationService;
  }

  public static int getTotalOrdersCount() {
    return totalOrdersCount;
  }

  public static double getTotalRevenue() {
    return totalRevenue;
  }

  public void processPayment(ConcertTicket sub, String contact, double finalPrice) {
    if (sub == null || notificationService == null) {
      System.out.println("Предупреждение: билет или сервис уведомлений не указан. Оплата отменена.");
      return;
    }
    double revenue = totalRevenue + finalPrice;
    if (!Double.isFinite(finalPrice) || finalPrice <= 0
        || !Double.isFinite(revenue) || revenue * 100 >= Long.MAX_VALUE) {
      System.out.println("Предупреждение: неверная сумма. Оплата отменена.");
      return;
    }
    totalRevenue = Math.round(revenue * 100) / 100.0;
    totalOrdersCount++;
    String message = String.format("Оплачен заказ «%s»: %.2f руб.",
        sub.getName(), finalPrice);
    notificationService.sendNotification(message, contact);
  }

  public static void printGlobalStats() {
    System.out.println("\nСтатистика кассы");
    System.out.println("Оплачено заказов: " + getTotalOrdersCount());
    System.out.printf("Общая выручка: %.2f руб.%n", getTotalRevenue());
  }
}
