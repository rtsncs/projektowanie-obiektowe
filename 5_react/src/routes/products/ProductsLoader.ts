import Product from "../../models/Product";

export default async function ProductsLoader() {
  const response = await fetch("/api/products");
  const products = (await response.json()) as Product[];
  return products;
}
