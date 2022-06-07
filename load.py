#!/usr/bin/env python

import threading
import time
import os
import subprocess

def do_request():
    start = time.time()
    process = subprocess.Popen(["bash", "-c", "./post.bash"], stdout=subprocess.PIPE)
    output, error = process.communicate()
    end = time.time()
    print("%s, %s", output, (end - start))

def do_concurrent_request():
    threads = []
    for i in range(0, 50):
        t = threading.Thread(name='daemon', target=do_request)
        threads.append(t)
        t.start()
    for t in threads:
        t.join()

if __name__ == '__main__':
    for i in range(0, 99):
        print('>> batch ', i)
        do_concurrent_request()
