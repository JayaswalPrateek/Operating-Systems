import threading
import time

NUM_OF_ITEMS = 20
BUFFER_LIMIT = 10
buffer = []
mutex = threading.Lock()
can_produce = threading.Condition(lock=mutex)
can_consume = threading.Condition(lock=mutex)


def producer():
    for i in range(NUM_OF_ITEMS):
        with can_produce:
            while len(buffer) == BUFFER_LIMIT:
                can_produce.wait()
            buffer.append(i)
            print("Producer produced: ", i)
            can_consume.notify()
        time.sleep(1)


def consumer():
    for i in range(NUM_OF_ITEMS):
        with can_consume:
            while len(buffer) == 0:
                can_consume.wait()
            item = buffer.pop(0)
            print("Consumer consumed: ", item)
            can_produce.notify()
        time.sleep(2)


prod_thread = threading.Thread(target=producer)
cons_thread = threading.Thread(target=consumer)
prod_thread.start()
cons_thread.start()
prod_thread.join()
cons_thread.join()
