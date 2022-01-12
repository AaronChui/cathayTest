create table currency_info
(
  code varchar(10) NOT NULL PRIMARY KEY,
  ch_name varchar(50),
  eng_name varchar(50),
  symbol varchar(5),
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);


insert into currency_info ( code,ch_name,eng_name,symbol,rate,update_time )
  values( 'USD' ,'美元','United States Dollar','&#36;',update_time );