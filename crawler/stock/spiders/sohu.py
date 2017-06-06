# -*- coding: utf-8 -*-
import scrapy
import logging
import scrapy
import re
import sys
sys.path.append(r'/Users/finley/Finley/workspace/java/Investigation/Investigation/crawler/stock/lib/pinyin/')
from pinyin import PinYin
from scrapy import Selector
from scrapy import Request
from stock.items import StockItem

logger = logging.getLogger('sohu')

class SohuSpider(scrapy.Spider):
    name = "sohu"
    allowed_domains = ["q.stock.sohu.com"]
    start_urls = ['http://q.stock.sohu.com/cn/bk.shtml']
    base_url = 'http://q.stock.sohu.com/cn/';

    def parse(self, response):
        # collect `item_urls`
        logger.info('Start to update all stocks')
        items = response.xpath("//*[@id='BIZ_MS_pllist']/tbody/tr").extract()
        for item in items:
            sl = Selector(text = item)
            item_url = sl.xpath("//td[@class='e2']/a/@href").extract()[0]
            item_name = sl.xpath("//td[@class='e2']/a/text()").extract()[0].encode('utf-8')
            logger.info('Section-----------------------------------------------')
            logger.info('Section name: ' + item_name)
            logger.info('Section url: ' + self.add_base_url(item_url))
            yield Request(url=self.add_base_url(item_url), callback=self.parse_item)


    def add_base_url(self, section):
        return self.base_url + section

    def parse_item(self, response):
        items = response.xpath("//*[@id='BIZ_MS_plstock']/tbody/tr").extract()
        for item in items:
            stock = StockItem()
            section_name = response.xpath("//*[@id='BIZ_MS_board_caption']/text()").extract()[0].encode('utf-8')
            stock['section_label'] = section_name   
            stock['section_code'] = self.get_pinyin(section_name)
            sl = Selector(text = item)
            stock_code = sl.xpath("//td[@class='e1']/text()").extract()[0]
            stock_label = sl.xpath("//td[@class='e2']/a/text()").extract()[0]
            stock['stock_code'] = stock_code
            stock['stock_label'] = stock_label
            stock['stock_name'] = self.get_pinyin(stock_label)
            yield stock

    def get_pinyin(self, hanzi):
        py = PinYin();
        py.load_word("/Users/finley/Finley/workspace/java/Investigation/Investigation/crawler/stock/lib/pinyin//word.data")
        item_code = ''
        try:
            item_code = py.hanzi2pinyin_split(string=hanzi, split="", firstcode=True)
        except Exception,e:
            logger.info('Get pinyin error' + hanzi)
        return item_code