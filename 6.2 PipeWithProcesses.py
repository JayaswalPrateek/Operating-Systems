# Program to write and read two messages through the pipe using the parent and the child processes.
import multiprocessing as mp


def child_process(pipe):
    message1, message2 = pipe.recv(), pipe.recv()
    print("Child Process Reads: " + message1.decode())
    print("Child Process Reads: " + message2.decode())


pipe_recv, pipe_send = mp.Pipe()

pid = mp.Process(target=child_process, args=(pipe_recv,))
pid.start()

print("Parent Process Writes: " + "Hi")
pipe_send.send(b"Hi")
print("Parent Process Writes: " + "Hello")
pipe_send.send(b"Hello")

pid.join()
