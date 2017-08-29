from threading import Thread
import os
import urllib2

MAX_MAP_ID = 500000
WORKER_THREADS = 50

# -------------------------------------------
# Script downloads all available weewar maps 
# -------------------------------------------
def main():
    # create directory for maps
    if not os.path.exists('maps'):
        os.makedirs('maps')

    # download maps in separate threads
    for thread_no in range(0, WORKER_THREADS):
        Thread(target=worker, args=(thread_no, )).start()


def worker(thread_no):
    idx = 0
    while True:
        current_map_id = idx * WORKER_THREADS + thread_no
        idx += 1
        if current_map_id <= MAX_MAP_ID:
            download_map(current_map_id)
        else:
            break


def download_map(map_id):
    weewar_map = read_url(weewar_map_url(map_id))
    if weewar_map is not None:
        map_file = open('maps/%d.txt' % map_id, mode='w')
        map_file.write(weewar_map)
        map_file.close()
        print("saved maps/%d.txt" % map_id)


def read_url(url):
    conn = None
    try:
        conn = urllib2.urlopen(url)
        return conn.read()
    except:
        return None
    finally:
        if conn is not None:
            conn.close()


def weewar_map_url(map_id):
    return 'http://weewar.com/api1/map/' + str(map_id)


if __name__ == '__main__':
    main()