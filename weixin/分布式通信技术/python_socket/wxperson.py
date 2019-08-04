import time
import itchat
import _thread
import  requests



class WxPerson:

    #微信需要登录
    def  login(self):

        print("初始化方法")
        #自动登录
        itchat.auto_login(hotReload=True)
        #永久运行
        itchat.run()

    #指定对象  name：微信名字
    def  get_users(self,name):
        print("查询微信好友的名字")
        users=itchat.search_friends(name=name)
        print(users)
        username=users[0]["UserName"]
        print(username)
        return  username

    #指定群
    def   get_room(self,roomname):
         _thread.start_new_thread(self.login, ())
         print("查询微信群的名字")
         roominfos=itchat.search_chatrooms(name=roomname)
         print(roominfos)
         print(roominfos[0]["UserName"])
         return roominfos[0]["UserName"]


    def  sendMsg(self,name,mess):
        print("发送消息.......")

        #发给好友
        #uname=self.get_users(name)
        #发个群
        room=self.get_room(name)

        #msg="卡路里（电影《西虹市首富》插曲）,http://zhangmenshiting.qianqian.com/data2/music/e60a4af66f8dc349d72418bba3a9f57f/612356199/6014273881560913261128.mp3?xcode=2d7098e557a50fdbb59adc403ebeee44"

        #itchat.send(str(weaterlist),toUserName=room)
        itchat.send(mess,toUserName=room)

        return  room+",哈哈谢谢您"
