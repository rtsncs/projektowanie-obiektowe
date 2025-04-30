import { Link, Outlet } from "react-router";
import { Suspense } from "react";
import { useCart } from "./hooks/Cart";

function App() {
  const { cart } = useCart();
  return (
    <>
      <Suspense fallback={<p>Loading</p>}>
        <nav>
          <Link to="/">Products</Link>
          <Link to="/cart">Cart ({cart.length})</Link>
        </nav>
        <Outlet />
      </Suspense>
    </>
  );
}

export default App;
