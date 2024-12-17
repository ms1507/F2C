
Sql command generated first time:

create table orders (
        order_id bigint not null auto_increment,
        customer_id varchar(255) not null,
        delivery_date datetime(6) not null,
        delivery_status varchar(255) not null,
        description varchar(255),
        order_date datetime(6) not null,
        order_total float(53) not null,
        payment_mode varchar(255) not null,
        payment_type varchar(255) not null,
        shipping_address varchar(255) not null,
        primary key (order_id)
    )



    Request payload:

    {
      "order": {
        "customerId": 1,
        "deliveryDate": "2024-12-12T10:00:00",
        "deliveryStatus": "Pending",
        "description": "Order for farm equipment",
        "orderDate": "2024-12-11T10:00:00",
        "orderTotal": 5000,
        "paymentMode": "Credit Card",
        "paymentType": "Online",
        "shippingAddress": "123 Farm Lane, Ruralville"
      },
      "productIds": [101, 102, 103]
    }