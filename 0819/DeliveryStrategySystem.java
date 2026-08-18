interface DeliveryMethod {
    double calculateFee();
    String getEstimatedDeliveryDescription();
}

class HomeDelivery implements DeliveryMethod {
    @Override
    public double calculateFee() {
        return 120.0;
    }

    @Override
    public String getEstimatedDeliveryDescription() {
        return "預計 1-2 個工作天內宅配到府。";
    }
}

class ConvenienceStoreDelivery implements DeliveryMethod {
    @Override
    public double calculateFee() {
        return 60.0;
    }

    @Override
    public String getEstimatedDeliveryDescription() {
        return "預計 2-3 個工作天內送達指定超商。";
    }
}

class SelfPickup implements DeliveryMethod {
    @Override
    public double calculateFee() {
        return 0.0;
    }

    @Override
    public String getEstimatedDeliveryDescription() {
        return "完成訂單後可立即至實體店面自取。";
    }
}

class OrderService {
    private DeliveryMethod deliveryMethod;

    public OrderService(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod != null ? deliveryMethod : new SelfPickup();
    }

    public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
        if (deliveryMethod != null) {
            this.deliveryMethod = deliveryMethod;
        }
    }

    public void processOrder() {
        System.out.println("運費計算: " + deliveryMethod.calculateFee() + " 元");
        System.out.println("配送說明: " + deliveryMethod.getEstimatedDeliveryDescription());
        System.out.println("-------------------------");
    }
}

public class DeliveryStrategySystem {
    public static void main(String[] args) {
        OrderService order1 = new OrderService(new HomeDelivery());
        System.out.println("【訂單一：宅配】");
        order1.processOrder();

        OrderService order2 = new OrderService(null);
        System.out.println("【訂單二：Null 測試 (預設自取)】");
        order2.processOrder();
    }
}