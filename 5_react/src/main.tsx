import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import { createBrowserRouter, RouterProvider } from "react-router";
import Products from "./routes/products/Products";
import ProductsLoader from "./routes/products/ProductsLoader";
import Payment from "./routes/payment/Payment";
import Cart from "./routes/cart/Cart";
import { CartProvider } from "./hooks/Cart";
import App from "./App";

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    children: [
      {
        path: "/",
        element: <Products />,
        loader: ProductsLoader,
      },
      {
        path: "/payment",
        element: <Payment />,
      },
      {
        path: "/cart",
        element: <Cart />,
      },
    ],
  },
]);

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <CartProvider>
      <RouterProvider router={router} />
    </CartProvider>
  </StrictMode>,
);
