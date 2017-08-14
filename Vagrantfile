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

    # Shared directories
    config.vm.synced_folder "config/", "/config/"

    # Create a forwarded port mapping which allows access to a specific port
    # within the machine from a port on the host machine.
    config.vm.network "forwarded_port", guest: 80, host: 80         # nginx
    config.vm.network "forwarded_port", guest: 9200, host: 9200     # elastic search
    config.vm.network "forwarded_port", guest: 27017, host: 27017   # mongodb

    # Enable provisioning with a shell script. Additional provisioners such as
    # Puppet, Chef, Ansible, Salt, and Docker are also available.
    config.vm.provision "shell", inline: <<-SHELL
        # install java 8
        add-apt-repository -y ppa:openjdk-r/ppa
        apt-get update
        apt-get install --yes openjdk-8-jdk

        # install elastic search
        ELASTIC_SEARCH=elasticsearch-5.5.1.deb
        wget -q https://artifacts.elastic.co/downloads/elasticsearch/$ELASTIC_SEARCH
        dpkg -i $ELASTIC_SEARCH
        cp -r /config/elasticsearch/* /etc/elasticsearch/
        update-rc.d elasticsearch defaults
        rm -f $ELASTIC_SEARCH

        # install utilities
        sudo apt-get install --yes httpie

        reboot
    SHELL
end
