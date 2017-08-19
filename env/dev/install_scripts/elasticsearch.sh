#!/bin/bash

function update_configuration() {
    cp -fr /box_config/etc/elasticsearch/* /etc/elasticsearch/
    service elasticsearch restart
}

# install elastic search, see https://www.elastic.co/downloads/elasticsearch
if [ ! -d "/etc/elasticsearch" ]; then
    echo "Installing elasticsearch service"
    wget -q https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-5.5.1.deb
    dpkg -i elasticsearch-5.5.1.deb
    update-rc.d elasticsearch defaults
    rm -f elasticsearch-5.5.1.deb
    update_configuration
else
    echo "Updating elasticsearch configuration"
    update_configuration
fi