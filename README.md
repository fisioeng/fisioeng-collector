# Fisioeng Collector
#### Software on-line de análise e processamento dos dados oriundos de sensores de frequência cardíaca e temperatura superficial para otimização da agropecuária


### Instalation

##### Windows

The application has dependency with rxtx library, and it should be installed in the system.
For this, clone the repository and go to libs/ and:

 * copy RXTXcomm.jar to [JAVA_HOME]\jre\lib\ext
 * copy rxtxSerial.dll to [JAVA_HOME]\jre\bin
 * copy rxtxParallel.dll to [JAVA_HOME]\jre\bin

Where <JAVA_HOME> is the path to java instalation. In windows, maybe is "C:\Program Files\Java\jdk[YOUR JAVA VERSION]"

##### Linux (Debian dists)
See your architetura and put in her files.

 * copy RXTXcomm.jar ---> [JAVA_HOME]/jre/lib/ext
 * copy librxtxSerial.so ---> [JAVA_HOME]/jre/lib/i386/
 * copy librxtxParallel.so ---> [JAVA_HOME]/jre/lib/i386/

NOTE: For a JDK installation on architecture=x86_64, just change the
i386 to x86_64 above.

To emulate ttyUSB use a emulation application, as socat.

```
sudo apt-get install socat
```

And emulate a ttyUSB:

```
socat -d -d pty,raw,echo=0,link=/dev/ttyUSB1 pty,raw,echo=0,link=/dev/ttyUSB1
```
[Se more about socat](http://www.dest-unreach.org/socat/doc/socat.html)

