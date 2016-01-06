Overview

# What is it? #

Memcachefy is built on top of the excellent APIs [Google concurrentlinkedhashmap](http://code.google.com/p/concurrentlinkedhashmap/) and [Spymemcached client](http://code.google.com/p/spymemcached/). It also can be extended for using with other cache systems like [Redis](http://redis.io/) or [Google Guava](http://code.google.com/p/guava-libraries/) ([MapMaker](http://guava-libraries.googlecode.com/svn/trunk/javadoc/com/google/common/collect/MapMaker.html), [CacheBuilder](http://guava-libraries.googlecode.com/svn/trunk/javadoc/com/google/common/cache/CacheBuilder.html)). It firstly introduces a set of common interfaces ([Cache](http://memcachefy.googlecode.com/svn/trunk/javadoc/com/googlecode/memcachefy/Cache.html), [CacheManager](http://memcachefy.googlecode.com/svn/trunk/javadoc/com/googlecode/memcachefy/CacheManager.html)) and respective base implementations for dealing with the different cache APIs in an uniform way (It'd be desirable to make it comply with [JSR-107](http://jcp.org/en/jsr/detail?id=107) in future versions).
In addition, Memcachefy provides a [CacheInterceptor](http://memcachefy.googlecode.com/svn/trunk/javadoc/com/googlecode/memcachefy/interceptor/CacheInterceptor.html) facility by which you can (almost) transparently enable caching into your existing code.

### Cache interceptor ###

The [CacheInterceptor](http://memcachefy.googlecode.com/svn/trunk/javadoc/com/googlecode/memcachefy/interceptor/CacheInterceptor.html) is a convenience API for intercepting method calls of any existing class, so that the returning values can be cached and used later in future calls to the method. It currently comes in two flavours: [Dynamic proxies](http://memcachefy.googlecode.com/svn/trunk/javadoc/com/googlecode/memcachefy/CacheProxy.html) and [EJB3 interceptor](http://memcachefy.googlecode.com/svn/trunk/javadoc/com/googlecode/memcachefy/Ejb3CacheInterceptor.html). If you have in your code simple Java objects implementing interfaces, you can use the [CacheProxy](http://memcachefy.googlecode.com/svn/trunk/javadoc/com/googlecode/memcachefy/CacheProxy.html) for generating proxied instances of it. You can choose annotating the methods in your class as [Cacheable](http://memcachefy.googlecode.com/svn/trunk/javadoc/com/googlecode/memcachefy/interceptor/Cacheable.html) or just pass a list of methods (you want to enable caching) in the instantiation of the proxy class.  In case of EJB3 classes you only need to annotate in the class-level of the EJB the use of the [EJB3CacheInterceptor](http://memcachefy.googlecode.com/svn/trunk/javadoc/com/googlecode/memcachefy/Ejb3CacheInterceptor.html), marking the methods you want to enable caching with the [Cacheable](http://memcachefy.googlecode.com/svn/trunk/javadoc/com/googlecode/memcachefy/interceptor/Cacheable.html) annotation. See the [Examples](http://code.google.com/p/memcachefy/wiki/Examples#Caching_with_dynamic_proxies).

<img src='http://memcachefy.googlecode.com/files/memcachefy-interceptor-diag.gif' />

# Configuration #

[Configuration](http://code.google.com/p/memcachefy/wiki/Configuration)

# Examples #
[Examples](http://code.google.com/p/memcachefy/wiki/Examples)