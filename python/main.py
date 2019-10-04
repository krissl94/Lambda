import json
from faker import Faker
fake = Faker()
import sys

def lambda_handler(event, context):
    print("Hello from lambda")

    return {
        'statusCode': 200,
        'body': json.dumps('Hello there, random user ' + fake.name())
    }

print("Hello from lambda")
sys.stdout.write('Hello there, random user ' + fake.name())
sys.stdout.flush()
