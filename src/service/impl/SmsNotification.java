package service.impl;

import service.NotificationService;

public class SmsNotification implements NotificationService {
  @Override
  public void sendNotification(String message, String recipient) {
    if (recipient != null
        && ((recipient.startsWith("+7") && recipient.length() == 12)
            || (recipient.startsWith("8") && recipient.length() == 11))
        && recipient.substring(recipient.startsWith("+") ? 1 : 0).matches("[0-9]+")) {
      System.out.println("[SMS] На номер " + recipient + " отправлено сообщение: " + message);
    } else {
      System.out.println("Предупреждение: неверный телефон, уведомление не отправлено.");
    }
  }
}
