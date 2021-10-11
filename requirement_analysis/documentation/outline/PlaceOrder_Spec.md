# SPECIFICATIONS FOR "PLACE ORDER" USECASE

## NAME: Place order
## BRIEF DESCRIPTIONS:
After having selected the goods to buy to the cart, customer requests to place an order with delivery details. Order payment must be done before the items being delivered.

## ACTORS:
- Customer

## FLOW OF EVENTS:
### Preconditions:
- The cart of user has had some items
- Customer is viewing the cart

### Basic flow of usecase:
Step 1: Customer chooses to place an order
Step 2: AIMS checks and notifies of the number of items in the inventory is enough for order
Step 3: Customer updates the cart according to current quantity states of items
Step 4: Customer requests to place order again
Step 5: AIMS shows the form to fill delivery information and delivery instruction
Step 6: Customer fills in the delivery information and delivery instruction and submit
Step 7: AIMS validates the customer's input
Step 8: AIMS calculates fees
Step 9: AIMS displays the invoice with details and total amount of money to pay
Step 10: Customer confirms the invoice
Step 11: AIMS saves the invoice


Step 15: AIMS records payment details and set the order to pending state
Step 16: AIMS display payment details to customer
Step 17: AIMS sends and email of invoice transaction information to customer

### Alternative flow of usecase:
- At anytime between step 1 and 13, customer can choose to cancel placing order
  - AIMS saves the current state of the cart
  - AIMS clears all the received information
- At anytime after step 13, customer can choose to cancel placing order
  - AIMS displays a notification about cancel order decision
  - Customer confirms the cancellation
  - AIMS calls "REFUND" usecase
- At step 6 => 6a: Customer chooses to place rush order
  - AIMS calls "PLACE RUSH ORDER" usecase
- At step 7 => 7a: Customer lefts a blank field
  - AIMS notifies about the blank field, waiting for fullfilment
  