# LogReaperMQ
I created LogReaperMQ for educational purposes, i wanted to know more about the world of Distributed Systems, so i decided to make a message queue broker in Java. <br>
At the beginning i did not know what it would be good for me to create a queue broker, so i started looking for problems that a queue broker could solve, and i came across Microservice Architecture, witch i did know before, and the Log Aggregation problem. One way to solve it is to convey all the logs into a unique queue, in this way we can have an overll idea of the system. <br>
As i said before LogReaperMQ was born only for educational purposes, and is not a production-ready product. In facts the main weakness is the cluster-less structure without a Leader Election algorithm to provide FIFO total order broadcast for replication.<br>
The name LogReaperMQ is inspired by Robert Whittaker's Nikname, which is 'The Reaper'.
## LogReaperMQ Internal
<img width="704" alt="internal" src="https://github.com/user-attachments/assets/7145916f-d88f-4084-988d-5aaa699881bc"> <br>
The internal structure of the system is widely inspired by RabbitMQ one. I read RabbitMQ free documentation for understand how a message queue broker is internally built. <br>
LogReaperMQ use a lot of Hash Maps and Hash Sets to provide O(1) operations. The Data Structure that manage and store the queues is a classic Hash Map, which can be imagined as follows: <br>
<img width="403" alt="hash" src="https://github.com/user-attachments/assets/3d723c8e-fc0b-4b32-964f-58920d2c2194"> <br>
The keys consist of the topics name, which are the name of microservices that want to store their logs. The values consist of an instance of the class that manage all the queue referred to a topic. Using this Hash Map we can search, add and remove queues by looking to the topic name. <br> <br>

The queues must be of the valid type. These are INFO, WARN, DEBUG, FATAL, ERROR and TRACE. If the subscribers want to create or add new logs to a queue that are not of the intended type, then they will receive an error.



## Publisher Guide

## Subscriber Guide
