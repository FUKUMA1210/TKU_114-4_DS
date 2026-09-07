interface MessageSender {
    void send(String receiver, String message);
}

class EmailSender implements MessageSender {

    @Override
    public void send(String receiver, String message) {
        if (receiver == null || receiver.isBlank()) {
            System.out.println("Email 發送失敗：收件人不能為空");
            return;
        }

        if (message == null || message.isBlank()) {
            System.out.println("Email 發送失敗：訊息不能為空");
            return;
        }

        System.out.println("Email 發送給：" + receiver);
        System.out.println("內容：" + message);
    }
}

class SmsSender implements MessageSender {

    @Override
    public void send(String receiver, String message) {
        if (receiver == null || receiver.isBlank()) {
            System.out.println("SMS 發送失敗：收件人不能為空");
            return;
        }

        if (message == null || message.isBlank()) {
            System.out.println("SMS 發送失敗：訊息不能為空");
            return;
        }

        System.out.println("SMS 發送給：" + receiver);
        System.out.println("內容：" + message);
    }
}

class ConsoleSender implements MessageSender {

    @Override
    public void send(String receiver, String message) {
        if (receiver == null || receiver.isBlank()) {
            System.out.println("console發送失敗：收件人不能為空");
            return;
        }

        if (message == null || message.isBlank()) {
            System.out.println("console發送失敗：訊息不能為空");
            return;
        }

        System.out.println("console 發送給：" + receiver);
        System.out.println("內容：" + message);
    }
}

public class MessageSenderSystem {

    public static void notify(
            MessageSender sender,
            String receiver,
            String message) {

        sender.send(receiver, message);
    }

    public static void main(String[] args) {

        MessageSender email = new EmailSender();
        MessageSender sms = new SmsSender();
        MessageSender console = new ConsoleSender();

        notify(email, "amy@gmail.com", "你好");
        System.out.println();

        notify(sms, "0912345678", "提醒你明天上課");
        System.out.println();

        notify(console, "Amy", "測試訊息");
        System.out.println();

        notify(email, "", "空白收件人測試");
        notify(sms, "0912345678", "");
    }
}