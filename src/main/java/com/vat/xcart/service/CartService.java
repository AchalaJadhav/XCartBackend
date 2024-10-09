package com.vat.xcart.service;

import com.vat.xcart.exception.ResourceNotFoundException;
import com.vat.xcart.exception.UserNotFoundException;
import com.vat.xcart.models.entity.Cart;
import com.vat.xcart.models.entity.Product;
import com.vat.xcart.models.entity.User;
import com.vat.xcart.models.pojo.CartItem;
import com.vat.xcart.repository.CartRepository;
import com.vat.xcart.repository.ProductRepository;
import com.vat.xcart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;


@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public Cart addToCart(String userId, String productId, int quantity) throws Exception {
        // Check if userId present
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("UserId Issue"));

        // Fetch the product
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new Exception("Product not found"));

        // Validate stock availability
        if (product.getStock() < quantity) {
            throw new Exception("Insufficient stock for product: " + product.getProductName());
        }

        Cart cart = cartRepository.findByUserId(userId).orElse(new Cart());

        if (Objects.isNull(cart.getId())) {
            // Create user's cart
            cart.setUserId(userId);
            cart.setItems(Collections.singletonList(new CartItem(productId, quantity)));

        } else {
            // Update cart
            cart.getItems().stream()
                    .filter(item -> item.getProductId().equals(productId))
                    .findFirst()
                    .ifPresentOrElse(
                            existingItem -> existingItem.setQuantity(quantity),
                            () -> cart.getItems().add(new CartItem(productId, quantity))
                    );
        }


            // Save the updated cart
            return cartRepository.save(cart);
        }

    public Cart getCartByUserId (String userId) throws Exception {
        return cartRepository.findByUserId(userId)
                .orElseThrow(() -> new Exception("No cart found for user"));
    }

    public Cart removeFromCart(String userId, String productId) {
        // Fetch the user's cart by userId
        Optional<Cart> optionalCart = cartRepository.findByUserId(userId);

        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();

            // Remove the item if it exists
            boolean itemRemoved = cart.getItems().removeIf(item -> item.getProductId().equals(productId));

            if (itemRemoved) {
                // Save the updated cart back to the repository if an item was removed
                cartRepository.save(cart);
                return cart;
            } else {
                throw new ResourceNotFoundException("Product not found in user cart");
            }
        } else {
            throw new UserNotFoundException("User not found with userId: " + userId);
        }
    }

    public Optional<Cart> decreaseProductCount(String userId, String productId) {

        Optional<Cart> cartOptional = cartRepository.findByUserId(userId);

        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();

            // Find the item in the cart with the given productId
            Optional<CartItem> cartItemOptional = cart.getItems().stream()
                    .filter(item -> item.getProductId().equals(productId))
                    .findFirst();

            if (cartItemOptional.isPresent()) {
                CartItem cartItem = cartItemOptional.get();
                int newQuantity = cartItem.getQuantity() - 1;

                if (newQuantity <= 0) {
                    // Remove the item from the cart if quantity is zero or less
                    cart.getItems().remove(cartItem);
                } else {
                    // Update the quantity
                    cartItem.setQuantity(newQuantity);
                }

                // Save the updated cart to the database
                cartRepository.save(cart);

                // Return the updated cart
                return Optional.of(cart);
            } else {
                // Product not found in the cart
                return Optional.empty();
            }
        } else {
            // Cart not found for the user
            throw new UserNotFoundException("Cart not found for user: " + userId);
        }
    }



    public Optional<Cart> increaseProductCount(String userId, String productId) {

        // Find the cart by userId
        Optional<Cart> cartOptional = cartRepository.findByUserId(userId);

        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();

            // Find the product in the cart
            Optional<CartItem> cartItemOptional = cart.getItems().stream()
                    .filter(item -> item.getProductId().equals(productId))
                    .findFirst();

            if (cartItemOptional.isPresent()) {
                // If the item is present, increase the quantity by 1
                CartItem cartItem = cartItemOptional.get();
                int newQuantity = cartItem.getQuantity() + 1;
                cartItem.setQuantity(newQuantity);
            } else {
                // If the product is not present in the cart, add it with quantity 1
                CartItem newItem = new CartItem(productId, 1);
                cart.getItems().add(newItem);
            }

            // Save the updated cart back to the repository
            cartRepository.save(cart);

            // Return the updated cart
            return Optional.of(cart);
        } else {
            // Cart not found for the user
            throw new UserNotFoundException("Cart not found for user: " + userId);
        }
    }


}
