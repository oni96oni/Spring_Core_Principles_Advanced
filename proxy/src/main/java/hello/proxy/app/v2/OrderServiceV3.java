package hello.proxy.app.v2;

public class OrderServiceV3 {

    private final OrderRepositoryV2 orderRepository;

    public OrderServiceV3(OrderRepositoryV2 orderRepositoryV2) {
        this.orderRepository = orderRepositoryV2;
    }

    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }

}
