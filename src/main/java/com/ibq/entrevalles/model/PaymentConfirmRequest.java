package com.ibq.entrevalles.model;

public class PaymentConfirmRequest {
        private Long precio;
        private String currency;
        private String status;
		public Long getPrecio() {
			return precio;
		}
		public void setPrecio(Long precio) {
			this.precio = precio;
		}
		public String getCurrency() {
			return currency;
		}
		public void setCurrency(String currency) {
			this.currency = currency;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
        
        
        
}
