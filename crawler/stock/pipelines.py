# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: http://doc.scrapy.org/en/latest/topics/item-pipeline.html
import MySQLdb

class StockPipeline(object):
    def process_item(self, item, spider):
        insert_section = "INSERT INTO COMMON_SECTION(CODE, LABEL) \
	       VALUES ('%s', '%s')" % \
	       (item['section_code'], 
	       item['section_label'])
    	self.updateDB(insert_section)
    	insert_stock = "INSERT INTO COMMON_STOCK(CODE, NAME, LABEL) \
	       VALUES ('%s', '%s', '%s')" % \
	       (item['stock_code'], 
	       item['stock_name'],
	       item['stock_label'])
    	self.updateDB(insert_stock)
    	insert_stock_section = "INSERT INTO COMMON_STOCK_SECTION(STOCK_CODE, SECTION_CODE) \
	       VALUES ('%s', '%s')" % \
	       (item['stock_code'], 
	       item['section_code'])
    	self.updateDB(insert_stock_section)
        return item


    def open_spider(self, spider):
        self.db = MySQLdb.connect("localhost", "root", "root", "stock", charset='utf8')
        self.cursor = self.db.cursor()
        self.updateDB("insert into common_stock_bak (bak_id, code, name, label) select unix_timestamp(now()), code, name, label from common_stock");
        self.updateDB("delete from common_stock_section");
        self.updateDB("delete from common_stock");
        self.updateDB("delete from common_section");

    def close_spider(self, spider):
        self.db.close()

    def updateDB(self, sql):
    	try:
        	self.cursor.execute(sql)
        	self.db.commit()
    	except MySQLdb.Error, e:
        	print e
        	self.db.rollback()
