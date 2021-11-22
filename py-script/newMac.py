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
    f = open("out.txt", "w")
    for i in range(1,37):
        f.write("%d,%s\n" % (1, random_mac()))
        f.write("%d,%s\n" % (2, random_mac()))
        f.write("%d,%s\n" % (3, random_mac()))
    f.close()
