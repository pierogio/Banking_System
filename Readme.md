Banking System

The API handles some of the basics operations of a Banking System.

USER

It includes three different types of users which extends user’s abstract class parameters:

•	Admin  

Can perform most of the actions in the system.
Admins are able to:
-	Check balance
     (route “/balance/accountNumber/{accountNumber}”);
-	Add balance
     (route “/balance/add”);
-	Add account
     (route “/account/add”);
-	Update account status
     (route “/account/status”);
-	Delete account
     (route “/account/delete/{accountNumber}”);
-	Decrease balance
     (route “/balance/decrease”);
-	Add new third-party
     (route “/thirdparty/add”).

•	AccountHolder

Can manage more accounts and realize transactions:
-	Check accounts
     (route “/accountholder/account/{accountHolderid}”);
-	Check balance
     (route “/balance/accountholder/{accountNumber}”);
-	Transfer balance
     (route “/balance/transfer”).

•	Third-Party

Are external to the system, but they can:
-	Transfer money
     (route “/thirdparty/transfer”).

ACCOUNT

Accounts are divided in four categories that extends account’s abstract class parameters:

•	CheckingAccount:
 Have a primaryOwner which age is equal or more than 24, a minimumBalance of 250 and a monthlyMaintenanceFee of 12.

•	StudentCheckingAccount
 Have a primaryOwner which age is less than 24 years and don’t have monthlyMaintenanceFee.

•	Savings
 Savings have a default interest rate of 0.0025, a maximum interest rate of 0.5 and a default minimumBalance of 1000.

•	CreditCard
 CreditCard have a creditLimit of 100 and interestRate of 0.2.
