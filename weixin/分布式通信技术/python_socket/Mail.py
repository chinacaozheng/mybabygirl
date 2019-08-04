import yagmail
#创建对象

class Sendmail:
    def sendmail(self,id,content):
        gmail=yagmail.SMTP(user="2219644911@qq.com",password="egwqetcvmisuebcj",host="smtp.qq.com")
        print(gmail)
        #构建文本内容
        #发送发邮件
        gmail.send(id,content)