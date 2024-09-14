# LogReaperMQ
I created LogReaperMQ for educational purposes, i wanted to know more about the world of Distributed Systems, so i decided to make a message queue broker in Java. <br>
At the beginning i did not know what it would be good for me to create a queue broker, so i started looking for problems that a queue broker could solve, and i came across Microservice Architecture, witch i did know before, and the Log Aggregation problem. One way to solve it is to convey all the logs into a unique queue, so we can have an overll idea of the system. <br>
As i said before LogReaperMQ was born only for educational purposes, and is not a production-ready product. In facts the main weakness is the cluster-less structure without a Leader Election algorithm to provide FIFO total order broadcast for replication.
the name LogReaperMQ is inspired by Robert Whittaker's Nikname, which is 'The Reaper'.
## LogReamperMQ Internal
<img width="704" alt="internal" src="https://github.com/user-attachments/assets/7145916f-d88f-4084-988d-5aaa699881bc">

## Publisher Guide

## Subscriber Guide
