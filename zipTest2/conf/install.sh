            #!/bin/bash
            if [ -z ${'$'}JAVA_HOME ];then
                echo "I require ${'$'}JAVA_HOME variable setting but it's not exists.  Aborting."
                exit 1
            fi
           # curl -o /tmp/logCoat.zip -L 192.168.43.234:8081/api/download
           curl -o /tmp/logCoat.zip -L 10.241.150.159:8081/api/download
            mkdir -p /usr/local/opt/logCoat
            cd /usr/local/opt/logCoat
            #${'$'}JAVA_HOME/bin/jar xvf /tmp/logCoat.zip
            sudo tar -zxvf /tmp/logCoat.zip
            rm -rf /tmp/logCoat.zip
            chmod 755 /usr/local/opt/logCoat/apache-flume-1.8.0-bin/bin/startup.sh
            chmod 755 /usr/local/opt/logCoat/apache-flume-1.8.0-bin/bin/flume-ng
            /usr/local/opt/logCoat/apache-flume-1.8.0-bin/bin/startup.sh
