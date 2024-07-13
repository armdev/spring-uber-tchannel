FROM registry.gitlab.com/armdev/dockers/eclipse-temurin:22
COPY target/tchannel-client.jar /opt/tchannel-client.jar
RUN useradd -ms /bin/sh notSec2075 \
    && echo "root:Docker!" | chpasswd \
    && chown -R notSec2075 /opt/
USER notSec2075
ENTRYPOINT ["java", "--add-opens", "java.base/java.time.zone=ALL-UNNAMED", "--add-opens", "java.base/java.time=ALL-UNNAMED", \
            "--add-opens", "java.base/java.math=ALL-UNNAMED", "--add-opens", "java.base/java.lang=ALL-UNNAMED", \
            "--add-opens", "java.management/sun.management=ALL-UNNAMED", \
            "--add-opens", "java.base/java.time.LocalDateTime=ALL-UNNAMED", \
            "-XX:+UseZGC", "-XX:+ZGenerational", "-Xss2m", "-XX:MinHeapSize=512M", "-XX:InitialHeapSize=512M", "-XX:MaxHeapSize=2G", \
            "-XX:TieredStopAtLevel=1", "-XX:CICompilerCount=1", "-Xshare:on", \
            "-XX:ParallelGCThreads=100", "-XX:ConcGCThreads=30", "--enable-preview", \
            "-XX:SoftRefLRUPolicyMSPerMB=0","-XX:ZCollectionInterval=30", "-XX:ZFragmentationLimit=10", \
            "-XX:MaxGCPauseMillis=50", "-XX:+UseCompressedClassPointers", "-XX:-UseCompressedOops", "-XX:ZUncommitDelay=30", \
            "-XX:InitiatingHeapOccupancyPercent=35", "-XX:G1HeapRegionSize=16M", \
            "-XX:MinMetaspaceFreeRatio=50", "-XX:MaxMetaspaceFreeRatio=100", \  
            "-XX:InitialCodeCacheSize=8m", \    
            "-XX:+HeapDumpOnOutOfMemoryError", "-Dsun.net.inetaddr.ttl=60", "-Dsun.net.client.defaultConnectTimeout=10000", "-Dsun.net.client.defaultReadTimeout=30000", \
            "-XX:+UnlockExperimentalVMOptions", "-XshowSettings:vm" ,"-jar","/opt/tchannel-client.jar"]
EXPOSE 2019 2029 8888


