package dev.lokeshbisht.CartService.repository;

import dev.lokeshbisht.CartService.model.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, String> {
}
