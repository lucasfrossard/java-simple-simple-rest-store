# Avenue Code Java Challenge

Your task is to develop a simple RESTful webservice that implements a common e-commerce scenario, placing and modifying orders. We have provided you with an mavenized starter template that provides you with the necessary dependencies to build, test and run a webservice using the following:

- Core tools:
    - JDK 7
    - Maven 3
- Compile scope:
    - Guava
    - slf4j/logback
    - Spring
    - Hibernate
    - Jersey / JAX-RS
    - Jackson
    - H2 embedded database
- Test scope:
    - JUnit/Hamcrest
    - Mockito
    - PowerMock
    - Spring Test
    - dbUnit

You may add additional dependencies, tools and maven plugins as you deem necessary to complete your application. 

# REST API

Your service should consist of two RESTful resources:

## Products
	
which should implement two methods:
		
- list product catalog
- read a single product by its id
			
## Orders
	
which should implement four methods:
		
- place an order
- modify an existing order
- list placed orders
- read an existing order by its id
			
Your service should use JSON as its representation format for objects
			
You will need to create an appropriate relational schema to persist your entities, along with any required mappings.

### Delivery Instructions ###

1. You must provide his BitBucket username. A free BitBucket account can be created at http://bitbucket.org
1. The recruiter will give you read permission to a repository named **java-challenge**, at https://bitbucket.org/ac-recruitment/java-challenge
1. You must fork this repository into a private repository on your own account and push your code in there.
1. Once finished, you must give the user **ac-recruitment** read permission on your repository so that you can be evaluated. Then, please contact back your recruiter and he will get an engineer to evaluate your test.
1. After you are evaluated, the recruiter will remove your read permission from the original repository.

### Format ###

* This assessment must be delivered within 2 days.
* You must provide a README.txt (plain text) or a README.md (Markdown) file at the root of your repository, explaining:
    * How to compile and run the application.
    * How to run the suite of automated tests.
    * Mention anything that was asked but not delivered and why, and any additional comments.
* Any questions, please send an email to **recruitment.engineering@avenuecode.com**

Thank you,
The AvenueCode Recruiting Team