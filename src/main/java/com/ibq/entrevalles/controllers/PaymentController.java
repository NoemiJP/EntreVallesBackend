package com.ibq.entrevalles.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibq.entrevalles.model.PaymentConfirmRequest;
import com.ibq.entrevalles.model.PaymentIntentRequest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

@RestController
public class PaymentController {

	 @Value("${stripe.secretKey}")
	    private String stripeSecretKey;

	    @PostMapping("/payment")
	    public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentIntentRequest intent) {
	        Stripe.apiKey = stripeSecretKey;

	        try {
	            PaymentIntent paymentIntent = PaymentIntent.create(
	                new PaymentIntentCreateParams.Builder()
	                    .setCurrency("eur")
	                    .setAmount(intent.getPrecio()) // Monto en centavos
	                    .build()
	            );

	            // Devuelve el Client Secret
	            return ResponseEntity.ok(paymentIntent.getClientSecret());
	        } catch (StripeException e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el PaymentIntent");
	        }
	    }
	    
	    @GetMapping("/confirmPayment/{payment}")
	    public ResponseEntity<PaymentConfirmRequest> getPaymentDetails(@PathVariable String payment) {
	        Stripe.apiKey = stripeSecretKey;

	        try {
	            PaymentIntent paymentIntent = PaymentIntent.retrieve(payment);
	            PaymentConfirmRequest paymentIntentDetails = new PaymentConfirmRequest();
	            paymentIntentDetails.setPrecio(paymentIntent.getAmount());
	            paymentIntentDetails.setCurrency(paymentIntent.getCurrency());
	            paymentIntentDetails.setStatus(paymentIntent.getStatus());
	            // Otros detalles que quieras mostrar...

	            return ResponseEntity.ok(paymentIntentDetails);
	        } catch (StripeException e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	        }
	    }
}
