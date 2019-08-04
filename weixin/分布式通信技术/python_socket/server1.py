import  socket

from python_socket.Mail import Sendmail
from python_socket.wxperson import WxPerson
import  _thread
import time
import json

try:
    #创建python socket对象  socket.AF_INET 网络
    socket=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
    print("创建套接字对象成功")
    socket.bind(("127.0.0.1",9998))
    print("绑定套接字对象成功")

    #tcp数量5个，最少1个
    socket.listen(5)


except:
    print("创建套接字失败!")


while  True:
    print("监听客户端的请求")
    conn, addr=socket.accept()
    print("服务器和客户机连接成功")
    print(addr,conn)

    #接受客户机的消息
    buffer=conn.recv(1024)
    msg=str(buffer,"utf-8")
    print("接受客户机的消息为：",msg)

    strs = msg.split(",")
    name = strs[1]

    mess = strs[2]

    print(name)

    print(mess)

    smail = Sendmail()
    smail.sendmail(name,mess)