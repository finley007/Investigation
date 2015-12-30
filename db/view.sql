creat or replace view v_stock_action as 
select ms.TRANSACTION_ID, ma.QUANTITY, ma.PRIZE, ma.TIME, ma.ACTION_TYPE, ms.STOCK_CODE, ms.PROFIT, ms.PROFIT_RATE 
from my_stock ms, my_action ma 
where ma.TRANSACTION_ID = ms.TRANSACTION_ID and ms.status = 0 
order by ms.TRANSACTION_ID, ma.ACTION_TYPE desc; 