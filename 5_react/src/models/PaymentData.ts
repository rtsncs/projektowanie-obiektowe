import Product from "./Product";

export default interface PaymentData {
  cardNumber: string;
  expirationDate: string;
  cvv: string;
  products: Product[];
}
