// سیستم سفارش — پاسخ پروژهٔ جلسه ۱۴
enum OrderStatus { pending, processing, shipped, delivered, cancelled }
enum PaymentMethod { cash, card, online }

class Order {
  final int id;
  OrderStatus status;
  final PaymentMethod paymentMethod;

  Order(this.id, this.status, this.paymentMethod);

  void advance() {
    switch (status) {
      case OrderStatus.pending: status = OrderStatus.processing;
      case OrderStatus.processing: status = OrderStatus.shipped;
      case OrderStatus.shipped: status = OrderStatus.delivered;
      default: break;
    }
  }

  void display() {
    print('Order #$id: ${status.name} | Payment: ${paymentMethod.name}');
  }
}

void main() {
  var order = Order(1, OrderStatus.pending, PaymentMethod.card);
  order.display();
  order.advance(); order.display();
  order.advance(); order.display();
  order.advance(); order.display();
}
