import { useLoaderData } from "react-router";
import Product from "../../models/Product";
import { useCart } from "../../hooks/Cart";

function Products() {
  const products = useLoaderData<Product[]>();
  const { addToCart } = useCart();

  return (
    <>
      <h1>Products</h1>

      <ul>
        {products.map((product) => (
          <li key={product.id}>
            <h2>{product.name}</h2>
            <p>Price: ${product.price}</p>
            <button onClick={() => addToCart(product)}>Add to Cart</button>
          </li>
        ))}
      </ul>
    </>
  );
}

export default Products;
