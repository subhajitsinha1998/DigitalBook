**Introduction**  

DigitalBooks app which takes traditional books a step further, combining text with visual and audio elements to make authors' publications truly multimodal. Authors can write down their thoughts and assemble a collection of original or curated content ranging from photos, drawings, and images to audio and video clips -- in some cases, even animated text.  
  

And while Digital Book app can magically use images of autobiographical presentations or fantastical tales which are certainly options. It is also important to think beyond personal narratives to how authors might share the "stories/experiences" of their learning on any topic. And beyond author presentations and publications, plenty of students, teachers, doctors, engineers can jump on board, to create dynamic books and presentations that serve as instructional tools.  
  

Build digital book app. Below are the different Microservices, which need to be developed and deployed.  
**1.User Microservice**   
    Managed roles  
        i.	Guest user  
        ii.	Reader  
        iii.	Author  
**2.Book Microservice**  
    Crud application  
        i.	Guest user can search book, create account and get login  
        ii.	Author can search/add/edit/block book  
        iii.	Reader can search/subscribe/unsubscribe book  
  

**Required services**  
     User microservice  
     Book microservice  
  
  
**Possible rest clients**  
    We will use below clients for our microservice applications  
        	Postman  
        	Postwoman (hoppscotch.io)  
        	Swagger  
        	Rest Client - plugin  
        	Angular app  
        	Other microservice applications  
  
    Any client from below list can consume our microservice (we will not use them):  
        	React app  
        	Jmeter  
        	Android app  
        	Ios app  
        	.Net application  
        	python application  
  

**Use Cases**  

User  
Story #	User Story Name	User Story  
US_01	Guest User Features		As a guest user I want to search for books based on title, category, author, price, so I can see search result containing book logo, title, author, publisher, price, published date, category.  
	As a guest user,  I want to create an account. I can create account as either reader or author  
	As a guest user, I want to get login to my account.  
US_02	Reader Features		As a reader, I want to search for books based on title, category, author, price, so I can see search result containing book logo, title, author, publisher, price, published date, category.  
	As a reader, (from the above search result) I want to select a book (which is not blocked) and subscribe to it. I want to get a unique subscription_id in response after subscription is done.  
	As a reader, I can read only subscribed books.   
	As a reader, I can not read blocked book or book which is not subscribed.  
	As a reader, I want to    
	view history of all previous subscriptions  
	view invoice using subscription_id  
	cancel the subscription within 24 hrs  
US_02	Author Features	1.	As an author, I want to create/edit book so that reader can subscribe to it.  
2.	As an author, I want to create book with below properties   
	logo: image   
	title: Spiderman is back  
	category: comic  
	price: 24  
	author: current user name}  
	publisher: Moon publisher  
	published date: 22/04/2022   
	chapters/content  
	active: true  
3.	As an author, I want to block and unblock a book.   
4.	When book is blocked  
	It will not be shown in Search results for the reader.  
	Readers (who have already subscribed to this book) should get notification about the   unavailability of book.  


**Proposed Rest Endpoints**  
If you think rest endpoints need improvisation and modification as per given use case, you can make necessary changes.  

GET	/api/v1/digitalbooks/search?category&title&author&price&publisher	Guest, Reader and Author can search books  
POST	/api/v1/digitalbooks/sign-up	Guest can create account as either reader or author  
POST	/api/v1/digitalbooks/sign-in	Guest can get login  
POST	/api/v1/digitalbooks/{book-id}/subscribe  

payload: {bookId, reader {email / pk}}	Reader can subscribe to a book  
GET	/api/v1/digitalbooks/readers/{emailId}/books	Reader can fetch all subscribed books  
GET	/api/v1/digitalbooks/readers/{emailId}/books/{subscription-id}	Reader can fetch a subscribe book   
GET	/api/v1/digitalbooks/readers/{emailId}/books/{subscription-id}/read	Reader can read book content  
POST	/api/v1/digitalbooks/readers/{emailId}/books/{subscription-id}/cancel-subscription	Reader can cancel the subscription within 24 hrs of subscription  
POST	/api/v1/digitalbooks/author/{author-id}/books  
  
Payload: {logo, title, category, price, author, publisher, published date, chapters/content, active}	Author creates a book  
PUT	/api/v1/digitalbooks/author/{author-id}/books/{book-id}  
  
Payload: {logo, title, category, price, author, publisher, published date, chapters/content,   active}	Author can edit a book  
POST	/api/v1/digitalbooks/author/{author-id}/books/{book-id}?block=yes	Author can block a book  
POST	/api/v1/digitalbooks/author/{author-id}/books/{book-id}?block=no	Author can unblock a book  

 


