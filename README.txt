# How to compile
mvn clean install

# How to run
mvn jetty:run

# How to run the automatted test suite
mvn clean test


# How to test the application

I suggest you to use postman, a chrome extension

Incluir produto: [GET] http://localhost:8080/store/product/populate/chinelo
                 [GET] http://localhost:8080/store/product/populate/patins
                 [GET] http://localhost:8080/store/product/populate/skate

Listar produto:  [GET] http://localhost:8080/store/product/list

Buscar produto:  [GET] http://localhost:8080/store/product/10
em que 10 é o ID do produto

Incluir compra:  [POST] http://localhost:8080/store/order/place/
Body: raw
Accept:application/json
Content-Type:application/json
[{"id":10,"name":"chinelo"}]

Listar pedidos:  [GET] http://localhost:8080/store/order/list

Detalhar pedido: [GET] http://localhost:8080/store/details/20
em que 20 é o ID do produto

Alterar pedido:  [POST] http://localhost:8080/store/order/change/20
Body: raw
Accept:application/json
Content-Type:application/json
[{"id":11,"name":"bolsa"}]
em que 20 é o ID da compra


# Other considerations

I tried to make a funcional test using junit together with jersey, but I couldn't get it to work and because 
there had been a lot of time already, I decided not to do it.

Current projects I have worked on made use of SOAP UI for those kind of tests. But as that wasn't mentioned
on the instructions, I decided not to use it.
