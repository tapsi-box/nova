#!/bin/bash
set -e

# Wait until MongoDB is ready to accept connections
until mongosh --eval "db.adminCommand('ping')" > /dev/null 2>&1; do
  echo "Waiting for MongoDB to start..."
  sleep 2
done

echo "Initiating replica set with host 'localhost:27017'..."
mongosh --eval "rs.initiate({_id: 'rs', members: [{ _id: 0, host: 'localhost:27017' }]})"

# Wait until the replica set becomes PRIMARY (state 1)
echo "Waiting for replica set to become PRIMARY..."
PRIMARY_STATE=""
until [ "$PRIMARY_STATE" = "1" ]; do
  sleep 2
  PRIMARY_STATE=$(mongosh --quiet --eval "rs.status().myState")
  echo "Current replica set state: $PRIMARY_STATE"
done

echo "Replica set is PRIMARY."
