# Vagrant automates setup of development environment in a virtual machine.
# https://docs.vagrantup.com
# https://vagrantcloud.com/search
Vagrant.configure("2") do |config|
    # Box configuration
    config.vm.box = "ubuntu/trusty64"
    config.vm.box_check_update = true

    config.vm.provider "virtualbox" do |vb|
        # Customize the amount of memory on the VM:
        vb.memory = "4000"
    end

    # Expose configuration for all services under /config
    config.vm.synced_folder "config/", "/config/"

    # Create a forwarded port mapping which allows access to a specific port
    # within the machine from a port on the host machine.
    config.vm.network "forwarded_port", guest: 8080, host: 80       # nginx
    config.vm.network "forwarded_port", guest: 9200, host: 9200     # elastic search
    config.vm.network "forwarded_port", guest: 27017, host: 27017   # mongodb

    # Enable provisioning with a shell script. Additional provisioners such as
    # Puppet, Chef, Ansible, Salt, and Docker are also available.
    config.vm.provision "shell", inline: <<-SHELL
        # install java 8
        add-apt-repository -y ppa:openjdk-r/ppa
        apt-get update
        apt-get install --yes openjdk-8-jdk

        # install elastic search, see https://www.elastic.co/downloads/elasticsearch
        ELASTIC_SEARCH=elasticsearch-5.5.1.deb
        wget -q https://artifacts.elastic.co/downloads/elasticsearch/$ELASTIC_SEARCH
        dpkg -i $ELASTIC_SEARCH
        cp -fr /config/elasticsearch/* /etc/elasticsearch/
        service elasticsearch start
        update-rc.d elasticsearch defaults
        rm -f $ELASTIC_SEARCH

        # install mongodb, see https://docs.mongodb.com/manual/tutorial/install-mongodb-on-ubuntu/
        apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 0C49F3730359A14518585931BC711F9BA15703C6
        echo "deb [ arch=amd64 ] http://repo.mongodb.org/apt/ubuntu trusty/mongodb-org/3.4 multiverse" | tee /etc/apt/sources.list.d/mongodb-org-3.4.list
        apt-get update
        apt-get install --yes mongodb-org
        cp -f /config/mongod.conf /etc/mongod.conf
        service mongod restart

        # install nginx, see https://www.nginx.com/resources/wiki/start/topics/tutorials/install/
        apt-get --yes install nginx
        cp -fr /config/nginx/* /etc/nginx
        service nginx reload

        # install utilities
        sudo apt-get install --yes httpie
    SHELL
end
