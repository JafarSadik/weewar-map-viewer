#!/bin/bash

# install nginx, see https://www.nginx.com/resources/wiki/start/topics/tutorials/install/
apt-get --yes install nginx
cp -fr /box_config/etc/nginx/* /etc/nginx
service nginx reload