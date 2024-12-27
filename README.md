### Saga Pattern ile Event-Driven İletişim ve Rate Limiter Kullanımı

Bu projede, **Saga Pattern** ve **Koreografi yöntemi** kullanarak mikroservisler arasında **event-driven iletişim** sağladım. Bu yöntem, servislerin birbirleriyle olaylar üzerinden iletişim kurmasını ve süreçlerin tutarlı bir şekilde yönetilmesini sağlar.

Ayrıca, belirli bir zaman diliminde işlenebilecek istek sayısını kontrol etmek için **Rate Limiter** kullandım:

```java
@GetMapping("/getAll/orders")
@RateLimiter(name = "default")
public List<GetAllOrderResponse> getAllOrders() {
    return orderService.getAllOrderResponse();
}
````
Bu örnekte, @RateLimiter ile /getAll/orders endpoint'ine gelen istekler sınırlandırılmıştır.