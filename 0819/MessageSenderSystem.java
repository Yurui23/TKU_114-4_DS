interface MessageSender {
    void send(String receiver, String message);
}

class EmailSender implements MessageSender {
    @Override
    public void send(String receiver, String message) {
        System.out.println("Email sent to " + receiver + " : " + message);
    }
}

class SmsSender implements MessageSender {
    @Override
    public void send(String receiver, String message) {
        System.out.println("SMS sent to " + receiver + " : " + message);
    }
}

class ConsoleSender implements MessageSender {
    @Override
    public void send(String receiver, String message) {
        System.out.println("Console output to " + receiver + " : " + message);
    }
}

public class MessageSenderSystem {
    public static void notify(MessageSender sender, String receiver, String message) {
        if (receiver == null || receiver.trim().isEmpty() || message == null || message.trim().isEmpty()) {
            System.out.println("Error: Receiver or message cannot be empty.");
            return;
        }
        sender.send(receiver, message);
    }

    public static void main(String[] args) {
        MessageSender email = new EmailSender();
        MessageSender sms = new SmsSender();
        MessageSender console = new ConsoleSender();

        notify(email, "user@example.com", "Hello via Email");
        notify(sms, "0912345678", "Hello via SMS");
        notify(console, "Admin", "Hello via Console");
        
        notify(email, "", "Test empty receiver");
        notify(sms, "0912345678", " ");
    }
}