interface PricingPolicy {
    double calculatePrice(double originalPrice);
}

class NormalPricing implements PricingPolicy {
    @Override
    public double calculatePrice(double originalPrice) {
        return Math.max(0, originalPrice);
    }
}

class VipPricing implements PricingPolicy {
    @Override
    public double calculatePrice(double originalPrice) {
        return Math.max(0, originalPrice) * 0.85;
    }
}

class ThresholdDiscountPricing implements PricingPolicy {
    @Override
    public double calculatePrice(double originalPrice) {
        double price = Math.max(0, originalPrice);
        if (price >= 2000) {
            return price - 300;
        }
        return price;
    }
}

interface NotificationChannel {
    boolean sendNotification(String message);
}

class EmailNotification implements NotificationChannel {
    @Override
    public boolean sendNotification(String message) {
        if (message == null || message.trim().isEmpty()) return false;
        System.out.println("[Email 發送] " + message);
        return true;
    }
}

class SmsNotification implements NotificationChannel {
    @Override
    public boolean sendNotification(String message) {
        if (message == null || message.trim().isEmpty()) return false;
        System.out.println("[SMS 發送] " + message);
        return true;
    }
}

class ConsoleNotification implements NotificationChannel {
    @Override
    public boolean sendNotification(String message) {
        if (message == null || message.trim().isEmpty()) return false;
        System.out.println("[Console 顯示] " + message);
        return true;
    }
}

class CheckoutResult {
    public String orderId;
    public double originalPrice;
    public double finalPrice;
    public boolean notificationStatus;

    public CheckoutResult(String orderId, double originalPrice, double finalPrice, boolean notificationStatus) {
        this.orderId = orderId;
        this.originalPrice = originalPrice;
        this.finalPrice = finalPrice;
        this.notificationStatus = notificationStatus;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId + 
               " | 原價: " + originalPrice + 
               " | 結帳金額: " + finalPrice + 
               " | 通知狀態: " + (notificationStatus ? "成功" : "失敗");
    }
}

public class FlexibleCheckoutSystem {

    public static CheckoutResult checkout(String orderId, double originalPrice, PricingPolicy policy, NotificationChannel channel) {
        String safeOrderId = (orderId == null || orderId.trim().isEmpty()) ? "UNKNOWN_ORDER" : orderId;
        double safePrice = Math.max(0, originalPrice);
        
        double finalPrice = safePrice;
        if (policy != null) {
            finalPrice = policy.calculatePrice(safePrice);
        }
        
        boolean status = false;
        if (channel != null) {
            String msg = "訂單 " + safeOrderId + " 結帳完成，金額為 " + finalPrice;
            status = channel.sendNotification(msg);
        }
        
        return new CheckoutResult(safeOrderId, safePrice, finalPrice, status);
    }

    public static void main(String[] args) {
        PricingPolicy vip = new VipPricing();
        NotificationChannel email = new EmailNotification();

        CheckoutResult r1 = checkout(null, -500, vip, email);
        System.out.println(r1);

        CheckoutResult r2 = checkout("ORD-999", 1000, null, null);
        System.out.println(r2);
    }
}