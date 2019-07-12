#!/usr/bin/python
# coding:utf-8
from urllib import request, parse


def loadPage(fullUrl, filename):
    """
        作用：根据url发送请求，获取服务器响应文件
        url: 需要爬取的url地址
        filename : 处理的文件名
    """
    print('正在下载' + filename)

    headers = {"User_Agent" : "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36"}

    # 构造请求对象
    request1 = request.Request(fullUrl, headers=headers)
    return request.urlopen(request1).read()


def wirtePage(html,filename):
    """
        作用:将html内容写入到本地
        html:服务器相应的文件内容
    """

    print('正在保存' + filename)

    # 文件写入
    with open(filename,'w', encoding='utf-8') as f:
        # 此时打印的html伪bytes格式的,f.write()参数需要字符串
        f.write(html.decode(encoding='utf-8'))

    print('-' * 30)


def tiebaSpider(url,beginPage, endPage):

    for page in range(beginPage, endPage+1):
        pn = (page-1) * 50
        filename = "第" + str(page) +'页.html'
        fullUrl = url + '&pn=' + str(pn)

        # 发起请求
        html = loadPage(fullUrl, filename)
        print(html)

        # 写网页
        wirtePage(html, filename)


if __name__ == '__main__':
    kw = input('请输入爬去的贴吧名:')
    beginPage = int(input('请输入起始页:'))
    endPage = int(input('请输入结束页'))

    url = 'http://tieba.baidu.com/f?'
    key = parse.urlencode({"kw": kw})
    fullUrl = url + key
    tiebaSpider(fullUrl, beginPage, endPage)
