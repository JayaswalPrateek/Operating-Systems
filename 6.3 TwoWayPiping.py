# Achieving two-way communication using pipes.

from multiprocessing import Process, Pipe


def child(pipe1, pipe2):
    message = pipe2.recv()
    print("Child Reading from pipe 1: " + message)
    message = "Hello"
    print("Child Writing to pipe 2: " + message)
    pipe2.send(message)


pipe1, pipe2 = Pipe()
p = Process(target=child, args=(pipe1, pipe2))
p.start()

message = "Hi"
print("Parent Writing to pipe 1: " + message)
pipe1.send(message)

message = pipe1.recv()
print("Parent Reading from pipe 2: " + message)

p.join()
