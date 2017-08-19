#!/bin/bash

# install elastic search, see https://www.elastic.co/downloads/elasticsearch
wget -q https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-5.5.1.deb
dpkg -i elasticsearch-5.5.1.deb
cp -fr /box_config/etc/elasticsearch/* /etc/elasticsearch/
service elasticsearch start
update-rc.d elasticsearch defaults
rm -f elasticsearch-5.5.1.deb