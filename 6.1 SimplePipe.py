# Program to write and read two messages using pipe.
import os

pipe = os.pipe()
messageSizeLimit = 20
readingLocation = pipe[0]
writingLocation = pipe[1]

print("Writing to pipe: Hi")
os.write(writingLocation, "Hi".encode())
print("Reading from pipe: " + os.read(readingLocation, messageSizeLimit).decode())

print("Writing to pipe: Hello")  # Corrected
os.write(writingLocation, "Hello".encode())
print("Reading from pipe: " + os.read(readingLocation, messageSizeLimit).decode())
