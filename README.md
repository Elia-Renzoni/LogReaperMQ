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

The queues must be of the valid type. These are INFO, WARN, DEBUG, FATAL, ERROR and TRACE. If the subscribers want to create or add new logs to a queue that are not of the intended type, then they will receive an error. <br>

The classes that handle queues are QueuesManager and QueueEnvironment, the last one contains a single queue, while the first one contains a set of queues that manage. <br>
The QueueEnvironment class contains a queue that is identified by its name, the queue also has additional information such as:
* dirty bit -> which is a boolean indicating if the queue is too full or not.
* dirty bit for call back methods -> if subcribers register to a queue, set the dirty bit to true, otherwise false. This will help research operations.
* List of registered subscribers -> when a subscriber wants to register, it is added to the list of subscribers.
Obviously these attributes are followed by methods to manipulate them. <br>

The QueuesManager class is responsible for keeping a list of queues to manage, in fact it is responsible for adding and removing queues. <br> <br>

Subscribers to register must contact the correct enpoint and pass as body of the request the host and port. If a subcriber wants to register, he must pass a list of topics and queues to which his call back method should be attached, the goal is to search in the hash map for topics and then the subsequent queues to insert the subscriber’s name in the list of subscribers explained above, obviously changing the flag indicating the recording of the call back methods. <br> LogReaperMQ uses a method other than polling to avoid receiving too many messages that would render the system unusable.
## Publisher Guide

## Subscriber Guide

## Metrics and Performaces
