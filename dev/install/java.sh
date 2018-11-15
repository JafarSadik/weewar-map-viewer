#!/bin/bash

# install java 8
if [ ! `which java` ]; then
    add-apt-repository -y ppa:openjdk-r/ppa
    apt-get update
    apt-get install --yes --force-yes openjdk-8-jdk
fi