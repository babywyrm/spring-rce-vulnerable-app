# DEPRECATED

This repo is not indicative of the Spring4Shell vulnerability any longer. The initial though was that the RCE was 
achieved via a "Deserialization Injection" attack vector, but it has since been discovered to use a Struts-style
"Class Loader Manipulation" attack vector instead.

For more details, please see our [blog post on Spring4Shell](https://www.lunasec.io/docs/blog/spring-rce-vulnerabilities). 

# Spring RCE example vulnerable application

This repository contains a Spring Boot web application vulnerable to a _possible_ RCE due to [this commit](https://github.com/spring-projects/spring-framework/pull/28075/files).

More details will be posted in [this Twitter thread](https://twitter.com/LunaSecIO/status/1509084844042510336) as they are identified. For now though, this repo serves only to help others determine the exploitability and impact of this vulnerability.

It uses Log4j 2.14.1 (through `spring-boot-starter-log4j2` 2.6.1) and the JDK 12. (_Note: Apparently JDK versions 8 and below aren't vulnerable.)

## Running the application

Run and build it yourself (you don't need any Java-related tooling):

```bash
docker build . -t vulnerable-app
docker run -p 8080:8080 --name vulnerable-app --rm vulnerable-app
```

## Exploitation steps

*Note: This is highly inspired from the original [LunaSec advisory](https://www.lunasec.io/docs/blog/log4j-zero-day/). **Run at your own risk, preferably in a VM in a sandbox environment**.*

_Currently unknown!_

We'll post up a more formal guide once we determine if there is a real exploit available for this issue. To stay informed, please follow us on [Twitter](https://twitter.com/LunaSecIO) or subscribe to our [mailing list](https://www.lunasec.io/docs/blog/node-ipc-protestware/#help-us-stop-malicious-dependencies) for updates.

```bash
$ curl -v 127.0.0.1:8080/post-body --data "$(echo foobar)"                                                              
*   Trying 127.0.0.1:8080...
* Connected to 127.0.0.1 (127.0.0.1) port 8080 (#0)
> POST /post-body HTTP/1.1
> Host: 127.0.0.1:8088
> User-Agent: curl/7.82.0
> Accept: */*
> Content-Length: 2
> Content-Type: application/x-www-form-urlencoded
> 
* Mark bundle as not supporting multiuse
< HTTP/1.1 200 
< Content-Type: text/plain;charset=UTF-8
< Content-Length: 26
< Date: Wed, 30 Mar 2022 09:46:04 GMT
< 
* Connection #0 to host 127.0.0.1 left intact
Hello, foobar 
```

## Reference

https://www.lunasec.io/docs/blog/log4j-zero-day/
https://mbechler.github.io/2021/12/10/PSA_Log4Shell_JNDI_Injection/

## Contributors

Thanks for these people for writing this basic vulnerable Spring app for Log4Shell. This repo is simply a fork of that with some slight tweaks. They did most of the work!

[@christophetd](https://twitter.com/christophetd)
[@rayhan0x01](https://twitter.com/rayhan0x01)
