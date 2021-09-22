Outside-In TDD with Acceptance Tests
====================================

### Objective

Learn and practice the double loop of TDD
Test application from outside, identifying side effects

### Problem description - Bank kata

Create a simple bank application with the following features:   
 
___
#### BANK ACCOUNT STATEMENT

 **Acceptance criteria**    
 **As a** Bank Client       
 **I want** to see my history    
 **So that** I can check its state and the various operations 

 Rule 1 : The print statement must look like :

```
  DATE       | AMOUNT  | BALANCE
  10/04/2014 | 500.00  | 1400.00
  02/04/2014 | -100.00 | 900.00
  01/04/2014 | 1000.00 | 1000.00
```

Rule 2 :Transactions must be displayed in ante-chronological order

Rule 3: It is possible to display them in chronological order

___
#### WITHDRAW ANOTHER ACCOUNT

**Acceptance criteria**    
**As a** Bank Client       
**I want** to deposit money on my account  
**So that** I can credit my account

Rule 1 : A deposit is always positif

___
#### BANK TRANSFER 

**Acceptance criteria**    
**As a** Bank Client       
**I want** be able to make a transfer from account to account
**So that** I can send money to someone else

Rule 1: A transfer must be positive
Rule 2: If my account is a blocked account, sotransfer operation is allowed only If I got enough money
___