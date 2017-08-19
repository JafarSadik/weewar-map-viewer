#!/bin/bash

function update_configuration() {
    cp -f /box_config/etc/mongod.conf /etc/mongod.conf
    service mongod restart
}

# install mongodb, see https://docs.mongodb.com/manual/tutorial/install-mongodb-on-ubuntu/
if [ ! -f "/etc/mongod.conf" ]; then
    echo "Installing mongodb service"
    apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 0C49F3730359A14518585931BC711F9BA15703C6
    echo "deb [ arch=amd64 ] http://repo.mongodb.org/apt/ubuntu trusty/mongodb-org/3.4 multiverse" | tee /etc/apt/sources.list.d/mongodb-org-3.4.list
    apt-get update
    apt-get install --yes --force-yes mongodb-org
    update_configuration
else
    echo "Updating mongodb configuration"
    update_configuration
fi