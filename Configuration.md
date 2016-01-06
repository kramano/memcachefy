Basic configuration

# Maven #

Maven users can add the following dependency to the **pom.xml** file:

```
<dependency>
  <groupId>com.googlecode.memcachefy</groupId>
  <artifactId>memcachefy</artifactId>
  <version>1.0.3</version>
</dependency>
```

# Cache interceptor bootstrapping #

By default, it will look for a configuration file named "memcachefy.properties" in the classpath.

Example:
```
## file: memcachefy.properties

cache.entry.ttl=60
cache.type=MEMCACHED
#cache.initialMaxEntries=5000 ## may be specified if cache.type == ONHEAP
memcached.hosts=host1\:11211, host2\:11211 
memcached.transcoder=NONE

```

You can also create the properties file using XML:

```
<!--file: memcachefy-properties.xml-->
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE properties SYSTEM "http://java.sun.com/dtd/properties.dtd">
<properties>
<entry key="cache.entry.ttl">60</entry>
    <entry key="cache.type">MEMCACHED</entry>
    <!--<entry key="cache.initialMaxEntries">5000</entry>-->
    <entry key=" memcached.hosts">localhost\:11211</entry>
    <entry key=" memcached.transcoder">NONE</entry>
</properties>
```

When using the [CacheProxy](http://memcachefy.googlecode.com/svn/trunk/javadoc/com/googlecode/memcachefy/CacheProxy.html) you can supply new instances of the proxy with custom cache objects:

```
    CacheManager cacheManager = CacheManagerBuilder.newBuilder()....build();

    Cache<String, Object> cache = cacheManager.getCache("test");
    Action action = (Action) CacheProxy.newInstance(new MyAction(), cache);

```

# Spring integration #

You may want to use the `FactoryBean` interface for creating instances of [Cache](http://memcachefy.googlecode.com/svn/trunk/javadoc/com/googlecode/memcachefy/Cache.html) for integration with Spring:

```
public class CacheBean implements FactoryBean<Cache> {

    private String hosts;

    public Cache getObject() throws Exception {
        
        return CacheManagerBuilder.newBuilder().
				setCacheType(CacheType.MEMCACHED).
				setMemcachedHosts(hosts).
				setDefaultTtl(120).build().getCache("cache");
    }

    public Class<? extends Cache> getObjectType() {
        return Cache.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void setHosts(String hosts) {
        this.hosts = hosts;
    }
}

```

Then add the bean definition into the spring context configuration file:

```
    <beans ...> 
        <bean name="cacheBean" class="CacheBean">
            <property name="port" value="11211"/>
            <property name="host" value="localhost"/>
        </bean>
    </beans>
```