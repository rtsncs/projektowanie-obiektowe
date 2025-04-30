import { Link } from "react-router";
import { useCart } from "../../hooks/Cart";

function Cart() {
  const { cart } = useCart();
  return (
    <div>
      <h1>Shopping Cart</h1>
      <li>
        {cart.map((item) => (
          <div key={item.id}>
            <h2>{item.name}</h2>
            <p>Price: ${item.price}</p>
          </div>
        ))}
      </li>
      <h2>Total: ${cart.reduce((total, item) => total + item.price, 0)}</h2>

      <Link to="/payment">Pay</Link>
    </div>
  );
}

export default Cart;
