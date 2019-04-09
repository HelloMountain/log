            #!/bin/bash
            if [ -z ${'$'}JAVA_HOME ];then
                echo "I require ${'$'}JAVA_HOME variable setting but it's not exists.  Aborting."
                exit 1
            fi
            curl -o /tmp/logCoat.zip -L '$downloadUrl$uuid/file'
            mkdir -p /usr/local/opt/logCoat
            cd /usr/local/opt/logCoat
            ${'$'}JAVA_HOME/bin/jar xvf /tmp/logCoat.zip
            rm -rf /tmp/logCoat.zip
            chmod 755 /usr/local/opt/logCoat/bin/startup.sh
            chmod 755 /usr/local/opt/logCoat/bin/flume-ng
            /usr/local/opt/logCoat/bin/startup.sh