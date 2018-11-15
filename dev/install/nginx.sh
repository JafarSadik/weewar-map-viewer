#!/bin/bash

function update_configuration() {
    cp -fr /box_config/etc/nginx/* /etc/nginx
    service nginx reload
}

# install nginx, see https://www.nginx.com/resources/wiki/start/topics/tutorials/install/
if [ ! -d "/etc/nginx" ]; then
    echo "Installing nginx service"
    apt-get --yes --force-yes install nginx
    update_configuration
else
    echo "Updating nginx configuration"
    update_configuration
fi

