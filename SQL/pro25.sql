-- pro25 Æ®·£Àè¼Ç ½Ç½À
create table cust_account (
    accountNo varchar2(50) primary key,
    custName varchar2(50),
    balance number(20, 4)
    );
    
    insert into cust_account(accountNo, custName, balance)
    values('70-490-930','È«±æµ¿',10000000);
    
    insert into cust_account(accountNo, custName, balance)
    values('70-490-911','±èÀ¯½Å',10000000);
    
    select * from cust_account;
    
    commit;