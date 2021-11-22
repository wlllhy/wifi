#coding=utf-8
import random
import threading
from time import ctime,sleep
import json
import requests
import time
from random import choice
def random_mac():
    macList = []
    for i in range(1, 7):
        randStr = "".join(random.sample("0123456789abcdef",2))
        macList.append(randStr)
    randMac = ":".join(macList)
    return randMac

def random_rssi():
    return str(random.randrange(-100, 0))

def random_range():
    return str(round(random.uniform(0, 20), 1))

def random_id():
    return str(random.randrange(1, 1000))

probeList = []

foo = [
    "03:f9:df:5f:e6:f5",
"06:10:54:d0:be:f1",
"06:51:85:b7:dc:0d",
"06:f3:07:79:fd:db",
"09:45:70:e9:52:6e",
"09:b7:5a:db:43:72",
"0c:e2:59:62:70:e1",
"0d:76:91:81:0d:c9",
"10:fb:ae:53:87:e0",
"12:97:dc:82:57:d4",
"12:d9:cd:d3:c9:f9",
"12:e0:82:8c:e2:70",
"13:ce:2b:37:13:ed",
"15:69:79:fc:ce:64",
"19:5c:3b:5a:6c:d4",
"19:79:a3:b4:36:3a",
"1b:8a:84:d6:e3:4a",
"1d:1f:a8:27:ab:21",
"1d:a1:e8:84:b0:bf",
"1e:27:a9:8b:51:a1",
"20:0f:80:74:f4:2b",
"20:d2:9a:d8:bc:d0",
"27:16:2d:28:08:1f",
"29:68:90:0a:16:7d",
"2e:82:47:57:af:a5",
"30:b5:54:ac:0e:da",
"32:01:86:04:9d:7d",
"32:a1:0a:bf:3b:fc",
"32:c4:ed:1e:2d:20",
"35:b9:ec:6b:1b:65",
"37:8c:4b:be:e5:40",
"3b:96:c3:0c:b5:fa",
"41:48:5a:e5:4a:6b",
"45:3e:b6:2b:e4:c2",
"45:7e:19:32:42:75",
"45:92:b3:2d:ab:32",
"46:42:8c:c0:d4:42",
"46:93:98:06:e0:38",
"4b:03:ef:2b:6e:6a",
"4b:ce:80:10:29:c4",
"4c:02:f7:87:dc:80",
"4d:cb:67:58:37:a9",
"4f:d1:e9:f5:d3:f7",
"51:84:79:f7:1e:19",
"54:89:da:29:73:18",
"57:f3:7d:63:0b:de",
"5c:db:fe:fd:ab:f1",
"5d:7c:dc:c3:b9:d6",
"5f:1a:31:ac:be:d6",
"67:d5:de:76:2e:68",
"68:eb:0e:7d:45:3d",
"6a:95:12:e7:50:76",
"6e:30:5c:17:ac:83",
"6e:c2:e4:10:c8:c7",
"72:12:d4:6d:02:0c",
"78:1a:5b:8f:de:80",
"79:75:93:89:51:b2",
"79:b5:2f:ce:3f:a2",
"7b:51:ad:8f:36:68",
"7c:e1:fa:a4:fc:db",
"7c:ea:63:a1:d8:6b",
"7d:f2:89:4d:bf:b5",
"82:d5:1a:f3:95:bc",
"86:b5:df:09:fa:50",
"87:3f:a6:52:18:b5",
"87:5b:2f:8a:e8:30",
"8a:83:b8:9b:ad:e2",
"90:7c:2c:61:f4:cf",
"95:1c:c8:d5:cd:cd",
"9a:c6:fc:1c:80:dc",
"9c:e1:e5:e8:3c:31",
"a0:4c:3b:82:8d:5c",
"a1:e3:5c:98:e5:38",
"a4:60:ab:47:de:b8",
"a4:f3:6f:1f:8d:5d",
"a6:c5:04:4e:f9:7b",
"a8:e0:e5:b8:d3:d0",
"a9:fd:e8:20:5e:a6",
"ab:61:26:8e:d8:e2",
"b1:e5:18:60:cf:b2",
"b2:3d:52:6e:4d:6a",
"b3:5d:d6:13:cd:a4",
"b3:65:db:c2:19:c2",
"b4:a5:b9:6d:92:12",
"b5:9c:3d:2e:e4:c0",
"c0:2b:f8:17:83:8b",
"c0:74:3a:ea:52:92",
"c8:e3:a1:3b:85:e0",
"cd:d0:21:d0:3b:17",
"ce:b2:f7:cf:65:15",
"d1:c9:5a:67:a8:f5",
"d6:f2:e2:49:fb:12",
"e0:86:d1:a8:6c:37",
"e1:5d:fb:ce:3b:7b",
"e2:05:91:31:f2:bc",
"e2:e4:31:9f:6f:f6",
"e8:a5:7c:09:6b:38",
"e8:f4:76:25:2a:fd",
"ea:c8:a4:85:29:24",
"ec:45:8e:39:f6:97",
"ec:7c:2a:30:f4:31",
"ec:b0:36:2a:da:01",
"ed:4c:a3:f6:e8:87",
"ef:e7:28:39:86:19",
"f1:ac:ea:a0:67:be",
"f2:8f:9e:72:53:36",
"f6:4a:91:1f:7c:0e",
"f9:93:30:a0:b3:6f"
]

# 模拟探针发包
def send_random_json():
    headers = {'Content-Type': 'application/json'}
    probe = {"id": ''+random_id(), "mmac": random_mac(), "rate": "3", "wssid": "test", "wmac": random_mac(), "time": time.strftime('%a %b %e %H:%M:%S %Y', time.localtime(time.time()))}
    mac_DataMul = []
    for i in range(random.randrange(15, 30)):
        mac_DataMul.append({"mac": choice(foo), "rssi": random_rssi(), "range": random_range()})
    probe['data'] = mac_DataMul
    probe = json.dumps(probe)
    print(probe)
    request = requests.post(url='http://localhost:8000/upload.action', headers=headers, data=probe)
    print("response code:", request.status_code)

# 模拟多线程发包
def send_rand_json_with_multi_thread():
    for i in range(10):
        probe = {"id": i, "mmac": random_mac(), "rate": 3, "wssid": "test", "wmac": random_mac()}
        probes = json.dumps(probe)
        probeList.append(probes)

    for i in range(10):
        t = threading.Thread(target=send_random_json)
        threads.append(t)

    for i in range(10):
        threads[i].setDaemon(True)
        threads[i].start()

if __name__ == '__main__':
    threads = []
    probeList = []
    index=0
    for i in range(1000):
        send_random_json()
        sleep(1)
    print("all over %s" %ctime())