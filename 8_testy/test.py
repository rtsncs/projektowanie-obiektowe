from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
import unittest

BASE_URL = "http://localhost:5173"


class Test(unittest.TestCase):
    def setUp(self):
        options = Options()
        options.add_argument("--headless")
        self.driver = webdriver.Chrome(options=options)
        self.driver.implicitly_wait(1)

    def tearDown(self):
        self.driver.quit()

    def test_nav_bar(self):
        self.driver.get(BASE_URL)
        navBar = self.driver.find_element(By.TAG_NAME, "nav")
        links = navBar.find_elements(By.TAG_NAME, "a")
        link_texts = [link.text for link in links]

        self.assertIn("Login", link_texts)
        self.assertIn("Register", link_texts)
        self.assertIn("Products", link_texts)
        self.assertIn("Cart (0)", link_texts)

    def test_products_nav(self):
        self.driver.get(BASE_URL)
        self.driver.find_element(By.LINK_TEXT, "Products").click()
        header = self.driver.find_element(By.TAG_NAME, "h1")
        self.assertEqual(header.text, "Products")

    def test_products_list(self):
        self.driver.get(BASE_URL)
        products = self.driver.find_elements(By.TAG_NAME, "li")
        self.assertGreaterEqual(len(products), 1)

    def test_product_has_name(self):
        self.driver.get(BASE_URL)
        product = self.driver.find_element(By.TAG_NAME, "li")
        name = product.find_element(By.TAG_NAME, "h2")
        self.assertIsNotNone(name)

    def test_product_has_price(self):
        self.driver.get(BASE_URL)
        product = self.driver.find_element(By.TAG_NAME, "li")
        price = product.find_element(By.TAG_NAME, "p")
        self.assertIsNotNone(price)
        self.assertIn("Price:", price.text)

    def test_add_to_cart(self):
        self.driver.get(BASE_URL)
        product = self.driver.find_element(By.TAG_NAME, "li")
        product.find_element(
            By.XPATH, "//button[contains(text(), 'Add to Cart')]").click()
        cart_link = self.driver.find_element(By.LINK_TEXT, "Cart (1)")
        self.assertIsNotNone(cart_link)

    def test_cart_nav(self):
        self.driver.get(BASE_URL)
        self.driver.find_element(By.PARTIAL_LINK_TEXT, "Cart").click()
        header = self.driver.find_element(By.TAG_NAME, "h1")
        self.assertEqual(header.text, "Shopping Cart")

    def test_cart_product_has_name(self):
        self.driver.get(BASE_URL + '/cart')
        product = self.driver.find_element(By.TAG_NAME, "li")
        name = product.find_element(By.TAG_NAME, "h2")
        self.assertIsNotNone(name)

    def test_cart_product_has_price(self):
        self.driver.get(BASE_URL + '/cart')
        product = self.driver.find_element(By.TAG_NAME, "li")
        price = product.find_element(By.TAG_NAME, "p")
        self.assertIsNotNone(price)
        self.assertIn("Price:", price.text)

    def test_cart_total(self):
        self.driver.get(BASE_URL + '/cart')
        total = self.driver.find_element(
            By.XPATH, "//p[contains(text(), 'Total:')]")
        self.assertIsNotNone(total)

    def test_pay_link(self):
        self.driver.get(BASE_URL + '/cart')
        pay_link = self.driver.find_element(By.LINK_TEXT, "Pay")
        self.assertIsNotNone(pay_link)

    def test_pay_nav(self):
        self.driver.get(BASE_URL + '/cart')
        self.driver.find_element(By.LINK_TEXT, "Pay").click()
        header = self.driver.find_element(By.TAG_NAME, "h1")
        self.assertEqual(header.text, "Payment")

    def test_payment_card_number_field(self):
        self.driver.get(BASE_URL + '/payment')
        field = self.driver.find_element(By.NAME, "cardNumber")
        self.assertIsNotNone(field)

    def test_payment_date_field(self):
        self.driver.get(BASE_URL + '/payment')
        field = self.driver.find_element(By.NAME, "expirationDate")
        self.assertIsNotNone(field)

    def test_payment_cvv_field(self):
        self.driver.get(BASE_URL + '/payment')
        field = self.driver.find_element(By.NAME, "cvv")
        self.assertIsNotNone(field)


if __name__ == '__main__':
    unittest.main()
