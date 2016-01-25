import pymysql
 
try:
    conn=pymysql.connect(host='localhost',user='root',passwd='',port=3306,db='stock')
    output=open("./output.txt", 'w+')
    cur=conn.cursor()
    cur.execute('select v.TRANSACTION_ID, v.STOCK_CODE, avg(v.PROFIT) from v_stock_action v group by v.TRANSACTION_ID, v.STOCK_CODE')

    allTrans=cur.fetchall()
    for trans in allTrans:
        # print ('Current transaction id:' + trans[0] + ' and stock code:' + trans[1], file=output)
		# Caculate buy action
        cur.execute('select * from v_stock_action v where v.TRANSACTION_ID = %s and v.action_type = 1', trans[0])
        results=cur.fetchall()
        totalPrice=0
        for r in results:
            totalPrice += r[1] * r[2]
		# Caculate sell action
        cur.execute('select * from v_stock_action v where v.TRANSACTION_ID = %s and v.action_type = 0', trans[0])
        results=cur.fetchall()
        for r in results:
            totalPrice -= r[1] * r[2]
        totalPrice = -totalPrice
        if trans[2] != totalPrice:
            print ('Problem transaction:' + trans[0] + ' infact profit:' + str(totalPrice) + ' and current profit:' + str(trans[2]), file=output) 
    conn.commit()
    cur.close()
    conn.close()
 
except pymysql.Error as e:
     print ("Mysql Error %d: %s" % (e.args[0], e.args[1]))