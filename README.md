# Simple RESTful webservice application

This application is a simple RESTful webservice that implements a common e-commerce scenario, placing and modifying orders.

# How to compile
mvn clean install

# How to run
mvn jetty:run

# How to run the automatted test suite
mvn clean test


# How to test the application

I suggest you to use postman, a chrome extension

To add a  produc: [GET] http://localhost:8080/store/product/populate/chinelo
                  [GET] http://localhost:8080/store/product/populate/patins
                  [GET] http://localhost:8080/store/product/populate/skate

(I know I should change it to POST)

List products:    [GET] http://localhost:8080/store/product/list

Search products:  [GET] http://localhost:8080/store/product/10
                        (10 is the product ID)

Place Order:      [POST] http://localhost:8080/store/order/place/
Body: raw
Accept:application/json
Content-Type:application/json
[{"id":10,"name":"chinelo"}]

List Orders:       [GET] http://localhost:8080/store/order/list

Product details:   [GET] http://localhost:8080/store/details/20
                   (20 is the product details)

Change order:  [POST] http://localhost:8080/store/order/change/20
Body: raw
Accept:application/json
Content-Type:application/json
[{"id":11,"name":"bolsa"}]
em que 20 é o ID da compra


# Other considerations

Integration tests is on the backlog.
