package service.impl;

import service.NotificationService;

public class EmailNotification implements NotificationService {
  @Override
  public void sendNotification(String message, String recipient) {
    if (recipient != null && recipient.contains("@") && recipient.contains(".")) {
      System.out.println("[EMAIL] На адрес " + recipient + " отправлено письмо: " + message);
    } else {
      System.out.println("Предупреждение: неверный Email, уведомление не отправлено.");
    }
  }
}
