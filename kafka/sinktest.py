from kafka import KafkaProducer
import json
from json import dumps

p = KafkaProducer(bootstrap_servers = ['localhost:9092'], value_serializer = lambda x:dumps(x).encode('utf-8'))

print("hello")
print(p)

data = {'name': 'hakan'}
p.send('patika.logs', value = data)

p.flush()
