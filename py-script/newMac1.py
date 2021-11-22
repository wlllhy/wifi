#coding=utf-8
import random
import threading
from time import ctime,sleep
import json
import time

def random_mac():
    macList = []
    for i in range(1, 7):
        randStr = "".join(random.sample("0123456789abcdef",2))
        macList.append(randStr)
    randMac = ":".join(macList)
    return randMac

if __name__ == '__main__':
    f = open("out2.txt", "w",encoding='utf-8')
    i =2018211100
    j = 1
    for o in range(1,37):
        # print("%s"%("张三"+str(i)))
        f.write("%d,%s,%s,%d\n" % (i,random_mac(),("张三"+str(j)),1))
        i+=1
        j+=1
        f.write("%d,%s,%s,%d\n" % (i,random_mac(),("张三"+str(j)),2))
        i+=1
        j += 1
        f.write("%d,%s,%s,%d\n" % (i,random_mac(),("张三"+str(j)),3))
        i+=1
        j += 1
    f.close()
