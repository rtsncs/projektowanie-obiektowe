#!/bin/bash

curl -X POST http://localhost:8000/product/new -d "name=TestProduct&price=10"

curl -X POST http://localhost:8000/order/new

curl -X POST http://localhost:8000/order/item/new -d "order_id=1&product_id=1&quantity=2"
