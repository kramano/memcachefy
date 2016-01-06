An easy and seamless way of integrating web applications and/or services with distributed cache systems like [Memcached](http://memcached.org/) without the burden of refactoring or re-designing the actual code. It also provides an _on heap_ cache implementation in case you don't need a distributed cache system.


# Features #

  * Simple cache API designed for easy of use;
  * Distributed _off-heap_ support;
  * On-heap support with configurable time-to-live and max entries;
  * Non-native serialization support (Allow caching Java objects not implementing the Serializable interface);
  * Interceptor API for transparently enable caching for regular Java objects or EJBs;
  * Cache live statistics through Log4J;


# Start using #

Memcachefy is available in Maven Central under the identifier:

  * [com.googlecode.memcachefy:memcachefy-1.0.3](http://search.maven.org/#artifactdetails%7Ccom.googlecode.memcachefy%7Cmemcachefy%7C1.0.3%7Cjar)

You can download the JAR at:

  * [memcachefy-1.0.3.jar](http://search.maven.org/remotecontent?filepath=com/googlecode/memcachefy/memcachefy/1.0.3/memcachefy-1.0.3.jar)

Learn how to use it:

  * [Overview](http://code.google.com/p/memcachefy/wiki/Documentation)
  * [Configuration](http://code.google.com/p/memcachefy/wiki/Configuration)
  * [Examples](http://code.google.com/p/memcachefy/wiki/Examples)