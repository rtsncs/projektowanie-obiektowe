import { useCart } from "../../hooks/Cart";
import PaymentData from "../../models/PaymentData";

function Payment() {
  const { cart } = useCart();

  const handlePayment = (event: React.FormEvent) => {
    event.preventDefault();
    const form = event.target as HTMLFormElement;
    const paymentData: PaymentData = {
      cardNumber: form.cardNumber.value as string,
      expirationDate: form.expirationDate.value as string,
      cvv: form.cvv.value as string,
      products: cart,
    };

    fetch("/api/payment", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(paymentData),
    })
      .then((response) => {
        if (response.ok) {
          alert("Payment successful!");
        } else {
          console.error("Payment failed");
          alert("Payment failed. Please try again.");
        }
      })
      .catch((error) => {
        console.error("Payment failed:", error);
        alert("Payment failed. Please try again.");
      });
  };

  return (
    <>
      <h1>Payment</h1>
      <h2>Total: ${cart.reduce((total, item) => total + item.price, 0)}</h2>
      <form onSubmit={handlePayment}>
        <label>
          Card Number:
          <input type="text" name="cardNumber" required />
        </label>
        <br />
        <label>
          Expiration Date:
          <input type="text" name="expirationDate" required />
        </label>
        <br />
        <label>
          CVV:
          <input type="text" name="cvv" required />
        </label>
        <br />
        <button type="submit">Pay</button>
      </form>
    </>
  );
}

export default Payment;
