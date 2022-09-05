# Description
	This is a spring-boot, maven based, project that implements some of the functionalities required for a store managemant tool.
# Features
	The main features provided bu thid project are:

		-allows you to manage the store database by following operations(`add-product`,`remove-product`, `change-price`,`find-product`, `list-products`)
		-allows to dynamically add users with different roles with these operations (`add-account`, `remove-account`, `add-account`)
		-all of the above operations have unit tests in place to prevent any regression and to shorten de development time when a new feature needs to be added
# General structure
This progect is consisted from 6 classes:

	Application - the `main` calss wich contains the main `ApplicationContext` and the endpoints
	Store - the class which contains most of the data about the store and and the controllers used in the Application endpoints
	Accounts - the class which represents the structure of an account
	Product - the class which contains the structure of a product
	SecurityConfig - this class contains the security filter which restricts the access to some endpoints and the 2 default accounts
	ApplicationsTests - this class contains the test suite of the project

#requests for manual testing
	///http://localhost:8080/add-product?name=pantofi&weight=2&price=10&description=O%20pereche%20de%20pantofi%20portocali%20de%20excep%C5%A3ie
	//http://localhost:8080/add-product?name=geanta&weight=1&price=100&description=Geanta%20abibas!@SpringBootApplication
	//localhost:8080/add-account?username=usr1&password=usr1&role=USER

